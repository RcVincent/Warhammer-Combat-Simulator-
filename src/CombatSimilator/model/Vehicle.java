package CombatSimilator.model;

public class Vehicle {
	private int WS, BS, S, FR, SI, R, HP; 
	private Weapon weapon;
	private String name;
	public Vehicle(int ws, int bs, int s, int fr, int si, int r, int hp){
		this.setWS(ws);
		this.setBS(bs);
		this.setS(s);
		this.setFR(fr);
		this.setSI(si);
		this.setR(r);
		this.setHP(hp);
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

	public int getS() {
		return S;
	}

	public void setS(int s) {
		S = s;
	}

	public int getFR() {
		return FR;
	}

	public void setFR(int fR) {
		FR = fR;
	}

	public int getSI() {
		return SI;
	}

	public void setSI(int sI) {
		SI = sI;
	}

	public int getR() {
		return R;
	}

	public void setR(int r) {
		R = r;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
