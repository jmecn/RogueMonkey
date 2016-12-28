package net.jmecn.rogue.entity;

public class Item {

	private ItemTypes type;

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
		type = ItemTypes.Junk;
		name = "Unknown";
		atk = 0;
		def = 0;
		hp = 0;
		weight = 1;
		value = 1;
		stackable = false;
		stackCount = 0;
	}

	public Item(ItemTypes type, String name, int atk, int def, int hp, int weight, int value) {
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
	
	public Item(ItemTypes type, String name, int atk, int def, int hp, int weight, int value, boolean stackable, int stackCount) {
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

	public ItemTypes getType() {
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