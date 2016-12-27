package net.jmecn.rogue.map;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.jmecn.rogue.core.Map;

public class MapFactory {

	static Logger logger = LoggerFactory.getLogger(MapFactory.class);
	
	Random rand = new Random();
	// MapCreater base
	private List<MapCreator> mapCreators;
	private MapCreator creator;
	private int creatorCnt;
	private int width;
	private int height;
	private long seed;
	private boolean isRand;
	
	public MapFactory() {
		width = 41;
		height = 41;
		seed = md5("yan");
		isRand = true;
		
		mapCreators = new ArrayList<MapCreator>();
		mapCreators.add(new ForestHauberk(width, height));
		mapCreators.add(new CaveCellauto(width, height));
		mapCreators.add(new DungeonNickgravelyn(width, height));
		mapCreators.add(new DungeonYan(width, height));
		mapCreators.add(new DungeonHauberk(width, height));
		mapCreators.add(new DungeonTyrant(width, height));
		mapCreators.add(new Maze(width/4, height/4));
		mapCreators.add(new Building(width, height));
		mapCreators.add(new CityLeafVenation(width, height));
		creatorCnt= mapCreators.size();
	}

	/**
	 * update map
	 */
	public Map createMap() {
		creator = mapCreators.get(rand.nextInt(creatorCnt));
		
		creator.resize(width, height);
		creator.setSeed(seed);
		creator.setUseSeed(!isRand);
		creator.initialze();
		creator.create();
		
		logger.debug("Create map with : {}", creator);
		return creator.getMap();
	}
	
	private long md5(String seeds) {
		long value = seed;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(seeds.getBytes("UTF-8"));
	
			byte byteData[] = md.digest();
	
			// convert the byte to hex format method 2
			StringBuffer hexString = new StringBuffer();
			hexString.append("0x");
			for (int i = 0; i < 7; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			value = Long.decode(hexString.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return value;
	}
}
