package src;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Simulation {
    private Map map = new Map(10, 10);
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

            for (Herbivore h : map.getHerbivores()) {
                h.makeMove(map);
            }
            for (Predator p : map.getPredators()) {
                p.makeMove(map);
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
