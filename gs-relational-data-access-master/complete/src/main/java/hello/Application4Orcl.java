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
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ninja.cero.sqltemplate.core.SqlTemplate;

@SpringBootApplication
public class Application4Orcl implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(Application4Orcl.class);

	public static void main(String args[]) {
		SpringApplication.run(Application4Orcl.class, args);
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SqlTemplate template;

	@Bean
  SqlTemplate sqlTemplate(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
      return new SqlTemplate(jdbcTemplate, namedParameterJdbcTemplate);
  }


	@Override
	public void run(String... strings) throws Exception {
		log.info("Creating tables");

/////		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE customers(id long, first_name VARCHAR2(255), last_name VARCHAR2(255))");

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

		//////////

/////		jdbcTemplate.execute("DROP TABLE foobar IF EXISTS");
		jdbcTemplate.execute(
			"CREATE TABLE foobar(id long, col1 VARCHAR2(255), col2 varchar2(255), s_reg_date timestamp default sysdate not null)");

		Object[][] rows = new Object[][] { { "hoge", "HOGE" }, { "fuga", "FUGA" }, { "", "none" }, { "piyo", "PIYO" } };
		List<Object[]> params = new ArrayList<Object[]>();
		for (Object[] e : rows) {
			params.add(e);
		}
		jdbcTemplate.batchUpdate("INSERT INTO foobar(col1, col2) VALUES (?, ?)", params);

		log.info("--- Use queryForList(Map).");
		jdbcTemplate.queryForList("SELECT id, col1, col2, s_reg_date FROM foobar")
			.forEach(map -> log.info(map.toString()));

		log.info("--- Use BeanPropertyRowMapper(Foobar).");
		// BeanPropertyRowMapperはとても便利だが、パフォーマンスを求める場合は、自前のRowMapperを実装すること。
		List<Foobar> listByBP = jdbcTemplate.query(
			"SELECT /*id,*/ col1, col2,  s_reg_date FROM foobar", new BeanPropertyRowMapper<Foobar>(Foobar.class));
		listByBP.forEach(foobar -> log.info(foobar.toString()));

		log.info("--- Use define RowMapper(Foobar).");
		List<Foobar> list = jdbcTemplate.query(
			"SELECT id, col1, col2, s_reg_date FROM foobar",
			(rs, rowNum) -> new Foobar(rs.getLong("id"), rs.getString("col1"), rs.getString("col2"), rs.getTimestamp("s_reg_date")));
		list.forEach(foobar -> log.info(foobar.toString()));

		List<NotifiesVo4Orcl> emps = template.forList("sql/selectAll4Orcl.sql", NotifiesVo4Orcl.class);
		emps.forEach(e -> log.info(e.getFirst_name()));

	}
}