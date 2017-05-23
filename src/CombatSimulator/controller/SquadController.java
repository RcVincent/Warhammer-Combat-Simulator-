package CombatSimulator.controller;

import java.util.Random;

import CombatSimilator.model.Infantry;
import CombatSimilator.model.Squad;

public class SquadController {
	Random rand = new Random(); 
	int attacksweep = 0;
	int defsweep = 0;
	CombatController con = new CombatController();
	Infantry i1;
	Infantry i2;
	
	Squad s1 = new Squad(10); 
	Squad s2 = new Squad(15);
	
	
	public void battle() {
		if(con.p1hasInitiative(i1, i2)) {
			for(int i = 0; i < s1.getSquadSize(); i++) {
				if(con.takesWound(i1, i2)) {
					s2.setSquadSize(s2.getSquadSize() - 1);
					if(con.isRunning(i1, i2)) {
						attacksweep = rand.nextInt(6);
						defsweep = rand.nextInt(6);
						if(attacksweep + i1.getInitiaitve() > defsweep + i2.getInitiaitve() ) {
							s2.setSquadSize(0);
							break;
						}
					}
				}
			}
		
		}
		else if(con.p2hasInitiative(i2, i1)) {
			if(con.takesWound(i2, i1)) {
				s1.setSquadSize(s1.getSquadSize() - 1);
				if(con.isRunning(i2, i1)) {
					attacksweep = rand.nextInt(6);
					defsweep = rand.nextInt(6);
					if(attacksweep + i2.getInitiaitve() > defsweep + i1.getInitiaitve() ) {
						s1.setSquadSize(0);
					}
				}
				}
			}
		}
	}
	


