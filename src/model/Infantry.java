package model;

public class Infantry {
	private int WS, BS, Strength, Toughness, Wounds, Initiaitve, Attacks, Save;
	private Weapon weapon;
	public Infantry(int ws, int bs, int s, int t, int w, int i, int a, int sa) {
		this.WS = ws;
		this.BS = bs; 
		this.Strength = s;
		this.Toughness = t; 
		this.Wounds = w;
		this.Initiaitve = i;
		this.Attacks = a;
		this.Save = sa; 
	}

	public int getWS() {
		return WS;
	}

	public void setWS(int wS) {
		WS = wS;
	}

	public int getBS() {
		return BS;
	}

	public void setBS(int bS) {
		BS = bS;
	}

	public int getStrength() {
		return Strength;
	}

	public void setStrength(int strength) {
		Strength = strength;
	}

	public int getToughness() {
		return Toughness;
	}

	public void setToughness(int toughness) {
		Toughness = toughness;
	}

	public int getWounds() {
		return Wounds;
	}

	public void setWounds(int wounds) {
		Wounds = wounds;
	}

	public int getInitiaitve() {
		return Initiaitve;
	}

	public void setInitiaitve(int initiaitve) {
		Initiaitve = initiaitve;
	}

	public int getAttacks() {
		return Attacks;
	}

	public void setAttacks(int attacks) {
		Attacks = attacks;
	}

	public int getSave() {
		return Save;
	}

	public void setSave(int save) {
		Save = save;
	}
	
	public Weapon getWeapon() {
		return weapon; 
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	
}
