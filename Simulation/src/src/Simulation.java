package src;

import java.util.ArrayList;
import java.util.Scanner;

import src.Actions.Action;
import src.Actions.InitActions;
import src.entities.Creature;
import src.entities.Entity;

import java.util.List;

public class Simulation {
    private Map map = new Map();
	private int turnCounter = 0;
    Scanner scanner = new Scanner(System.in);
    
    public List<Action> initActions = new ArrayList<>();
    public List<Action> turnActions = new ArrayList<>();

    public Simulation() {
        initActions.add(new InitActions());
    }

    private void nextTurn() {
        turnCounter++;
        System.out.println("Turn: " + turnCounter);
        entityCheckHealth();
        creatureInfo();
        creatureMove();
        Renderer.render(map);
        System.out.println("Press Enter to continue...");
        scanner.nextLine(); 
    }

    private void creatureMove() {
    	for (Entity entity : map.getEntities()) {
            if (entity instanceof Creature) {
                ((Creature) entity).makeMove(map);
            }
        }
    }
    
    private void entityCheckHealth() {
    	for (Entity entity : map.getEntities()) {
            entity.checkHealth(map);
            }
        }
    
    private void creatureInfo() {
    	for (Entity entity : map.getEntities()) {
    		if (entity instanceof Creature) {
		System.out.println("Creature: " + ((Creature) entity).toString());
    		}
    	}
    }
    
    public void startSimulation() {
        for (Action action : initActions) {
            action.execute(map);
        }
        Renderer.render(map);
        while (true)
            nextTurn();
        }

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.startSimulation();
    }
    
}
