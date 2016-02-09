import static org.junit.Assert.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 *
 */
public class LibraryTest
{
	private static final Logger logger = LogManager.getLogger();

	@Test
	public void testSomeLibraryMethod()
	{
		Library classUnderTest = new Library();
		assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
		logger.debug("Log test.");
	}
}
