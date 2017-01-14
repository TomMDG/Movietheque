import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

class MovieTitlePanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public MovieTitlePanel(Movie m){
		super();
		JTextArea title = new JTextArea(m.getTitle());
		Font font = new Font("Verdana", Font.BOLD, 30);
		title.setFont(font);
		title.setForeground(Color.WHITE);
		title.setOpaque(false);
		add(title);
		setOpaque(false);
	}
	
}