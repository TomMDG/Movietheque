import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MovieInfosPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Movie movie;
	private static final int WIDTH = 850;
	private static final int HEIGHT = 400;

	public MovieInfosPanel(Movie m) {
		super();
		movie = m;

		JTextArea infos = new JTextArea();

		Font fontPlain = new Font("Verdana", Font.PLAIN, 15);
		infos.append("Year : ");
		infos.append(movie.getYear() + "\n");
		infos.append("Runtime : ");
		infos.append(movie.getRuntime() + "\n");
		infos.append("Genre : ");
		infos.append(movie.getGenre() + "\n");
		infos.append("Director : ");
		infos.append(movie.getDirector() + "\n");
		infos.append("Actors : ");
		infos.append(movie.getActors() + "\n" + "\n");
		infos.append("Plot : ");
		infos.append(movie.getPlot());
		infos.setFont(fontPlain);
		infos.setWrapStyleWord(true);
		infos.setLineWrap(true);
		infos.setOpaque(false);
		infos.setEditable(false);
		infos.setSize(new Dimension(MovieInfosPanel.WIDTH, MovieInfosPanel.HEIGHT));
		infos.setForeground(Color.WHITE);
		this.add(infos);
		setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public static void main(String[] arg) throws IOException, NameException {
		JFrame window = new JFrame();
		window.setTitle("MovieTime");
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Movie film1 = Movie.createMovieFromIMDb("Pulp Fiction");
		MovieInfosPanel bg = new MovieInfosPanel(film1);
		window.add(bg);
		window.pack();
		window.setVisible(true);

	}
}
