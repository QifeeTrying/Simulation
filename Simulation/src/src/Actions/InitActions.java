package src.Actions;

import java.util.Random;

import src.Map;
import src.entities.Grass;
import src.entities.Herbivore;
import src.entities.Predator;
import src.entities.Rock;
import src.entities.Tree;

public class InitActions implements Action {
	private int numHerbivores = 5;
	private int numPredators = 2;
	private int numGrass = 7;
	private int numRocks = 3;
	private int numTrees = 3;

	@Override
	public void execute(Map map) {
		Random rand = new Random();
		map.initialaddEntities(numHerbivores, Herbivore.class, rand, 'H');
		map.initialaddEntities(numPredators, Predator.class, rand, 'P');
		map.initialaddEntities(numGrass, Grass.class, rand, 'G');
		map.initialaddEntities(numRocks, Rock.class, rand, 'R');
		map.initialaddEntities(numTrees, Tree.class, rand, 'T');
	}
}
