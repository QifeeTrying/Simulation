package src;

import src.entities.Entity;

public class Renderer {
    public static void render(Map map) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                char symbol = '.';

                for (Entity entity : map.getEntities()) {
                    if (entity.getX() == x && entity.getY() == y) {  
                        symbol = map.getSymbolForEntity(entity);
                        break;
                    }
                }

                System.out.print(" ");
                System.out.print(symbol);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
