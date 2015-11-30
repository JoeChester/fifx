package de.crossoak.fifx.ui;

import javax.swing.JFrame;

import de.crossoak.fifx.R;

public class FifxTournamentWizard extends CenteredJFrame{

	Fifx ctx;
	
	public FifxTournamentWizard(Fifx ctx) {
		this.ctx = ctx;
		this.setTitle(R.TOURNAMENT_WIZARD + " - " + ctx.getTournamentFile().getAbsolutePath());
		this.setSize(500, 500);
		this.centerLocation();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
