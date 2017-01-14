import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextArea;

class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private GridBagConstraints gbc;
	private int compteur;
	private ListMovie lst;
	private ListMoviePosterButton lstMPButton;
	private AddMovieButton addButton;
	private JTextArea movieTitleArea;
	private static final Insets ADD_BUTTON_INSETS = new Insets(0, -105, 0, 55);
	private static final Insets TITLE_AREA_INSETS = new Insets(-480, -160, 0, 0);
	private static final Dimension TITLE_AREA_DIMENSION = new Dimension(150, 17);

	static final Color COLOR_BACKGROUND = new Color(2, 31, 47);

	public BackgroundPanel() throws SourceFilesMissingException {
		super();

		setLayout(new GridBagLayout());
		setBackground(COLOR_BACKGROUND);
		setOpaque(true);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.insets = ADD_BUTTON_INSETS;
		addButton = new AddMovieButton();
		add(addButton, gbc);

		gbc.gridx += 1;
		gbc.insets = new Insets(0, 0, 0, 0);

		compteur = 0;
		lst = new ListMovie();
		lstMPButton = new ListMoviePosterButton();
	}

	public AddMovieButton getAddButton() {
		return addButton;
	}

	public GridBagConstraints getGbc() {
		return gbc;
	}

	public void addMovieButtonclicked() {
		int j = gbc.gridx;
		int i = gbc.gridy;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = TITLE_AREA_INSETS;
		movieTitleArea = new JTextArea();
		movieTitleArea.setPreferredSize(TITLE_AREA_DIMENSION);
		add(movieTitleArea, gbc);
		gbc.gridx = j;
		gbc.gridy = i;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.validate();
	}

	public JTextArea getMovieTitleArea() {
		return movieTitleArea;
	}

	public String getMovieTitle() {
		return movieTitleArea.getText();
	}

	public void addMovie(Movie m) {
		lstMPButton.addMovie(m);
		lst.add(m);
		add(lstMPButton.getLast(), gbc);
		compteur += 1;
		if (compteur % 5 == 0) {
			gbc.gridx = 1;
			gbc.gridy += 1;
		} else
			gbc.gridx += 1;
		this.validate();
	}

	public void addMovies(ListMovie l) {
		for (int i = 0; i < l.getSize(); i++) {
			addMovie(l.get(i));
		}
	}

	public void deleteMovie(Movie m, MoviePosterButton mpb) {
		lst.delete(m);
		lstMPButton.delete(mpb);
	}

	public ListMovie getList() {
		return lst;
	}

	public ListMoviePosterButton getListMPButton() {
		return lstMPButton;
	}

}