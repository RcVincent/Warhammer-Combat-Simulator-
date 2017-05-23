package CombatSimilator.model;

public class Weapon {
	private int Strength;
	private int AP;
	private String name;
	private int armory_id;
	public Weapon() {
		
	}

	public int getStrength() {
		return Strength;
	}

	public void setStrength(int strength) {
		Strength = strength;
	}

	public int getAP() {
		return AP;
	}

	public void setAP(int aP) {
		AP = aP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArmory_id() {
		return armory_id;
	}

	public void setArmory_id(int armory_id) {
		this.armory_id = armory_id;
	}

}
