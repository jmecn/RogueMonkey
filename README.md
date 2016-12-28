RogueMonkey
===========

This is a roguelike game, also a survive game.

## System

### Basic

* Hearth
Player die when hp = 0

* Hunger & Thirst
Player die when hunger or thirst = 0

Hunger track: Famished, Starving, Hungry, Not Hungry, Satiated, Bloated, Choking. 
Thirst track: Parched, Dehydrated, Thirsty, Not Thirsty, Satiated, Hydrated, Drenched. 

Each hunger and thirst level counts as 360 turns.
Hunger can be managed by eating.
Thirst can be managed by drinking.

### Aging

Weapon/Shield/Armour can aging up to Lv10. Each level cost different gems and money.

    Money = Item Price * Aging Level

+1 100%
+2 100%
+3 90%
+4 80%
+5 70%
+6 60%
+7 50%
+8 40%
+9 30%
+10 20%

### Craft
You can find different materials in different maps, they can be used to craft items.

### Talent
There are 6 types of talent, but player can only learn up to 4 talent in a game.

* Survival
 * Keen Eye: +1 search radius, automatically search every turn
 * Dark Vision: +1 Field of view
 * Hungry: can eat uncooked food
 * Silent Move
* Combat
 * Heroic charge: +1 move steps before attack a target
 * Blade flurry: 1 more attack chance(N%)
 * Combat master: +1 melee damage
* Archer
 * Aimed Shot: +1 Hit
 * Quick Shot: 1 more move before short
 * Archer master: +1 ranged damage
* Craft
 * Find more chance to find materials
 * Can see the recipes
* Alchemist
 * Literacy
 * Can make potions
 * +50% effect when use potion
* Cooker
 * Cooker food
 * +50% hungry & thirsty when eat food
 * find more meat when kill animals
 


## Maps
There are different types of maps, they are:

### Forest

### Cave

### Maze

### Dungeon

## Items

### Weapon
### Shield
### Armour
### Helmet
### Amulet
### Ring
### Gem
### Food
### Drink
### Scroll
### Potion
### Junk