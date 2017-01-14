import java.util.Comparator;

class ComparatorTitleMPB implements Comparator<MoviePosterButton> {

	public int compare(MoviePosterButton m1, MoviePosterButton m2) {
		return m1.getMovie().getTitle().compareTo(m2.getMovie().getTitle());
	}

}