
import java.awt.*;
import java.awt.TrayIcon.MessageType;
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
	private JLabel allahuakbar;
	private JButton start;
	private JBomb bomb;
	private Timer timer;

	private int time = 50;
	private JButton abort;

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
		super.setSize(new Dimension(300, 400));
		super.setTitle("Bombe");
		super.setResizable(false);

		scroll_bar = new JScrollBar(JScrollBar.VERTICAL, 50, 10, 0, 110);
		scroll_bar.addAdjustmentListener(new ScrollListener());
		contentPane.add(scroll_bar, BorderLayout.EAST);

		control_panel = new JPanel(new GridLayout(5, 1));
		contentPane.add(control_panel, BorderLayout.SOUTH);

		allahuakbar = new JLabel("ALLAHU AKBAR!!!", SwingConstants.CENTER);
		allahuakbar.setVisible(false);
		control_panel.add(allahuakbar);

		status = new JLabel("Time = 5.0s", SwingConstants.CENTER);
		status.setAlignmentX(0.5f);
		control_panel.add(status);

		start = new JButton("START");
		start.addActionListener(new StartListener());
		control_panel.add(start);

		abort = new JButton("ABORT");
		abort.addActionListener(new AbortListener());
		control_panel.add(abort);

		JButton reset = new JButton("RESET");
		reset.addActionListener(new ResetListener());
		control_panel.add(reset);

		bomb = new JBomb();
		bomb.updateImage(50);
		bomb.setStartTime(50);
		contentPane.add(bomb, BorderLayout.CENTER);

		timer = new Timer(100, new TimerListener());

	}

	private class AbortListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			timer.stop();

		}
	}

	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			time--;

			bomb.updateImage(time);
			status.setText("Time = " + (time / 10.0) + "s");
			scroll_bar.setValue(100 - time);

			if (time == 0) {
				timer.stop();
				allahuakbar.setVisible(true);
				scroll_bar.setEnabled(false);
				abort.setEnabled(false);
				start.setEnabled(false);
			}

		}

	}

	private class StartListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (time != 0) {
				bomb.setStartTime(time);
				bomb.updateImage(time);
				timer.start();
			}
		}

	}

	private class ScrollListener implements AdjustmentListener {

		public void adjustmentValueChanged(AdjustmentEvent e) {

			time = 100 - e.getValue();
			status.setText("Time = " + (time / 10.0) + "s");
			
			bomb.updateImage(time);

		}

	}
	
	private class ResetListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			timer.stop();
			
			time = 50;
			status.setText("Time = " + (time / 10.0) + "s");
			allahuakbar.setVisible(false);
			scroll_bar.setEnabled(true);
			abort.setEnabled(true);
			start.setEnabled(true);
			scroll_bar.setValue(100 - time);
			
			bomb.setStartTime(50);
			bomb.updateImage(50);
			
		}
		
	}

}
