import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class ListMovie {

	private ArrayList<Movie> list;

	public ListMovie() {
		list = new ArrayList<Movie>();
	}

	public ListMovie(String s) throws SourceFilesMissingException, IOException {
		try {
			FileReader in = new FileReader(s + ".txt");
			Scanner sc = new Scanner(in);
			Movie m;
			list = new ArrayList<Movie>();
			while (sc.hasNext()) {
				m = new Movie(sc.nextLine());
				list.add(m);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			new File("ListMovie.txt");
			list = new ArrayList<Movie>();
			throw new SourceFilesMissingException("Text file \"ListMovie.txt\" not found... We've created a new one for you!");
		}
	}

	public void add(Movie m) {
		list.add(m);
	}

	public boolean containsMovie(Movie movie) {
		String title = movie.getTitle();
		for (Movie m : list) {
			if (m.getTitle().equals(title)) {
				return true;
			}
		}
		return false;
	}

	public void delete(Movie m) {
		list.remove(m);
		if (!containsMovie(m)) {
			File PosterFile = new File(m.getTitle() + ".jpg");
			PosterFile.delete();
		}
	}

	public void sortByTitle() {
		Collections.sort(list, new ComparatorTitle());
	}

	public ArrayList<Movie> getList() {
		return list;
	}

	public int getSize() {
		return list.size();
	}

	public Movie get(int i) {
		return list.get(i);
	}

	public void save() throws IOException {
		sortByTitle();
		PrintWriter textOut = new PrintWriter(new FileWriter("ListMovie.txt"));
		for (int i = 0; i < list.size(); i++) {
			textOut.println(list.get(i));
		}
		textOut.close();
	}

}