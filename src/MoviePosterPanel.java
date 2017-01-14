import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class MoviePosterPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	BufferedImage poster;
	
	public MoviePosterPanel(Movie m){
		super();
		poster = m.getPoster();
		Dimension d = new Dimension(poster.getWidth(),poster.getHeight());
		this.setOpaque(false);
		this.setSize(d);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(poster, 0, 0, null);
    }
	
}