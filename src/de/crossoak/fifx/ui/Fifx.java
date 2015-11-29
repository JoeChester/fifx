package de.crossoak.fifx.ui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Fifx{
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	try {
	    				setSystemLookAndFeel();
	    			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
	    					| UnsupportedLookAndFeelException e) {
	    				e.printStackTrace();
	    			}
	            	Fifx fifx = new Fifx();
	    			new FifxWelcomeDialog(fifx);
	            }
	        });
		}
	    
	public static void setSystemLookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}
}
