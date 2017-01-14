import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

class WindowClosingListener extends WindowAdapter {
	
	Display frame;
	
	public WindowClosingListener(Display f ){
		super();
		frame = f;
	}
    
	
  public void windowClosing(WindowEvent w) {
	try {
		Display.getList(frame).save();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    w.getWindow().dispose() ;
    System.exit(0);
  }
  
}