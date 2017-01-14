import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class IMDbRatingPanel extends JPanel {

	private BufferedImage starA, starB, starC, starD, starE;
	private static final File STAR0 = new File("src img/star0.png");
	private static final File STAR1 = new File("src img/star1.png");
	private static final File STAR2 = new File("src img/star2.png");
	private static final File[] LIST_FILE = { STAR0, STAR1, STAR2 };
	private static final Dimension DIM = new Dimension(Movie.WIDTH,25);
	
	private static final long serialVersionUID = 1L;

	public IMDbRatingPanel(Movie m) throws IOException {
		super();
		setOpaque(false);
		setPreferredSize(DIM);

		BufferedImage[] listStar = new BufferedImage[5];
		String imdbRating = m.getIMDbRating();
		float rating = Float.parseFloat(imdbRating);
		float aux = 0;
		float diff;
		int compteurInt = -1;
		int currentStar = 0;
		while (aux < rating) {
			aux += 2;
			compteurInt += 1;
		}
		for (int i = 0; i < compteurInt; i++) {
			listStar[i] = ImageIO.read(LIST_FILE[2]);
			currentStar += 1;
		}

		diff = rating-aux+2;
		if (diff<0.5){
			listStar[currentStar] = ImageIO.read(LIST_FILE[0]);
		}else if(diff<1.5){
			listStar[currentStar] = ImageIO.read(LIST_FILE[1]);
		}else {

			listStar[currentStar] = ImageIO.read(LIST_FILE[2]);
		}
		currentStar += 1;
		while (currentStar <= 4) {
			listStar[currentStar] = ImageIO.read(LIST_FILE[0]);
			currentStar += 1;
		}
		starA = listStar[0];
		starB = listStar[1];
		starC = listStar[2];
		starD = listStar[3];
		starE = listStar[4];
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(starA, 20, 0, this);
		g.drawImage(starB, 52, 0, this);
		g.drawImage(starC, 84, 0, this);
		g.drawImage(starD, 116, 0, this);
		g.drawImage(starE, 148, 0, this);
	}

	public static void main(String[] args) throws IOException, NameException, SourceFilesMissingException {
		JFrame window = new JFrame();
		window.setTitle("MovieTime");
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ListMovie lste = new ListMovie("ListMovie");
		Movie film1 = lste.get(3);
		window.add(new IMDbRatingPanel(film1));
		System.out.println(film1);

		window.setVisible(true);
	}
}