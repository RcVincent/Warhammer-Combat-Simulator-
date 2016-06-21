package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Infantry;

public class CombatControllerTest {
	CombatController c = new CombatController();
	Infantry SpaceMarine = new Infantry();
	Infantry NecronWarrior = new Infantry();
	
	@Before
	public void setUp() {
		SpaceMarine.setStats(4, 4, 4, 4, 1, 4, 1, 3);
		NecronWarrior.setStats(4, 4, 4, 4, 1, 2, 1, 4);
		//will add weapons later
	}
	
	@Test
	public void InitiativeTest() {
		Boolean One = c.p1hasInitiative(SpaceMarine, NecronWarrior);
		Boolean two = c.p2hasInitiative(SpaceMarine, NecronWarrior);
		
		assertTrue(One);
		assertFalse(two);
	}
	
	//@Test
	public void ToHitTest() {
			
	}
}


