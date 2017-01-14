import javax.imageio.*;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class Movie {

	private String path;
	private String title;
	private String year;
	private String runtime;
	private String genre;
	private String director;
	private String actors;
	private String plot;
	private String posterURL;
	private String metascore;
	private String imdbRating;
	private String type;
	private boolean favorite;
	private boolean seen;
	private BufferedImage poster;
	final static int WIDTH = 195;
	final static int HEIGHT = 290;

	public Movie() {
	}

	public Movie(String line) throws FileNotFoundException, IOException {
		Scanner s = new Scanner(line);
		s.useDelimiter(" ; ");
		path = s.next();
		title = s.next();
		year = s.next();
		runtime = s.next();
		genre = s.next();
		director = s.next();
		actors = s.next();
		plot = s.next();
		posterURL = s.next();
		metascore = s.next();
		imdbRating = s.next();
		type = s.next();
		favorite = Boolean.valueOf(s.next());
		seen = Boolean.valueOf(s.next());
		s.close();
		File imgIn = new File(title + ".jpg");
		poster = ImageIO.read(imgIn);
	}

	public static Movie createMovieFromIMDb(String title) throws IOException, NameException {

		Movie m = new Movie();
		Scanner s = new Scanner(title);
		String titleModif = s.next();
		while (s.hasNext()) {
			titleModif += "+" + s.next();
		}
		s.close();
		URL url = new URL("http://www.omdbapi.com/?t=" + titleModif + "&y=&plot=short&r=json");
		URLConnection urlConnection = url.openConnection();
		Scanner sc = new Scanner(new InputStreamReader(urlConnection.getInputStream()));
		String content = null;
		sc.useDelimiter("\"");
		sc.next();
		content = sc.next();
		if (content.equals("Response")) {
			sc.close();
			throw new NameException("Film non trouvé");
		} else {
			String aux;
			Boolean test = true;
			sc.next();
			m.title = sc.next();
			for (int i = 0; i <= 2; i++)
				sc.next();
			m.year = sc.next();
			for (int i = 0; i <= 10; i++)
				sc.next();
			m.runtime = sc.next();
			for (int i = 0; i <= 2; i++)
				sc.next();
			m.genre = sc.next();
			for (int i = 0; i <= 2; i++)
				sc.next();
			m.director = sc.next();
			do {
				aux = sc.next();
			} while (!aux.equals("Actors"));
			sc.next();
			m.actors = sc.next();
			for (int i = 0; i <= 2; i++)
				sc.next();
			m.plot = sc.next();
			aux = sc.next();
			do {
				if (aux.equals("")) {
					sc.next();
					test = true;
				} else {
					if (aux.substring(aux.length() - 1).equals("\\")) {
						aux = aux.substring(0, aux.length() - 1);
						m.plot = m.plot.substring(0, m.plot.length() - 1) + "\"" + aux + "\"";
						aux = sc.next();
						test = false;
					} else {
						if (!test) {
							sc.next();
							m.plot += aux;
						}
						test = true;
					}
				}
			} while (!test);
			for (int i = 0; i <= 13; i++)
				sc.next();
			m.posterURL = sc.next();
			for (int i = 0; i <= 2; i++)
				sc.next();
			m.metascore = sc.next();
			for (int i = 0; i <= 2; i++)
				sc.next();
			m.imdbRating = sc.next();
			for (int i = 0; i <= 10; i++)
				sc.next();
			m.type = sc.next();

			if (!m.posterURL.equals("N/A")) {
				URL imgUrl = new URL(m.posterURL);
				m.poster = ImageResizer.resizeAndSave(ImageIO.read(imgUrl), m.title + ".jpg", Movie.WIDTH,
						Movie.HEIGHT);
			} else {
				m.poster = new MoviePosterNotFoundImage(m);
			}
			m.favorite = false;
			m.seen = false;
		}
		sc.close();
		return m;
	}

	public static Movie createMovieFromIMDb(String title, String filePath) throws IOException, NameException {
		Movie m = createMovieFromIMDb(title);
		m.path = filePath;
		return m;
	}

	public static Movie createMovieFromIMDbWithPath(String filePath) throws IOException, NameException {
		String title = extractTitleFromFilePath(filePath);
		Movie m = createMovieFromIMDb(title);
		m.path = filePath;
		return m;
	}

	public static String extractTitleFromFilePath(String filePath) {
		String fileName = extractFileNameFromFilePath(filePath);
		Scanner s = new Scanner(fileName);
		Scanner sc = new Scanner(fileName);
		String title = "";
		String aux;
		s.useDelimiter(" ");
		sc.useDelimiter("\\.");
		boolean stopTest1, stopTest2, stopTest3, stopTest4;
		stopTest3 = true;
		stopTest4 = true;

		aux = s.next();
		if (s.hasNext()) {
			title += aux;
			aux = s.next();
			stopTest1 = !aux.substring(0, 1).equals("(");
			stopTest2 = !aux.substring(0, 1).equals("[");
			if (aux.length() > 1) {
				stopTest3 = !aux.substring(0, 2).equals("19");
				stopTest4 = !aux.substring(0, 2).equals("20");
			}
			while (s.hasNext() && stopTest1 && stopTest2 && stopTest3 && stopTest4) {
				title += " " + aux;
				aux = s.next();
				stopTest1 = !aux.substring(0, 1).equals("(");
				stopTest2 = !aux.substring(0, 1).equals("[");
				if (aux.length() > 1) {
					stopTest3 = !aux.substring(0, 2).equals("19");
					stopTest4 = !aux.substring(0, 2).equals("20");
				}
			}
		} else {
			aux = sc.next();
			title += aux;
			aux = sc.next();

			stopTest1 = !aux.substring(0, 1).equals("(");
			stopTest2 = !aux.substring(0, 1).equals("[");
			if (aux.length() > 1) {
				stopTest3 = !aux.substring(0, 2).equals("19");
				stopTest4 = !aux.substring(0, 2).equals("20");
			}
			while (sc.hasNext() && stopTest1 && stopTest2 && stopTest3 && stopTest4) {
				title += " " + aux;
				aux = sc.next();
				stopTest1 = !aux.substring(0, 1).equals("(");
				stopTest2 = !aux.substring(0, 1).equals("[");
				if (aux.length() > 1) {
					stopTest3 = !aux.substring(0, 2).equals("19");
					stopTest4 = !aux.substring(0, 2).equals("20");
				}
			}
		}
		s.close();
		sc.close();
		return title;
	}

	public static String extractFileNameFromFilePath(String filePath) {
		Scanner s = new Scanner(filePath);
		s.useDelimiter("\\\\");
		String fileName = "";
		do {
			fileName = s.next();
		} while (s.hasNext());
		s.close();
		return fileName;
	}

	public String toString() {

		return (path + " ; " + title + " ; " + year + " ; " + runtime + " ; " + genre + " ; " + director + " ; "
				+ actors + " ; " + plot + " ; " + posterURL + " ; " + metascore + " ; " + imdbRating + " ; " + type
				+ " ; " + favorite + " ; " + seen);
	}

	public String getPath() {
		return path;
	}

	public BufferedImage getPoster() {
		return poster;
	}

	public String getTitle() {
		return title;
	}

	public String getPlot() {
		return plot;
	}

	public String getYear() {
		return year;
	}

	public String getDirector() {
		return director;
	}

	public String getActors() {
		return actors;
	}

	public String getGenre() {
		return genre;
	}

	public String getRuntime() {
		return runtime;
	}

	public String getIMDbRating() {
		return imdbRating;
	}

	public boolean isLiked() {
		return favorite;
	}

	public boolean isWatched() {
		return seen;
	}

	public MoviePosterButton getMoviePosterButton() {
		return new MoviePosterButton(this);
	}

	public void modifyFilePath(String filePath) {
		path = filePath;
	}

	public void savePoster() throws IOException {
		if (poster != null) {
			File imgOut = new File(title + ".jpg");
			ImageIO.write(poster, "jpg", imgOut);
		}
	}

	public void makeFavorite() {
		favorite = true;
	}

	public void unmakeFavorite() {
		favorite = false;
	}

	public void makeWatched() {
		seen = true;
	}

	public void unmakeWatched() {
		seen = false;
	}

}