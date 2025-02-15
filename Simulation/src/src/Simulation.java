package src;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private Map map = new Map(10, 10);
    private int turnCounter = 0;
    
    public List<Action> initActions = new ArrayList<>();  // Список дій для ініціалізації
    public List<Action> turnActions = new ArrayList<>();  // Список дій на хід

    public Simulation() {
        initActions.add(new InitActions());  // Викликаємо конструктор InitActions без додаткових параметрів
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
        
        // Далі йде цикл ходів
        while (true) {
            nextTurn();
            try {
                Thread.sleep(5000); // Затримка в 5 секунд для зручності
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Статичний метод main для запуску програми
    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.startSimulation();  // Запуск симуляції
    }
}
