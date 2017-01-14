import java.util.ArrayList;
import java.util.Collections;

class ListMoviePosterButton {

	private ArrayList<MoviePosterButton> listMPB;

	public ListMoviePosterButton() {
		listMPB = new ArrayList<MoviePosterButton>();
	}

	public void addMovie(MoviePosterButton MPB) {
		listMPB.add(MPB);
	}

	public void addMovie(Movie m) {
		listMPB.add(new MoviePosterButton(m));
	}

	public int getSize() {
		return listMPB.size();
	}

	public MoviePosterButton getLast() {
		return listMPB.get(this.getSize() - 1);
	}

	public MoviePosterButton get(int i) {
		return listMPB.get(i);
	}

	public ArrayList<MoviePosterButton> get() {
		return listMPB;
	}

	public void sortByTitle() {
		Collections.sort(listMPB, new ComparatorTitleMPB());
	}

	public void delete(MoviePosterButton mpb) {
		listMPB.remove(mpb);
	}
}