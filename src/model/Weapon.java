package model;

public class Weapon {
	private int Strength;
	private int AP;
	
	public Weapon(int s, int ap) {
		this.setStrength(s);
		this.setAP(ap);
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
	
	
}
