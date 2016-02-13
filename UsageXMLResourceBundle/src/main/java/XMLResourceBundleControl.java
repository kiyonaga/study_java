
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * XMLファイルによるリソースバンドルの扱いを可能にするコントローラ
 */
public class XMLResourceBundleControl extends ResourceBundle.Control
{
	// 対応するフォーマット形式
	private static final String xml = "xml";

	/**
	 * 対応するフォーマットを返す.
	 */
	@Override
	public List<String> getFormats(String baseName)
	{
		return Arrays.asList(xml);
	}

	@Override
	public long getTimeToLive(String baseName, Locale locale)
	{
		// リソースバンドルのキャッシュを無効。
		return TTL_DONT_CACHE;
	}

	/**
	 * http://docs.oracle.com/javase/6/docs/api/java/util/ResourceBundle.Control.html
	 */
	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException
	{
		if (baseName == null || locale == null || format == null || loader == null)
		{
			throw new NullPointerException();
		}

		ResourceBundle bundle = null;
		if (format.equals(xml))
		{
			String bundleName = toBundleName(baseName, locale);
			String resourceName = toResourceName(bundleName, format);
			InputStream stream = null;
			URL url = loader.getResource(resourceName);

			if (url != null)
			{
				URLConnection connection = url.openConnection();
				if (connection != null)
				{
					if (reload)
					{
						// リロードの場合はキャッシュを無効にしてロードを試みる
						connection.setUseCaches(false);
					}
					stream = connection.getInputStream();
				}
			}

			// 取得したXMLファイルからXMLリソースバンドルを生成して返す。
			if (stream != null)
			{
				BufferedInputStream bis = new BufferedInputStream(stream);
				try
				{
					bundle = new XMLResourceBundleConcrete(bis);
				}
				finally
				{
					bis.close();
				}
			}
		}
		return bundle;
	}
}
