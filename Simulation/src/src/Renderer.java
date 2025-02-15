package src;

public class Renderer {
    public static void render(Map map) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                System.out.print(map.getSymbolAt(x, y));
            }
            System.out.println();
        }
        System.out.println();
    }
}
