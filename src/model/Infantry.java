package model;

public class Infantry {
	private int WS, BS, Strength, Toughness, Wounds, Initiaitve, Attacks, leadership, Save;
	private Weapon weapon;
	public Infantry() {
		
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
	
	public void setStats(int ws, int bs, int s, int t, int w, int i, int a, int ld, int sa) {
		this.setWS(ws);
		this.setBS(bs);
		this.setStrength(s);
		this.setToughness(t);
		this.setWounds(w);
		this.setInitiaitve(i);
		this.setAttacks(a);
		this.setLeadership(ld);
		this.setSave(sa);
	}

	public int getLeadership() {
		return leadership;
	}

	public void setLeadership(int leadership) {
		this.leadership = leadership;
	}
	
	
}
