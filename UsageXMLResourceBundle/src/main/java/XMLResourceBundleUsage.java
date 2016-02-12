import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLResourceBundleUsage
{
	private static final Logger logger = LoggerFactory.getLogger(XMLResourceBundleUsage.class);

	public XMLResourceBundleUsage()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				// mail.xml というXML形式のリソースを取得。
				ResourceBundle rb = XMLResourceBundle.getBundle("mail");

				JFrame frame = new JFrame(rb.getString("title.name"));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JButton button = new JButton(rb.getString("button.name"));

				button.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent event)
					{
						JButton button = (JButton) event.getSource();

						// getBundleすることで、リソースの再読み込みが行われる。
						ResourceBundle rb = XMLResourceBundle.getBundle("mail");
						logger.debug("title:{} button:{}", rb.getString("title.name"), rb.getString("button.name"));
						frame.setTitle(rb.getString("title.name"));
						button.setText(rb.getString("button.name"));
					}
				});

				frame.add(button);
				frame.setLocationRelativeTo(null);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

	public static void main(String[] args)
	{
		new XMLResourceBundleUsage();
	}
}
