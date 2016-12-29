package net.jmecn.rogue.core;

import java.util.ArrayList;
import java.util.List;

import net.jmecn.rogue.entity.Creature;
import net.jmecn.rogue.entity.Item;
import net.jmecn.rogue.entity.ItemDatabase;
import net.jmecn.rogue.entity.Recipe;

public class Craft {

	private List<Recipe> recipes;
	public Craft() {
		recipes = new ArrayList<Recipe>();
		// 木剑 = 木头*3
		Recipe recipe = new Recipe();
		recipe.getRequires().put(Item.WOOD, 3);
		recipe.setResult(Item.WOOD_SWORDS);
		recipes.add(recipe);
		
		// 铁剑 = 木头*1 + 铁矿*2
		recipe = new Recipe();
		recipe.getRequires().put(Item.WOOD, 1);
		recipe.getRequires().put(Item.IRON, 2);
		recipe.setResult(Item.IRON_SWORDS);
		recipes.add(recipe);
		
		// 盾牌 = 木头 * 3
		recipe = new Recipe();
		recipe.getRequires().put(Item.WOOD, 3);
		recipe.setResult(Item.WOOD_SHIELD);
		recipes.add(recipe);
	}
	
	public void craft(Recipe recipe, Creature player) {
		List<Item> items = player.getItems();
		
		// 检测道具数量
		
		if (recipe.check(items)) {
			Integer result = recipe.getResult();
			
			// 移除
			recipe.remove(items);
			
			// 合成道具
			Item item = ItemDatabase.get(result);
			items.add(item);
		}
		
	}
}
