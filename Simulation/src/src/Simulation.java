package src;

import java.util.ArrayList;
import java.util.Scanner;

import src.Actions.Action;
import src.Actions.InitActions;
import src.entities.Creature;
import src.entities.Entity;

import java.util.List;

public class Simulation {
    private Map map = new Map(10, 10);
//	private static Map map;
	private int turnCounter = 0;
    Scanner scanner = new Scanner(System.in);
    
    public List<Action> initActions = new ArrayList<>();
    public List<Action> turnActions = new ArrayList<>();

    public Simulation() {
        initActions.add(new InitActions());
    }

    public void nextTurn() {
        turnCounter++;
        System.out.println("Turn: " + turnCounter);
        
        Renderer.render(map);
    }

    public void startSimulation() {
        for (Action action : initActions) {
            action.execute(map);
        }
        
        while (true) {
            for (Entity entity : map.getEntities()) {
                if (entity instanceof Creature) {
                    ((Creature) entity).makeMove(map);
                }
                entity.checkHealth(map);
            }
            
            nextTurn();
            System.out.println("Press Enter to continue...");
            scanner.nextLine(); 
        }
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.startSimulation();
    }
    
}
