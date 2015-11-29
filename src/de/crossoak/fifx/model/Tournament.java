package de.crossoak.fifx.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Tournament implements Serializable{
	
	public ArrayList<Player> participants;
	public ArrayList<Match> hinrunde; //first part of the season
	public ArrayList<Match> rueckrunde; //second part of the season
	
	public Tournament(){
		this.participants = new ArrayList<Player>();
		this.hinrunde = new ArrayList<Match>();
		this.rueckrunde = new ArrayList<Match>();
	}

}
