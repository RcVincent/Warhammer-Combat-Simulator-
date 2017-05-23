package CombatSimilator.model;

import java.awt.List;

import java.util.TreeMap;

public class Armory {
	private List weaponList;
	private String weapon;
	private int weaponID;
	private int ArmoryID;
	private int FactionID;
	private TreeMap<String, Double> mapper = new TreeMap<String, Double>();
	
	public void addToArmory(String weapon, Double quantity) {
		this.mapper.put(weapon, quantity);
	}
	
	public void addWeaponToList(String w) {
		weaponList.add(w);
	}
	
	public List viewArmory() {
		return this.weaponList;
	}
	public int getArmorySize() {
		return mapper.size();
	}
	
	public String getWeapon(){
		return weapon;
	}

	public int getWeaponID() {
		return weaponID;
	}

	public void setWeaponID(int weaponID) {
		this.weaponID = weaponID;
	}

	public int getArmoryID() {
		return ArmoryID;
	}

	public void setArmoryID(int armoryID) {
		ArmoryID = armoryID;
	}

	public int getFactionID() {
		return FactionID;
	}

	public void setFactionID(int factionID) {
		FactionID = factionID;
	}
	
}
