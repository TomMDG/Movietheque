import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

class WatchedButton extends JButton implements MouseListener{

	private static final long serialVersionUID = 1L;
	private Movie movie; 
	
	public WatchedButton(Movie m) throws SourceFilesMissingException{
		super();
		try {
			movie = m;
			File imgIn;
			if (movie.isWatched()) {
				imgIn = new File("src img/watchedButton1.png");
			} else {
				imgIn = new File("src img/watchedButton0.png");
			}
			BufferedImage bi = ImageIO.read(imgIn);
			setIcon(new ImageIcon(bi));
			setOpaque(true);
			setContentAreaFilled(false);
			setBorderPainted(false);
			setFocusPainted(false);
			setMargin(new Insets(0, 0, 0, 0));
			this.addMouseListener(this);
		} catch (IOException e) {
			throw new SourceFilesMissingException("\"Watched\" button not found...");
		}
	}

	public void switchButton() throws SourceFilesMissingException {
		File imgIn;
		if (movie.isWatched()) {
			imgIn = new File("src img/watchedButton0.png");
			movie.unmakeWatched();
		} else {
			imgIn = new File("src img/watchedButton1.png");
			movie.makeWatched();
		}
		BufferedImage bi;
		try {
			bi = ImageIO.read(imgIn);
			setIcon(new ImageIcon(bi));
		} catch (IOException e) {
			throw new SourceFilesMissingException("\"Watched\" button not found...");

		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		try {
			switchButton();
		} catch (SourceFilesMissingException e) {
			// TODO Auto-generated catch block
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}