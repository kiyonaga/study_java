import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * アプリケーションから利用するlogging APIは、SLF4J とする。
 * ログ実装は log4j2 を使用する。
 *
 * アプリケーションからorg.apache.logging.log4の利用を禁止する(コンパイルエラーとする)方法はないか？
 */
public class LogTest
{
	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
	//	private static final org.apache.logging.log4j.Logger log4j = LogManager.getLogger();

	@Test
	public void test()
	{
		logger.debug("Log test {} {} {}.", "1", "22", "333");
		//		log4j.debug("Log test {} {} {}.", "1", "22", "333");
	}
}
