
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * XMLファイルから構築されるリソースバンドル
 */
public class XMLResourceBundle extends ResourceBundle
{
	// XMLリソースバンドルのバックエンドとしてPropertiesクラスを使う.
	private Properties props = new Properties();

	XMLResourceBundle(InputStream is) throws IOException
	{
		props.loadFromXML(is);
	}

	@Override
	protected Object handleGetObject(String key)
	{
		return props.getProperty(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Enumeration<String> getKeys()
	{
		// Properties#keys()は、Objectを列挙するような型宣言だが
		// 実体はStringなので単にキャストすれば良い.
		// 互換性のない総称型同士をキャストすることはできないが、
		// 一旦、raw経由にすればキャスト可能になる。
		@SuppressWarnings("rawtypes")
		Enumeration enm = props.keys();
		return enm;
	}
}
