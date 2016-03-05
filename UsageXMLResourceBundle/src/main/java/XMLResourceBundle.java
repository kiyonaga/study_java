import java.util.Locale;
import java.util.ResourceBundle;

public class XMLResourceBundle {
	private static final XMLResourceBundleControl xmlctl = new XMLResourceBundleControl();

	/**
	 * XML形式のリソースバンドルを返す。
	 * @param baseName
	 * @return
	 */
	public static final ResourceBundle getBundle(String baseName) {
		return ResourceBundle.getBundle(baseName, xmlctl);
	}

	/**
	 * XML形式のリソースバンドルを返す。指定ロケールの。
	 * @param baseName
	 * @param locale
	 * @return
	 */
	public static final ResourceBundle getBundle(String baseName, Locale locale) {
		return ResourceBundle.getBundle(baseName, locale, xmlctl);
	}

}
