package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {
		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		// Split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name -> name.split(" "))
				.collect(Collectors.toList());

		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

		log.info("Querying for customer records where first_name = 'Josh':");
		jdbcTemplate.query(
				"SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
				(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")))
				.forEach(customer -> log.info(customer.toString()));

		jdbcTemplate.execute("DROP TABLE foobar IF EXISTS");
		jdbcTemplate.execute(
				"CREATE TABLE foobar(id BIGINT not null identity, col1 VARCHAR(255), s_reg_date timestamp not null default current_timestamp)");

		Object[][] rows = new Object[][] { { "hoge" }, { "fuga" }, { "" }, { "piyo" } };
		List<Object[]> params = new ArrayList<Object[]>();
		for (Object[] e : rows) {
			params.add(e);
		}
		jdbcTemplate.batchUpdate("INSERT INTO foobar(col1) VALUES (?)", params);

		List<Foobar> list = jdbcTemplate.query(
				"SELECT id, col1, s_reg_date FROM foobar",
				(rs, rowNum) -> new Foobar(rs.getLong("id"), rs.getString("col1"), rs.getTimestamp("s_reg_date")));
		list.forEach(foobar -> log.info(foobar.toString()));

		jdbcTemplate.queryForList("SELECT id, col1, s_reg_date FROM foobar").forEach(map -> log.info(map.toString()));

		List<Foobar> listByBP = jdbcTemplate.query(
				"SELECT id, col1, s_reg_date FROM foobar", new BeanPropertyRowMapper<Foobar>(Foobar.class));
		listByBP.forEach(foobar -> log.info(foobar.toString()));

	}
}