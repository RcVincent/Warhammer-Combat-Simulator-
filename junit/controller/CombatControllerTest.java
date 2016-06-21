package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Infantry;

public class CombatControllerTest {
	CombatController c = new CombatController();
	
	@Before
	public void setUp() {
		
		Infantry SpaceMarine = new Infantry();
		Infantry NecronWarrior = new Infantry();
		
		SpaceMarine.setStats(4, 4, 4, 4, 1, 4, 1, 3);
		NecronWarrior.setStats(4, 4, 4, 4, 1, 2, 1, 4);
		
		
	}
	
	@Test
	public void InitiativeTest() {
		Boolean One = c.p1hasInitiative(SpaceMarine);
		Boolean two = c.p2hasInitiative();
		
		assertTrue(One, true);
		assertFalse(two, true);
	}
	
	@Test
	public void ToHitTest() {
		assertEquals(3, )
	}
}


