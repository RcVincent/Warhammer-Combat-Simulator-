package model;

import java.util.ArrayList;

public class Search {
	ArrayList<Infantry> infantryList = new ArrayList<Infantry>(); 
	
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
}
