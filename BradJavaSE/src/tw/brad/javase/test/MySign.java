package tw.brad.javase.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import tw.brad.tools.MyDrawer;

public class MySign extends JFrame{
	private MyDrawer myDrawer;
	
	public MySign() {
		super("My Sign");
		setLayout(new BorderLayout());
		
		myDrawer = new MyDrawer();
		add(myDrawer, BorderLayout.CENTER);
		
		
		setVisible(true);
		setSize(800, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MySign();
	}

}
