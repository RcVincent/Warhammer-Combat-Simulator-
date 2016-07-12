package model;

public class Faction {
	private int faction_id, armory_id;
	private String faction_name;
	
	public Faction() {
		
	}

	public int getFaction_id() {
		return faction_id;
	}

	public void setFaction_id(int faction_id) {
		this.faction_id = faction_id;
	}

	public String getFaction_name() {
		return faction_name;
	}

	public void setFaction_name(String faction_name) {
		this.faction_name = faction_name;
	}

	public int getArmory_id() {
		return armory_id;
	}

	public void setArmory_id(int armory_id) {
		this.armory_id = armory_id;
	}
	
	
}
