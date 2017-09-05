import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class JBomb extends JPanel {
	
	private Image[] images;
	private int time;
	private int start_time;
	
	public JBomb() {
		
		super();
		
		images = new Image[4];
		
		for (int n=1;n<=4;n++) {
			images[n-1] = Toolkit.getDefaultToolkit().createImage("res/bomb" + n + ".png");
		}
		
	}
	
	public void setStartTime(int start_time) {
		this.start_time = start_time;
	}
	
	public void updateImage(int time) {
		this.time = time;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		int n = (int) ((start_time-time)/(start_time/3.0));
		
		if (time == 0) {
			n = 3;
		}
		
		Image img = images[n];
		
		g.drawImage(img, 0, 0, null);
		
		super.repaint();
	}
	
}
