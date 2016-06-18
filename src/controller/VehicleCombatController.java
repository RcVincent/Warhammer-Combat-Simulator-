package controller;

import java.util.Random;
//import model.Infantry;
import model.Vehicle;

public class VehicleCombatController {
	private Random rand = new Random(); 
	private Vehicle v;
	private Vehicle v2; 
	
	public boolean isShootingHit() {
		double hit = rand.nextInt(6); 
		int toHit = 0;
		if(v.getBS() == 1 || v2.getBS() == 1) {
			toHit = 6; 
		}
		else if(v.getBS() == 2 || v2.getBS() == 2) {
			toHit = 5; 
		}
		else if(v.getBS() == 3 || v2.getBS() == 3) {
			toHit = 4; 
		}
		else if(v.getBS() == 4 || v2.getBS() == 4) {
			toHit = 3; 
		}
		else if(v.getBS() >= 5 || v2.getBS() >= 5) {
			toHit = 2; 
		}
		
		if(hit >= toHit) {
			return true;
		}
		else {return false;}
	}
	
	
	//Need to work out what armor facing is affected. 
	
}
