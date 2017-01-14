import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoviePosterButton extends JButton {

	private static final long serialVersionUID = 1L;

	Movie movie;

	public MoviePosterButton(Movie m) {
		super("");
		movie = m;
		BufferedImage poster = m.getPoster();
		setOpaque(true);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setIcon(new ImageIcon(poster));
		setMaximumSize(new Dimension(poster.getWidth(), m.getPoster().getHeight()));
		setSize(new Dimension(m.getPoster().getWidth(), m.getPoster().getHeight()));
		setMargin(new Insets(0, 0, 0, 0));
	}

	public Movie getMovie() {
		return movie;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public static void main(String[] args) throws IOException, NameException {
		JFrame window = new JFrame();
		JPanel yolo = new JPanel();
		window.setTitle("MovieTime");
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Movie film1 = Movie.createMovieFromIMDb("Pulp Fiction");
		yolo.add(new MoviePosterButton(film1));
		window.add(yolo);
		window.setVisible(true);
	}

}