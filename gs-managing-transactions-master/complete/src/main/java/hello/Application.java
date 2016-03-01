package hello;

import javax.sql.DataSource;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	//	@Bean
	//	BookingService bookingService()
	//	{
	//		return new BookingService();
	//	}

	@Autowired
	private BookingService bookingService;
	@Autowired
	private TxScriptService txScriptService;

	@Bean
	JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		log.info("Creating tables");
		jdbcTemplate.execute("drop table BOOKINGS if exists");
		jdbcTemplate.execute(
				"create table BOOKINGS(ID serial, FIRST_NAME varchar(5) NOT NULL, s_reg_date timestamp not null default current_timestamp)");
		jdbcTemplate.execute("drop table NOTIFIES if exists");
		jdbcTemplate.execute(
				"create table NOTIFIES(ID serial, FIRST_NAME varchar(5) NOT NULL, s_reg_date timestamp not null default current_timestamp)");
		return jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		ConfigurableApplicationContext ctx = app.run(args);

		Application main = ctx.getBean(Application.class);
		main.demo();
	}

	private void demo() {
		bookingService.book("Alice", "Bob", "Carol");
		Assert.assertEquals("First booking should work with no problem", 3, bookingService.findAllBookings().size());

		try {
			bookingService.book("Chris", "Samuel");
		}
		catch (RuntimeException e) {
			log.info("v--- The following exception is expect because 'Samuel' is too big for the DB ---v");
			log.error(e.getMessage());
		}

		for (String person : bookingService.findAllBookings()) {
			log.info("So far, " + person + " is booked.");
		}
		log.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, and Chris was rolled back in the same TX");
		Assert.assertEquals("'Samuel' should have triggered a rollback", 3, bookingService.findAllBookings().size());

		try {
			bookingService.book("Buddy", null);
		}
		catch (RuntimeException e) {
			log.info("v--- The following exception is expect because null is not valid for the DB ---v");
			log.error(e.getMessage());
		}

		for (String person : bookingService.findAllBookings()) {
			log.info("So far, " + person + " is booked.");
		}
		log.info("You shouldn't see Buddy or null. null violated DB constraints, and Buddy was rolled back in the same TX");
		Assert.assertEquals("'null' should have triggered a rollback", 3, bookingService.findAllBookings().size());

		///// 複数のServiceを呼び出すTransactionScriptの例。
		// test rollback.
		try {
			log.info(txScriptService.executeForceRollback("Foo", "Bar", "Baz").getResultMessage());
		}
		catch (RuntimeException e) {
			log.error(e.getMessage(), e);
		}

		try {
			log.info(txScriptService.execute("Foo1", "Bar2", "Baz3").getResultMessage());
		}
		catch (RuntimeException e) {
			log.error(e.getMessage(), e);
		}

	}

}
