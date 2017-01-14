import java.awt.Color;

import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

class LikeButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	private Movie movie;

	public LikeButton(Movie m) throws SourceFilesMissingException {
		super();
		try {
			movie = m;
			File imgIn;
			if (movie.isLiked()) {
				imgIn = new File("src img/likeButton1.png");
			} else {
				imgIn = new File("src img/likeButton0.png");
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
			throw new SourceFilesMissingException("\"Like\" button not found...");
		}
	}

	public void switchButton() throws SourceFilesMissingException {
		File imgIn;
		if (movie.isLiked()) {
			imgIn = new File("src img/likeButton0.png");
			movie.unmakeFavorite();
		} else {
			imgIn = new File("src img/likeButton1.png");
			movie.makeFavorite();
		}
		BufferedImage bi;
		try {
			bi = ImageIO.read(imgIn);
			setIcon(new ImageIcon(bi));
		} catch (IOException e) {
			throw new SourceFilesMissingException("Bouton \"aimer\" non trouvé...");

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

	public static void main(String[] args) throws IOException, NameException, SourceFilesMissingException {
		JFrame window = new JFrame();
		window.setTitle("MovieTime");
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.BLUE);

		ListMovie lste = new ListMovie("ListMovie");
		Movie film1 = lste.get(3);
		window.add(new LikeButton(film1));
		window.add(new AddMovieButton());
		System.out.println(film1);

		window.setVisible(true);
	}
}
