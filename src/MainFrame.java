
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	private JPanel contentPane;
	private JPanel control_panel;
	
	private JScrollBar scroll_bar;
	private JLabel status;
	private JBomb bomb;
	private Timer timer;
	
	private int time = 50;
	
	public MainFrame() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			FrameInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Component initialization.
	 *
	 * @throws java.lang.Exception
	 */
	private void FrameInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());
		super.setSize(new Dimension(300, 350));
		super.setTitle("Bombe");
		super.setResizable(false);
		
		scroll_bar = new JScrollBar(JScrollBar.VERTICAL, 50, 10, 0, 110);
		scroll_bar.addAdjustmentListener(new ScrollListener());
		contentPane.add(scroll_bar, BorderLayout.EAST);
		
		control_panel = new JPanel(new GridLayout(2, 1));
		contentPane.add(control_panel, BorderLayout.SOUTH);
		
		status = new JLabel("Time = 5.0s", SwingConstants.CENTER);
		status.setAlignmentX(0.5f);
		control_panel.add(status);
		
		JButton start = new JButton("START");
		start.addActionListener(new StartListener());
		control_panel.add(start);
		
		bomb = new JBomb();
		bomb.updateImage(50);
		bomb.setStartTime(50);
		contentPane.add(bomb, BorderLayout.CENTER);
		
		timer = new Timer(100, new TimerListener());
		
	}
	
	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			time--;
			
			bomb.updateImage(time);
			status.setText("Time = " + (time/10.0) + "s");
			scroll_bar.setValue(100-time);
			
			if (time == 0) {
				timer.stop();
			}
			
		}
		
	}
	
	private class StartListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			bomb.setStartTime(time);
			timer.start();
		}
		
	}
	
	private class ScrollListener implements AdjustmentListener {

		public void adjustmentValueChanged(AdjustmentEvent e) {
			
			time = 100-e.getValue();
			status.setText("Time = " + (time/10.0) + "s");
			
		}
		
	}
	
}
