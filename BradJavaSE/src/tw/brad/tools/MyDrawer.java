package tw.brad.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JPanel;

public class MyDrawer extends JPanel {
	private LinkedList<LinkedList<HashMap<String, Integer>>> lines, recycle;
	
	public MyDrawer() {
		setBackground(Color.YELLOW);
		
		lines = new LinkedList<>();
		recycle = new LinkedList<>();
		
		
		MyMouseListener listener = new MyMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLUE);
		g2d.setStroke(new BasicStroke(4));
		
		for (LinkedList<HashMap<String, Integer>> line : lines) {
			for (int i=1; i<line.size(); i++) {
				HashMap<String, Integer> p0 = line.get(i-1);
				HashMap<String, Integer> p1 = line.get(i);
				g2d.drawLine(p0.get("x"), p0.get("y"), p1.get("x"), p1.get("y"));
			}
		}
		
		
		
	}
	
	public void clear() {
		lines.clear();
		repaint();
	}
	
	public void undo() throws Exception {
		if (lines.size()>0) {
			recycle.add(lines.removeLast());
			repaint();
		}else {
			throw new Exception();
		}
	}
	
	public void redo() {
		if (recycle.size()>0) {
			lines.add(recycle.removeLast());
			repaint();
		}
	}
	
	private class MyMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			recycle.clear();
			LinkedList<HashMap<String, Integer>> line = new LinkedList<>();
			lines.add(line);
			
			HashMap<String, Integer> point = new HashMap<>();
			point.put("x", e.getX());
			point.put("y", e.getY());
			line.add(point);
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			super.mouseDragged(e);
			HashMap<String, Integer> point = new HashMap<>();
			point.put("x", e.getX());
			point.put("y", e.getY());
			lines.getLast().add(point);
			repaint();
		}
		

	}
	
	private class Point {
		private int x, y;
		void setXY(int x, int y) {
			this.x = x; this.y = y;
		}
		int getX() {return x;}
		int getY() {return y;}
	}
	
	
}
