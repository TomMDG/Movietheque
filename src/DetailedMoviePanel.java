import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class DetailedMoviePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BackButton backButton;
	private DeleteButton delete;
	private PlayButton playButton;
	private LikeButton likeButton;
	private WatchedButton watchedButton;
	private JScrollPane scroll;
	private Movie movie;
	
	private static final Insets BACK_BUTTON_INSETS = new Insets(50, 55, 50, 30);
	private static final Insets MOVIE_TITLE_INSETS = new Insets(50, -100, 50, 0);
	private static final Insets DELETE_BUTTON_INSETS = new Insets(50, 0, 50, 50);
	private static final Insets MOVIE_POSTER_INSETS = new Insets(0, 50, 30, 30);
	private static final Insets PLAY_BUTTON_INSETS = new Insets(30, -155, 30, 50);
	private static final Insets IMDB_RATING_INSETS = new Insets(-110, 18, 30, 0);
	private static final Insets LIKE_BUTTON_INSETS = new Insets(0,-140,10,0);
	private static final Insets WATCHED_BUTTON_INSETS = new Insets(0,-1270,10,0);

	public DetailedMoviePanel(Movie m) throws SourceFilesMissingException, IOException {
		super();

		movie = m;
		backButton = new BackButton();
		delete = new DeleteButton();
		playButton = new PlayButton();
		likeButton = new LikeButton(movie);
		watchedButton = new WatchedButton(movie);
		

		setLayout(new GridBagLayout());
		setBackground(BackgroundPanel.COLOR_BACKGROUND);
		setOpaque(true);
		GridBagConstraints gc = new GridBagConstraints();
		MoviePosterPanel yo = new MoviePosterPanel(movie);
		gc.gridheight = 1;
		gc.gridwidth = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = BACK_BUTTON_INSETS;
		this.add(backButton, gc);
		gc.gridx = 1;
		gc.insets = MOVIE_TITLE_INSETS;
		MovieTitlePanel mtp = new MovieTitlePanel(m);
		this.add(mtp, gc);
		gc.gridx += 1;
		gc.insets = DELETE_BUTTON_INSETS;
		this.add(delete, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		gc.insets = LIKE_BUTTON_INSETS;
		this.add(likeButton,gc);
		gc.gridx +=1;
		gc.insets = WATCHED_BUTTON_INSETS;
		this.add(watchedButton, gc);
		gc.insets = MOVIE_POSTER_INSETS;
		gc.gridx = 0;
		gc.gridy = 2;
		this.add(yo, gc);
		gc.gridy += 1;
		gc.insets = IMDB_RATING_INSETS;
		this.add(new IMDbRatingPanel(movie), gc);
		gc.gridy = 2;
		gc.gridx = 1;
		gc.insets = gc.insets = MOVIE_POSTER_INSETS;
		this.add(new MovieInfosPanel(movie), gc);
		gc.gridy += 1;
		gc.gridx += 1;
		gc.insets = PLAY_BUTTON_INSETS;
		this.add(playButton, gc);
		scroll = new JScrollPane(this);
	}

	public BackButton getBackButton() {
		return backButton;
	}

	public JScrollPane getScrollPane() {
		return scroll;
	}

	public DeleteButton getDeleteButton() {
		return delete;
	}

	public PlayButton getPlayButton() {
		return playButton;
	}

	public static void main(String[] args) throws IOException, NameException, SourceFilesMissingException {
		JFrame window = new JFrame();
		window.setTitle("MovieTime");
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ListMovie lste = new ListMovie("ListMovie");
		Movie film1 = lste.get(0);
		window.add(new DetailedMoviePanel(film1));
		window.setVisible(true);
	}

}