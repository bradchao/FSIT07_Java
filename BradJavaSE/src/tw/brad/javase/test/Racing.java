package tw.brad.javase.test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Racing extends JFrame {
	private JButton go;
	private JLabel[] lanes = new JLabel[8];
	
	public Racing() {
		super("Racing");
		setLayout(new GridLayout(9,1));
		
		go = new JButton("Go!");
		go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				go();
			}
		});
		add(go);
		
		for (int i=0; i<lanes.length; i++) {
			lanes[i] = new JLabel();
			lanes[i].setText((i+1) + ".");
			add(lanes[i]);
		}
		
		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private Car[] cars = new Car[8];
	private int rank;
	
	private void go() {
		go.setEnabled(false);
		rank = 0;
		// new round
		for (int i=0; i<lanes.length; i++) {
			lanes[i].setText((i+1) + ".");
		}
		for (int i=0; i<cars.length; i++) {
			cars[i] = new Car(i);
		}
		for (int i=0; i<cars.length; i++) {
			cars[i].start();
		}
	}
	
	private class Car extends Thread {
		private int lane;
		Car(int lane){this.lane = lane;}
		@Override
		public void run() {
			for (int i=0; i<100; i++) {
				if (i==99) {
					lanes[lane].setText(lanes[lane].getText() + ">" + ++rank);
					stopRound();
				}else {
					lanes[lane].setText(lanes[lane].getText() + ">");
				}
				try {
					Thread.sleep(10 + (int)(Math.random()*200));
				}catch(Exception e) {
					break;
				}
			}
		}
	}
	
	private void stopRound() {
		for (int i=0; i<cars.length; i++) {
			cars[i].interrupt();
		}
		go.setEnabled(true);
	}
	
	
	public static void main(String[] args) {
		new Racing();
	}

}
