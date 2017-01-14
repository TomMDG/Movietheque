import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class Display extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private DetailedMoviePanel dmp;
	private JScrollPane dmpScroll;
	private BackgroundPanel back;
	private JScrollPane backScroll;
	private ListMovie lst;
	private CardLayout cl;
	private JPanel content;
	private ListMoviePosterButton lstMPButton;
	private BackButton backButton;
	private DeleteButton deleteButton;
	private AddMovieButton addButton;
	private PlayButton playButton;
	private JTextArea movieTitleArea;
	private Movie currentMovie;
	private MoviePosterButton currentMPButton;
	private String currentFilePath;
	private static JFileChooser FILE_CHOOSER = new JFileChooser("C:\\Users\\Tomich\\Desktop\\Movies");
	private static JFileChooser fileChooser;
	private static final int MIN_WIDTH = 1060;
	private static final int MIN_HEIGHT = 600;

	public Display() {
		super();
		setTitle("MovieTime");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		setLocationRelativeTo(null);
		addWindowListener(new WindowClosingListener(this));
	}

	public Display(ListMovie l) {
		super();
		try {
			setTitle("MovieTime");
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
			setLocationRelativeTo(null);
			addWindowListener(new WindowClosingListener(this));
			
			fileChooser = FILE_CHOOSER;
			cl = new CardLayout();
			content = new JPanel();
			back = new BackgroundPanel();
			addButton = back.getAddButton();
			addButton.addActionListener(this);
			lst = l;

			content.setLayout(cl);
			backScroll = new JScrollPane(back);
			content.add(backScroll, BorderLayout.CENTER);
			back.addMovies(lst);
			lstMPButton = back.getListMPButton();
			for (int i = 0; i < lstMPButton.getSize(); i++) {
				lstMPButton.get(i).addActionListener(this);
			}

			add(content);
		} catch (SourceFilesMissingException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Source Files Missing",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void displayMovieInfos(int i) throws IOException {
		try {
			if (!(movieTitleArea == null)) {
				if (movieTitleArea.getParent() == back) {
					back.remove(movieTitleArea);
				}
			}
			currentMovie = lst.get(i);
			currentMPButton = lstMPButton.get(i);
			if (dmp == null) {
				dmp = new DetailedMoviePanel(currentMovie);
				dmpScroll = dmp.getScrollPane();
				content.add(dmpScroll, BorderLayout.CENTER);
			} else {
				content.remove(dmpScroll);
				dmp = new DetailedMoviePanel(currentMovie);
				dmpScroll = dmp.getScrollPane();
				content.add(dmpScroll, BorderLayout.CENTER);
				content.validate();
			}
			backButton = dmp.getBackButton();
			deleteButton = dmp.getDeleteButton();
			playButton = dmp.getPlayButton();
			backButton.addActionListener(this);
			deleteButton.addActionListener(this);
			playButton.addActionListener(this);
			cl.next(content);
			this.validate();
		} catch (SourceFilesMissingException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Source Files Missing",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void undisplay() {
		cl.first(content);
	}

	public static ListMovie getList(Display f) {
		return f.lst;
	}

	public void addMovie(String filePath) {
		try { 
			currentFilePath = filePath;
			Movie m = Movie.createMovieFromIMDbWithPath(filePath);
			back.addMovie(m);
			lstMPButton = back.getListMPButton();
			lst = back.getList();
			lstMPButton.getLast().addActionListener(this);
			back.validate();
		} catch (NameException e) {
			back.addMovieButtonclicked();
			movieTitleArea = back.getMovieTitleArea();
			movieTitleArea.addKeyListener(this);
			JOptionPane.showMessageDialog(this,
					"It seems like we can't match any movie to the name of this file. Please enter the name of the movie corresponding to this file.",
					"Movie Not Found", JOptionPane.ERROR_MESSAGE);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(this,
					"It seems like you're not connected to the Internet. Please connect yourself if you want to continue.", "No Internet Connection",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,
					"Connection Problem", "No Connection",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void addMovieManually(String title) {
		try {
			Movie m = Movie.createMovieFromIMDb(title, currentFilePath);
			back.addMovie(m);
			lstMPButton = back.getListMPButton();
			lst = back.getList();
			lstMPButton.getLast().addActionListener(this);
			back.remove(movieTitleArea);
			back.validate();
			back.repaint();
			repaint();
		} catch (NameException e) {
			JOptionPane.showMessageDialog(this,
					"It seems like we can't match any movie to the name of this file. Please enter the name of the movie corresponding to this file.",
					"Movie Not Found", JOptionPane.ERROR_MESSAGE);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(this,
					"It seems like you're not connected to the Internet. Please connect yourself if you want to continue.", "No Internet Connection",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteCurrentMovie() {
		back.deleteMovie(currentMovie, currentMPButton);
		back.remove(currentMPButton);
		lst = back.getList();
		cl.first(content);
	}

	public void actionPerformed(ActionEvent evt) {
		for (int i = 0; i < lstMPButton.getSize(); i++) {
			if (evt.getSource() == lstMPButton.get(i)) {
				try {
					displayMovieInfos(i);
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
		}

		if (evt.getSource() == backButton) {
			undisplay();
		}

		if (evt.getSource() == addButton) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				addMovie(file.getPath());
			}
		}

		if (evt.getSource() == deleteButton) {
			deleteCurrentMovie();
		}
		
		if (evt.getSource()==playButton){
			Desktop d = Desktop.getDesktop();
			try {
				d.open(new File(currentMovie.getPath()));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this,
						"The movie's file must have been moved or deleted. Please indicate which file corresponds to this movie.", "File Not Found",
						JOptionPane.ERROR_MESSAGE);

				if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
					currentMovie.modifyFilePath(fileChooser.getSelectedFile().getPath());
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getSource() == movieTitleArea) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				addMovieManually(back.getMovieTitle());
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws IOException, NameException {
		Display frame;
		try {
			ListMovie lste = new ListMovie("ListMovie");
			frame = new Display(lste);
		} catch (SourceFilesMissingException e) {
			ListMovie lste = new ListMovie();
			frame = new Display(lste);
			JOptionPane.showMessageDialog(frame, e.getMessage(), "Source Files Missing", JOptionPane.ERROR_MESSAGE);
		}
		frame.setVisible(true);
	}

}