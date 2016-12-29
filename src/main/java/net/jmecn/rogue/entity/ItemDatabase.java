package net.jmecn.rogue.entity;

public class ItemDatabase {
	public final static String WOOD = "木头";
	public final static String WOOD_SHIELD = "木盾";
	public final static String IRON = "铁矿";
	public final static String IRON_SWORDS = "铁剑";

	public ItemDatabase() {
		// TODO Auto-generated constructor stub
	}

	public static Item[] items = {
		new Item(Item.WOOD, Item.Junk, WOOD, 0, 0, 0, 1, 1),// 0
		new Item(Item.IRON, Item.Junk, IRON, 0, 0, 0, 3, 1),// 1
		new Item(Item.IRON_SWORDS, Item.Weapon, IRON_SWORDS, 0, 0, 0, 1, 2),// 2
		new Item(Item.WOOD_SHIELD, Item.Shield, WOOD_SHIELD, 0, 2, 0, 5, 10),// 2
	};
	
	public static Item get(int id) {
		if (id >= 1 && id <= items.length) {
			return items[id-1];
		}
		
		return null;
	}
	
	public static Item query(String name) {
		Item item = null;
		for(int i=0; i<items.length; i++) {
			if (items[i].getName() == name) {
				item = items[i];
				break;
			}
		}
		return item;
	}
}
