package src;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Simulation {
    private static Map map = new Map(10, 10);
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

 //                   System.out.println("Made move");
                }
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
