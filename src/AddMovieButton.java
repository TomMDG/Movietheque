import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

class AddMovieButton extends JButton {

	private static final long serialVersionUID = 1L;

	public AddMovieButton() throws SourceFilesMissingException {
		super();
		try {
			File imgIn = new File("src img/addButton.png");
			BufferedImage bi = ImageIO.read(imgIn);
			setIcon(new ImageIcon(bi));
			setOpaque(true);
			setContentAreaFilled(false);
			setBorderPainted(false);
			setFocusPainted(false);
			setMargin(new Insets(0, 0, 0, 0));
		} catch (IOException e) {
			throw new SourceFilesMissingException("\"Add\" button not found...");
		}
	}
}