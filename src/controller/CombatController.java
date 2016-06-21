package controller;

import java.util.Random;

import model.Infantry;
//import model.Vehicle;


//It is important to note that the math is based on a 6 sided dice system
//with a result of a 1 always being a "failure". The math/number logic here
//is based on that system.
public class CombatController {
	Random rand = new Random();
	//private Infantry in;
	//private Infantry in2; 
	
	public boolean p1hasInitiative(Infantry in, Infantry in2) {
		if(in.getInitiaitve() > in2.getInitiaitve()) {
			return true;
		}
		else {return false;}
	}
	
	public boolean p2hasInitiative(Infantry in, Infantry in2) {
		if(in2.getInitiaitve() > in.getInitiaitve()) {
			return true;
		}
		else {return false;}
	}
	
	
	public boolean isShootingHit(Infantry in, Infantry in2) {
		double hit = rand.nextInt(6); 
		int toHit = 0;
		if(in.getBS() == 1 || in2.getBS() == 1) {
			toHit = 6; 
		}
		else if(in.getBS() == 2 || in2.getBS() == 2) {
			toHit = 5; 
		}
		else if(in.getBS() == 3 || in2.getBS() == 3) {
			toHit = 4; 
		}
		else if(in.getBS() == 4 || in2.getBS() == 4) {
			toHit = 3; 
		}
		else if(in.getBS() >= 5 || in2.getBS() >= 5) {
			toHit = 2; 
		}
		
		if(hit >= toHit) {
			return true;
		}
		else {return false;}
	}
	
	public boolean isMeleeHit(Infantry in, Infantry in2) {
		double hit = rand.nextInt(6);
		int toHit = 0;
		if(in2.getWS() > in.getWS() || in.getWS() > in2.getWS()) {
			toHit = 3;
		}
		else if(in2.getWS() < in.getWS() || in.getWS() < in2.getWS()) {
			toHit = 5; 
		}
		else if(in2.getWS() == in.getWS() || in.getWS() == in2.getWS()) {
			toHit = 4; 
		}
		
		if(hit >= toHit) {
			return true;
		}
		else {return false;}
	}
	
	public boolean isWound(Infantry in, Infantry in2) {
		//determining if an attack would cause a wound
		int toWoundRoll = rand.nextInt(6);
		int toWound = 0;
		if(in.getStrength() == in2.getToughness() || in2.getStrength() == in.getToughness() || in.getWeapon().getStrength() == in2.getToughness()  || in2.getWeapon().getStrength() == in.getToughness())  {
			toWound = 4; 
		}
		else if(in.getStrength() - in2.getToughness() == 1 || in2.getStrength() - in.getToughness() == 1 || in.getWeapon().getStrength() - in2.getToughness() > 1 || in2.getWeapon().getStrength() - in.getToughness() > 1) {
			toWound = 3; 
		}
		
		else if(in.getStrength() - in2.getToughness() >= 2 || in2.getStrength() - in.getToughness() >= 2 || in.getWeapon().getStrength() - in2.getToughness() >= 2 || in2.getWeapon().getStrength() - in.getToughness() >= 2) {
			toWound = 2; 
		}
		
		else if(in.getStrength() - in2.getToughness() == -1 || in2.getStrength() - in.getToughness() == -1 || in.getWeapon().getStrength() - in2.getToughness() <= -1 || in2.getWeapon().getStrength() - in.getToughness() <= -1) {
			toWound = 5; 
		}
		else if(in.getStrength() - in2.getToughness() == 2 || in2.getStrength() - in.getToughness() == 2 || in.getWeapon().getStrength() - in2.getToughness() <= -2 || in2.getWeapon().getStrength() - in.getToughness() <= -2) {
			toWound = 6; 
		}
		else if(in.getStrength() - in2.getToughness() <= 3 || in2.getStrength() - in.getToughness() <= 3 || in.getWeapon().getStrength() - in2.getToughness() <= -3 || in2.getWeapon().getStrength() - in.getToughness() <= -3) {
			toWound = 0; 
		}
		
		if(toWoundRoll >= toWound) {
			return true;
		}
		else {return false;} 
}
	
	public boolean saveIgnored(Infantry in, Infantry in2) {
		//if a weapons AP value ignores a units armor. 
		int ap = in.getWeapon().getAP();
		int ap2 = in2.getWeapon().getAP();
		
		if(ap <= in2.getSave() || ap2 <= in.getSave()) {
			return true;
		}
		else {return false;}
	}
	
	//These two methods could be combined into one. 
	
	public boolean isSaved(Infantry in, Infantry in2) {
		//if a unit gets its designated armor save
		int saveRoll = rand.nextInt(6);
		if(!saveIgnored(in, in2) && saveRoll >= in.getSave()) {
			return true;
		}
		else {return false;} 
		
	}
	
	public boolean takesWound(Infantry in, Infantry in2) {
		//if all of these methods pass and the saves do not, it is a wound 
		if(isShootingHit(in, in2)) {
			if(isWound(in, in2)) {
				if(!saveIgnored(in, in2)){
					if(!isSaved(in, in2)) {
						return true;
					}
				}
			}
		}
		else if (isMeleeHit(in, in2)) {
			if(isWound(in, in2)) {
				if(!saveIgnored(in, in2)){
					if(!isSaved(in, in2)) {
						return true;
					}
				}
			}
		}
		else {return false;};
		
		//if the method runs this far its a false result
		return false;
	}
}
