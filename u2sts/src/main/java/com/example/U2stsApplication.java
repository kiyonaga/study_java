package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class U2stsApplication
{
	private static final Logger logger = LoggerFactory.getLogger(U2stsApplication.class);

//	@Autowired
//	private JdbcTemplate jdbc;

	@Autowired
	private HogeService svc;

	public static void main(String[] args)
	{
		// Webアプリの場合、try-with-resourcesだとコンテナが終了してしまう。
		//		try (ConfigurableApplicationContext ctx = SpringApplication.run(U2stsApplication.class, args))
		//		{
		//			U2stsApplication main = ctx.getBean(U2stsApplication.class);
		////			main.init();
		//		}

    SpringApplication app = new SpringApplication(U2stsApplication.class);
    app.setBannerMode(Banner.Mode.OFF);

		ConfigurableApplicationContext ctx = app.run(args);
		U2stsApplication main = ctx.getBean(U2stsApplication.class);
		main.init();
	}

	public void init()
	{
//		jdbc.execute("CREATE TABLE if not exists TEST_TABLE (ID INTEGER NOT NULL IDENTITY, VALUE VARCHAR(256))");
//		logger.info("Called init. sql={}", "CREATE TABLE if not exists TEST_TABLE (ID INTEGER NOT NULL IDENTITY, VALUE VARCHAR(256))");
		svc.init();
	}

//	private static final String[] cv = {"hoge", "fuga", "piyo" };

//	@RequestMapping("/")
//	public String method()
//	{
//		String value = cv[(int)(Math.random() * cv.length)];
//
//		logger.debug("sql={}", "INSERT INTO TEST_TABLE (VALUE) VALUES (?)");
//		jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", value);
//
//		List<Map<String, Object>> list = jdbc.queryForList("SELECT * FROM TEST_TABLE");
//		StringBuffer sb = new StringBuffer();
//		list.forEach(sb::append);
//
//		logger.info("Called method().");
//		return sb.toString();
//	}

	@RequestMapping("/")
	public String mmm()
	{
		svc.insertRandam();
		StringBuffer sb = new StringBuffer();
		svc.select().forEach(sb::append);
		logger.info("Called mmm().");
		return sb.toString();
	}
}
