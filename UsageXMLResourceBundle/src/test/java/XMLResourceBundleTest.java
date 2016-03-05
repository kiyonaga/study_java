import static org.junit.Assert.*;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLResourceBundleTest {
	private static final Logger logger = LoggerFactory.getLogger(XMLResourceBundleTest.class);

	@Test
	public void test() {
		ResourceBundle rb = XMLResourceBundle.getBundle("mail");
		logger.debug("button.name: {}", rb.getString("button.name"));
		assertTrue("button.name", rb.getString("button.name").equals("へへへ"));

		rb = XMLResourceBundle.getBundle("mail", Locale.JAPAN);
		logger.debug("button.name: {}", rb.getString("button.name"));
		assertTrue("button.name", rb.getString("button.name").equals("ほほほ"));
	}
}
