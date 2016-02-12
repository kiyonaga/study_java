import static org.junit.Assert.*;
import java.util.ResourceBundle;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLResourceBundleTest
{
	private static final Logger logger = LoggerFactory.getLogger(XMLResourceBundleTest.class);

	private static final XMLResourceBundleControl xmlctl = new XMLResourceBundleControl();

	@Test
	public void test()
	{
		ResourceBundle rb = ResourceBundle.getBundle("mail", xmlctl);
		logger.debug("button.name: {}", rb.getString("button.name"));
		assertTrue("button.name", rb.getString("button.name").equals("へへへ"));
	}
}
