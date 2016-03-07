package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HogeService
{
	private static final Logger logger = LoggerFactory.getLogger(HogeService.class);

	@Autowired
	private JdbcTemplate jdbc;

	public void init()
	{
		String sql = "CREATE TABLE if not exists TEST_TABLE (ID INTEGER NOT NULL IDENTITY, VALUE VARCHAR(256), col2 varchar(255), value2 varchar(255))";
		jdbc.execute(sql);
		logger.info("Called init. sql={}", sql);
	}

	public List<Map<String, Object>> select()
	{
		return jdbc.queryForList("SELECT * FROM TEST_TABLE");
	}

	private static final String[] cv = {"hoge", "fuga", "piyo" };

	public int insertRandam()
	{
		String value = cv[(int)(Math.random() * cv.length)];

		String sql = "INSERT INTO TEST_TABLE (VALUE, value2) VALUES (?, ?)";
		logger.debug("sql={}", sql);
		return jdbc.update(sql, value, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime()));
	}
}
