package hello;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotifyService
{
	private final static Logger log = LoggerFactory.getLogger(NotifyService.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void notify(String... persons)
	{
		for (String person : persons)
		{
			log.info("Notify to {}... ", person);
			jdbcTemplate.update("insert into NOTIFIES(FIRST_NAME) values (?)", person);
		}
	};

	public List<String> find()
	{
		return jdbcTemplate.query("select FIRST_NAME from NOTIFIES", new RowMapper<String>()
		{
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				return rs.getString("FIRST_NAME");
			}
		});
	}

}
