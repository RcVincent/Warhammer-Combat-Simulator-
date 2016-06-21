package model;

import java.util.ArrayList;

public class Squad {
	private int squadSize;
	private int morale;
	private ArrayList<Infantry> squad = new ArrayList<Infantry>(); 
	//Infantry in = new Infantry(); 
	
	public Squad(int n) {
		this.squadSize = n;
		for(int i = 0; i < squadSize; i++ ) {
			Infantry in = new Infantry();
			squad.add(in);
		}
	}

	public int getSquadSize() {
		return squadSize;
	}

	public void setSquadSize(int squadSize) {
		this.squadSize = squadSize;
	}
	
	public int getMorale() {
		return morale;
	}
	
	public void setMorale(int n) {
		this.morale = n; 
	}
	public Infantry getInfantry(Infantry i) {
		return i;
	}

}
