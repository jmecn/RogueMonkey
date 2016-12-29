package net.jmecn.rogue.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Recipe {
	
	private HashMap<Integer, Integer> requires;

	private Integer result;
	
	public Recipe() {
		setRequires(new HashMap<Integer, Integer>());
		setResult(null);
	}

	public boolean check(List<Item> items) {
		Set<Integer> keys = getRequires().keySet();
		for(Integer key : keys) {
			Integer count = getRequires().get(key);
			
			int sum=0;
			for(Item item : items) {
				if (item.getID() == key) {
					sum++;
				}
			}
			
			if (sum < count) {
				return false;
			}
		}
		
		
		return true;
	}
	
	public boolean remove(List<Item> items) {
		Set<Integer> keys = getRequires().keySet();
		for(Integer key : keys) {
			Integer count = getRequires().get(key);
			
			int sum=0;
			Iterator<Item> it = items.iterator();
			while(it.hasNext()) {
				Item item = it.next();
				if (item.getID() == key) {
					if (sum < count) {
						items.remove(item);
						sum++;
					}
				}
			}
		}
		
		
		return true;
	}

	public HashMap<Integer, Integer> getRequires() {
		return requires;
	}

	public void setRequires(HashMap<Integer, Integer> requires) {
		this.requires = requires;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
}
