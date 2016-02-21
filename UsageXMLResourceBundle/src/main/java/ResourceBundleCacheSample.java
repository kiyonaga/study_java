import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ResourceBundleCacheSample
{
	private ResourceBundle.Control control;

	public ResourceBundleCacheSample()
	{
		control = new ResourceBundle.Control()
		{
			@Override
			public long getTimeToLive(String baseName, Locale locale)
			{
				// リソースバンドルのキャッシュの有効期限は10秒
				return 20000L;
			}
		};

		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				JFrame frame = new JFrame("ResourceBundle Sample");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				ResourceBundle bundle = ResourceBundle.getBundle("mail", control);

				JButton button = new JButton(bundle.getString("button.name"));
				button.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent event)
					{
						JButton button = (JButton) event.getSource();

						// リソースバンドルの再読み込み
						ResourceBundle bundle = ResourceBundle.getBundle("mail", control);
						button.setText(bundle.getString("button.name"));
					}
				});
				frame.add(button);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

	public static void main(String[] args)
	{
		new ResourceBundleCacheSample();
	}
}
