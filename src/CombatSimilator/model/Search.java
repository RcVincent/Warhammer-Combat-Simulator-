package model;

import java.util.ArrayList;

public class Search {
	ArrayList<Infantry> infantryList = new ArrayList<Infantry>(); 
	ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>(); 
	ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
	public Search() {
		
	}
	
	public ArrayList<Infantry> searchInfantryByName(String name) {
		ArrayList<Infantry> resultList = new ArrayList<Infantry>();
		for(int i = 0; i < infantryList.size(); i++) {
			if(infantryList.get(i).getName().equals(name)) {
				resultList.add(infantryList.get(i));
			}
		}
		
		return resultList;
	}
	
	public ArrayList<Vehicle> searchVehicleByName(String name) {
		ArrayList<Vehicle> resultList = new ArrayList<Vehicle>();
		for(int i = 0; i < vehicleList.size(); i++) {
			if(vehicleList.get(i).getName().equals(name)) {
				resultList.add(vehicleList.get(i));
			}
		}
		
		return resultList;
	}
	
	public ArrayList<Weapon> searchWeaponByName(String name) {
		ArrayList<Weapon> resultList = new ArrayList<Weapon>();
		for(int i = 0; i < weaponList.size(); i++) {
			if(weaponList.get(i).getName().equals(name)) {
				resultList.add(weaponList.get(i));
			}
		}
		
		return resultList;
	}
}
