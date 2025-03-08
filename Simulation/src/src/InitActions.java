package src;

import java.util.Random;

public class InitActions implements Action {
    private int numHerbivores = 1;
    private int numPredators = 1;
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
