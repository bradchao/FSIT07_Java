package tw.brad.javase.test;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyGame extends JFrame {
	private MyPanel myPanel;
	
	public MyGame() {
		setLayout(new BorderLayout());
	
		myPanel = new MyPanel();
		add(myPanel, BorderLayout.CENTER);
		
		setVisible(true);
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private class MyPanel extends JPanel {
		private BufferedImage ballImg;
		private Timer timer;
		private int ballX, ballY, myWidth, myHeight, ballW, ballH;
		private LinkedList<BallTask> balls = new LinkedList<>();
		
		public MyPanel() {
			addMouseListener(new MyMouseAdapter());
			timer = new Timer();
			timer.schedule(new RefreshTask(), 0, 16);
			try {
				ballImg = ImageIO.read(new File("dir2/ball.png"));
				ballW = ballImg.getWidth();
				ballH = ballImg.getHeight();
			}catch(Exception e) {
				
			}
		}
		private class RefreshTask extends TimerTask {
			@Override
			public void run() {
				repaint();
			}
		}
		
		private class BallTask extends TimerTask {
			private int dx, dy;
			int ballX, ballY;
			BallTask(int ballX, int ballY){
				dx = dy = 4;
				this.ballX = ballX; this.ballY = ballY;
			}
			@Override
			public void run() {
				if (ballX < 0 || ballX + ballW > myWidth) {
					dx *= -1;
				}
				if (ballY < 0 || ballY + ballH > myHeight) {
					dy *= -1;
				}
				
				ballX += dx;
				ballY += dy;
				//repaint();
			}
		}
		
		
		private class MyMouseAdapter extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				BallTask ball = new BallTask(e.getX()-ballW/2, e.getY()-ballW/2);
				timer.schedule(ball, 1000, 30);
				balls.add(ball);
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			myWidth = getWidth(); myHeight = getHeight();
			
			Graphics2D g2d = (Graphics2D)g;
			for (BallTask ball : balls) {
				g2d.drawImage(ballImg, ball.ballX, ball.ballY, null);
			}
			
		}
	}
	
	public static void main(String[] args) {
		new MyGame();
	}

}
