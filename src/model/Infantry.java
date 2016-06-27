package model;

public class Infantry {
	private String WS, BS, Strength, Toughness, Wounds, Initiaitve, Attacks, Leadership;
	String Save;
	private Weapon weapon;
	public Infantry() {
		
	}

	public String getWS() {
		return WS;
	}

	public void setWS(String string) {
		WS = string;
	}

	public String getBS() {
		return BS;
	}

	public void setBS(String string) {
		BS = string;
	}

	public String getStrength() {
		return Strength;
	}

	public void setStrength(String string) {
		Strength = string;
	}

	public String getToughness() {
		return Toughness;
	}

	public void setToughness(String string) {
		Toughness = string;
	}

	public String getWounds() {
		return Wounds;
	}

	public void setWounds(String string) {
		Wounds = string;
	}

	public String getInitiaitve() {
		return Initiaitve;
	}

	public void setInitiaitve(String string) {
		Initiaitve = string;
	}

	public String getAttacks() {
		return Attacks;
	}

	public void setAttacks(String string) {
		Attacks = string;
	}

	public String getSave() {
		return Save;
	}

	public void setSave(String string) {
		Save = string;
	}
	
	public Weapon getWeapon() {
		return weapon; 
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void setStats(String ws, String bs, String s, String t, String w, String i, String a, String ld, String sa) {
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

	public String getLeadership() {
		return Leadership;
	}

	public void setLeadership(String string) {
		this.Leadership = string;
	}
	
	
}
