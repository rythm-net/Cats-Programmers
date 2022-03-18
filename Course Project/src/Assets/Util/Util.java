package Assets.Util;

import java.util.Random;

public class Util {
    public static int generateObstacle() {
        Random random = new Random();
        return random.nextInt(4)+1;
    }

    public static int chooseObstacleType() {
        Random random = new Random();
        return random.nextInt(2)+1;
    }

    public static int diceForAttack() {
        Random random = new Random();
        return random.nextInt(6)+1;
    }
}