package tw.brad.javase.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tw.brad.tools.MyDrawer;

public class MySign extends JFrame{
	private MyDrawer myDrawer;
	private JButton clear, undo, redo, saveJPEG, saveObj, loadObj, chColor;
	
	public MySign() {
		super("My Sign");
		setLayout(new BorderLayout());
		
		myDrawer = new MyDrawer();
		add(myDrawer, BorderLayout.CENTER);
		
		JPanel topLine = new JPanel(new FlowLayout());
		clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myDrawer.clear();
			}
		});
		undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					myDrawer.undo();
				}catch(Exception e2) {
					
				}
			}
		});
		redo = new JButton("Redo");
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myDrawer.redo();
			}
		});
		saveJPEG = new JButton("Save JPEG");
		saveJPEG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myDrawer.saveJPEG(new File("dir2/brad.jpg"));
			}
		});
		saveObj = new JButton("Save Obj");
		saveObj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					myDrawer.saveObj(new File("dir2/brad.drawer"));
				}catch(Exception e2) {
					
				}
			}
		});
		loadObj = new JButton("Load Obj");
		loadObj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					myDrawer.loadObj(new File("dir2/brad.drawer"));
				}catch(Exception e2) {
					
				}
			}
		});
		chColor = new JButton("Change Color");
		chColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeColor();
			}
		});
		
		topLine.add(clear); topLine.add(undo); topLine.add(redo);
		topLine.add(saveJPEG);topLine.add(saveObj);topLine.add(loadObj);
		topLine.add(chColor);
		add(topLine, BorderLayout.NORTH);
		
		setVisible(true);
		setSize(800, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void changeColor() {
		Color newColor = JColorChooser.showDialog(this, 
				"change color", myDrawer.getLineColor());
		if (newColor != null) {
			myDrawer.setLineColor(newColor);
		}
		
	}
	
	public static void main(String[] args) {
		new MySign();
	}

}
