package hello;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TxScriptServiceTest
{
	private static final Logger logger = LoggerFactory.getLogger(TxScriptServiceTest.class);

	@Autowired
	private TxScriptService txScriptService;

	@Test
	public void executeForceRollback()
	{
		Assert.assertEquals("", "ForceRollback", txScriptService.executeForceRollback("aa", "").resultMessage);
	}

	@Test
	public void execute()
	{
		Assert.assertEquals("", "", txScriptService.execute("", "").resultMessage);
	}

}
