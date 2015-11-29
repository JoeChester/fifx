package de.crossoak.fifx.model;

import java.io.Serializable;
import java.util.Date;

public class Match implements Serializable{

	public Player player1;
	public Player player2;
	public int player1_goals;
	public int player2_goals;
	public Date time;
	
}
