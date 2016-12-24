package net.jmecn.rogue.map;

import static net.jmecn.rogue.core.Tile.*;

import java.util.ArrayList;
import java.util.List;

import net.jmecn.rogue.math.Vector2;

/**
 * https://github.com/scarino/DungeonGenerator/blob/master/src/main/java/com/scarino/dungeongenerator/CaveGenerator.java
 * 
 * 
 * @author yanmaoyuan
 *
 */
public class CaveSanto extends MapCreator {

    private int seedCount;
    private int iterations;
    
	public CaveSanto(int width, int height) {
		super("creator.cave.santo", width, height);
		seedCount = 5;
		iterations = 300;
	}

	public CaveSanto(String name, int width, int height) {
		super(name, width, height);
	}

	@Override
	public void initialze() {
		map.fill(Wall);
	}

	@Override
	public void create() {
		List<Vector2> seeds = generateSeeds();
        List<Vector2> dungeonList = new ArrayList<Vector2>(iterations+seedCount);
        List<Vector2> potentialTiles = new ArrayList<Vector2>(iterations+seedCount);

        for(Vector2 pos : seeds){
            dungeonList.add(pos);
            potentialTiles.addAll(getNeighbours(pos));
        }

        int count = 0;
        while(count < iterations){
            int next = nextInt(potentialTiles.size());
            Vector2 pos = potentialTiles.remove(next);
            if(!posExists(pos.getX(), pos.getY(), dungeonList) && !edgePos(pos)) {
                dungeonList.add(pos);
                potentialTiles.addAll(getNeighbours(pos));
                count++;
            }
        }

        connectSeeds(seeds, dungeonList);
        copyListToArray(dungeonList);
	}

    private boolean edgePos(Vector2 pos){
        return pos.getY() == 0 || pos.getY() == this.height - 1 ||
                pos.getX() == 0 || pos.getX() == this.width - 1;

    }

    private List<Vector2> getNeighbours(Vector2 pos){
        List<Vector2> neighbours = new ArrayList<Vector2>();
        for (int offsetY = -1; offsetY <= 1; offsetY++) {
			for (int offsetX = -1; offsetX <= 1; offsetX++) {
				if (offsetX == 0 && offsetY == 0)
					continue;
				neighbours.add(new Vector2(pos.x + offsetX, pos.y + offsetY));
			}
		}
        return neighbours;
    }

    private void connectSeeds(List<Vector2> seeds, List<Vector2> dungeonList){
        Vector2 first = seeds.remove(0);
        Vector2 next;
        while(seeds.size() > 0){
            next = getClosestSeed(first, seeds);
            addConnections(first, next, dungeonList);
            first = next;
        }
    }

    private void addConnections (Vector2 first, Vector2 second, List<Vector2> dungeonList){
        int firstX = first.getX();
        int secondX = second.getX();
        int firstY = first.getY();
        int secondY = second.getY();

        while(firstX != secondX){
            if(firstX < secondX){
                firstX++;
            }
            else{
                firstX--;
            }

            dungeonList.add(new Vector2(firstX, firstY));
        }

        while(firstY != secondY){
            if(firstY < secondY){
                firstY++;
            }
            else{
                firstY--;
            }

            dungeonList.add(new Vector2(firstX, firstY));
        }
    }

    private Vector2 getClosestSeed(Vector2 seed, List<Vector2> seeds){
        int curPos = -1;
        int curDis = Integer.MAX_VALUE;

        Vector2 current;
        int nextDis;
        for(int i = 0; i < seeds.size(); i++){
            current = seeds.get(i);
            nextDis = distance(seed, current);
            if(nextDis < curDis){
                curPos = i;
                curDis = nextDis;
            }
        }

        return seeds.remove(curPos);
    }

    private int distance(Vector2 seedOne, Vector2 seedTwo){
        int xDis = seedOne.getX() - seedTwo.getX();
        xDis *= xDis;

        int yDis = seedOne.getY() - seedTwo.getY();
        yDis *= yDis;

        return (int)Math.sqrt(xDis + yDis);
    }

    private void copyListToArray(List<Vector2> dungeonList){
        for(Vector2 pos : dungeonList){
        	map.set(pos.x, pos.y, Floor);
        }
    }

    private List<Vector2> generateSeeds(){
        List<Vector2> seeds = new ArrayList<Vector2>(seedCount);

        int count = 0;
        while(count < seedCount){
            int x = 1 + nextInt(width-2);
            int y = 1 + nextInt(height-2);

            if(!posExists(x, y, seeds)){
                seeds.add(new Vector2(x, y));
                count++;
            }
        }

        return seeds;
    }

    public boolean posExists(int x, int y, List<Vector2> positions){

        for(Vector2 pos : positions){
            if(x == pos.getX() && y == pos.getY()){
                return true;
            }
        }

        return false;
    }
}
