package net.jmecn.rogue.entity;

public class Item {

	// Item types
	public final static int Weapon = 0;
	public final static int Shield = 1;
	public final static int Helmet = 2;
	public final static int Armour = 3;
	public final static int Boots = 4;
	public final static int Ring = 5;
	public final static int Amulet = 6;
	public final static int Gem = 7;
	public final static int Scroll = 8;
	public final static int Food = 9;
	public final static int Drink = 10;
	public final static int Potion = 11;
	public final static int Junk = 12;
	
	// Item
	public final static int WOOD = 1;
	public final static int IRON = 2;
	public final static int WOOD_SHIELD = 3;
	public final static int WOOD_SWORDS = 4;
	public final static int IRON_SWORDS = 5;
	
	Recipe recipe = null;
	
	private int id;
	private int type;
	private String name;
	private int atk;
	private int def;
	private int hp;
	private int weight;
	private int value;

	private boolean stackable;
	private int stackCount;
	
	private boolean equeped = false;

	private Creature owner = null;
	
	public Item() {
		id = 0;
		type = Junk;
		name = "Unknown";
		atk = 0;
		def = 0;
		hp = 0;
		weight = 1;
		value = 1;
		stackable = false;
		stackCount = 0;
	}

	public Item(int id, int type, String name, int atk, int def, int hp, int weight, int value) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.atk = atk;
		this.def = def;
		this.hp = hp;
		this.weight = weight;
		this.value = value;
		
		this.stackable = false;
		this.stackCount = 0;
	}
	
	public Item(int id, int type, String name, int atk, int def, int hp, int weight, int value, boolean stackable, int stackCount) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.atk = atk;
		this.def = def;
		this.hp = hp;
		this.weight = weight;
		this.value = value;
		this.stackable = stackable;
		this.stackCount = stackCount;
	}

	public int getID() {
		return id;
	}
	
	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getAtk() {
		return atk;
	}

	public int getDef() {
		return def;
	}

	public int getHp() {
		return hp;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}

	public boolean isStackable() {
		return stackable;
	}

	public int getStackCount() {
		return stackCount;
	}

	public boolean isEqueped() {
		return equeped;
	}

	public void use() {
		if (owner == null) {
			return;
		}
		
		switch (type) {
		case Junk:
			break;
		case Food:
		case Drink:
			break;
		case Gem:
			break;
		case Potion:
			owner.hp += hp;
			break;
		case Weapon:
		case Shield:
		case Armour:
		case Helmet:
		case Boots:
		case Ring:
		case Amulet:
			equeped = !equeped;
			break;
		case Scroll:
			break;
		}
	}
}