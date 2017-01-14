import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class MoviePosterNotFoundImage extends BufferedImage {

	public static final Font TITLE_FONT = new Font("Verdana", Font.BOLD, 15);
	public static final int FONT_SIZE_TO_PIXEL = 8;

	public MoviePosterNotFoundImage(Movie m) throws IOException {
		super(Movie.WIDTH, Movie.HEIGHT, TYPE_3BYTE_BGR);
		String movieTitle = m.getTitle();
		Graphics g = getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Movie.WIDTH, Movie.HEIGHT);
		g.setFont(TITLE_FONT);
		g.setColor(Color.BLACK);
		int n = movieTitle.length() * FONT_SIZE_TO_PIXEL;
		g.drawString(movieTitle, (Movie.WIDTH - n) / 2, 145);
		ImageIO.write(this, "jpg", new File(movieTitle + ".jpg"));
	}

	public static void main(String[] arg) throws IOException, NameException {
		JFrame window = new JFrame();
		window.setTitle("MovieTime");
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Movie film = Movie.createMovieFromIMDb("Pulp Fiction");
		MoviePosterNotFoundImage img = new MoviePosterNotFoundImage(film);
		ImageIO.write(img, "jpg", new File("test.jpg"));
	}
}