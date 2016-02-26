package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxRollbackService
{
	private final static Logger log = LoggerFactory.getLogger(TxRollbackService.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void rollback()
	{
		jdbcTemplate.execute("rollback");
		log.info("rollback.");
	}
}
