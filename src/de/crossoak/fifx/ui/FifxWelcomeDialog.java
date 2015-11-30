package de.crossoak.fifx.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.crossoak.fifx.R;

public class FifxWelcomeDialog extends CenteredJFrame implements ActionListener{
	
	BufferedImage icon;
	JLabel banner;
	JButton createButton;
	JButton loadButton;
	Fifx ctx;
	
	JFileChooser fc;
	
	public FifxWelcomeDialog(Fifx ctx) {
		super();
		this.ctx = ctx;
		
		this.banner = new JLabel(new ImageIcon(R.BANNER));
		this.setLayout(new BorderLayout());
		this.createButton = new JButton(R.CREATE_TOURNAMENT);
		this.loadButton = new JButton(R.LOAD_TOURNAMENT);
		
		this.fc = new JFileChooser();
		
		createButton.addActionListener(this);
		loadButton.addActionListener(this);
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(createButton);
		buttonPanel.add(loadButton);
		
		this.add(buttonPanel, BorderLayout.CENTER);
		
		this.add(this.banner, BorderLayout.NORTH);
		this.setTitle(R.FIFX_TITLE);
		this.setSize(500, 285);
		this.setResizable(false);
		this.centerLocation();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.loadButton) {
            int returnVal = fc.showOpenDialog(FifxWelcomeDialog.this);
 
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                System.out.println(file.getAbsolutePath());
            } else {
            	System.out.println("open command cancelled");
            }

        } else if (e.getSource() == this.createButton) {
            int returnVal = fc.showSaveDialog(FifxWelcomeDialog.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String path = file.getAbsolutePath() + ".fifx";
               	File newfile = new File(path);
               	try {
					newfile.createNewFile();
					this.ctx.setTournamentFile(newfile);
					new FifxTournamentWizard(this.ctx);
					this.setVisible(false);
					this.dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            } else {
                System.out.println("Save command cancelled by user.");
            }
        }
		
	}
	
}
