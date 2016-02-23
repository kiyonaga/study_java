package com.example;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class U2stsApplication
{

	@Autowired
	private JdbcTemplate jdbc;

	public static void main(String[] args)
	{
		//		try (ConfigurableApplicationContext ctx = SpringApplication.run(U2stsApplication.class, args))
		//		{
		//			U2stsApplication main = ctx.getBean(U2stsApplication.class);
		////			main.init();
		//		}
		ConfigurableApplicationContext ctx = SpringApplication.run(U2stsApplication.class, args);
		U2stsApplication main = ctx.getBean(U2stsApplication.class);
		main.init();
	}

	public void init()
	{
		this.jdbc.execute("CREATE TABLE if not exists TEST_TABLE (ID INTEGER NOT NULL IDENTITY, VALUE VARCHAR(256))");
	}

	@RequestMapping("/")
	public String method()
	{
		this.jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", "hoge");
		this.jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", "fuga");
		this.jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", "piyo");

		List<Map<String, Object>> list = this.jdbc.queryForList("SELECT * FROM TEST_TABLE");
		StringBuffer sb = new StringBuffer();
		list.forEach(sb::append);
		return sb.toString();
	}
}
