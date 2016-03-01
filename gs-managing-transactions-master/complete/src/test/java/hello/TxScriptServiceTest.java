package hello;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TxScriptServiceTest
{
//	private static final Logger logger = LoggerFactory.getLogger(TxScriptServiceTest.class);

	@Autowired
	private TxScriptService txScriptService;

	@Test
	public void execute()
	{
		Assert.assertEquals("", false, txScriptService.execute("", "abcde").isFail());
	}

	@Test
	public void executeForceRollback()
	{
//		logger.debug("Force rollback...");
		Assert.assertEquals("", "Force rollback.", txScriptService.executeForceRollback("aa", "").getResultMessage());
	}

}
