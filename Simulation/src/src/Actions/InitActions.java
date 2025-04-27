package src.Actions;

import java.util.Random;

import src.Map;
import src.entities.Grass;
import src.entities.Herbivore;
import src.entities.Predator;
import src.entities.Rock;
import src.entities.Tree;

public class InitActions implements Action {
    private int numHerbivores = 1;
    private int numPredators = 0;
    private int numGrass = 1;
    private int numRocks = 0;
    private int numTrees = 0;

    @Override
    public void execute(Map map) {
        Random rand = new Random();
        map.addEntities(numHerbivores, Herbivore.class, rand, 'H');
        map.addEntities(numPredators, Predator.class, rand, 'P');
        map.addEntities(numGrass, Grass.class, rand, 'G');
        map.addEntities(numRocks, Rock.class, rand, 'R');
        map.addEntities(numTrees, Tree.class, rand, 'T');
    }
}
