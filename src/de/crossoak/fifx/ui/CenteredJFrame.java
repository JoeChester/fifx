package de.crossoak.fifx.ui;

import javax.swing.JFrame;

public class CenteredJFrame extends JFrame{
	
	protected void centerLocation() {
		this.setLocationRelativeTo(null);
		int x = this.getLocation().x;
		int y = this.getLocation().y;
		this.setLocation(x - (x / 4), (y - (y / 4)));
	}

}
