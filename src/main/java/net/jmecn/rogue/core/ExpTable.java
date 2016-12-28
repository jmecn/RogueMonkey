package net.jmecn.rogue.core;

/*
This is a utility for generating experience point tables.

Each line printed will display a level number, the number of points
required to reach that level and finally the gap to the next level.

Compile with something like: gcc -lm xptable.c -o xptable

ulf.astrom@gmail.com / happyponyland.net, 2014-11-20


http://www.roguebasin.com/index.php?title=Experience_table_generator

*/

public class ExpTable {

	public static void main(String[] args) {
		long total = 0;
		int level;

		// You probably want to change these!

		// Points required to go from level 1 -> 2
		long tnl = 100;

		// How quickly the curve flattens out
		double factor = 0.95;

		// The number of levels to display
		int levels = 20;

		for (level = 1; level <= levels; level++) {
			System.out.printf("Level %2d  |  %-12d |  %-12d |\n", level, total, tnl);
			total += tnl;
			tnl = (long) (tnl * (1 + Math.pow(factor, level)));
		}

		return;
	}
}
