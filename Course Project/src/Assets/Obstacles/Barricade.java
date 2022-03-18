package Assets.Obstacles;

import Assets.Config.DirectionsEnum;
import Assets.Unit;

public class Barricade extends Unit {
    public Barricade(int row,int col) {
        super("$",row,col);
        this.healthPoints = 1;
    }

    public boolean isMovePossible(DirectionsEnum command, int toRow, int toCol) {
        return isPossible(toRow, toCol, 0, 0);
    }

    public boolean isAttackPossible(int toRow, int toCol) {
        return isPossible(toRow, toCol, 0, 0);
    }

    private boolean isPossible(int toRow,int toCol,int expectedRowCoefficient, int expectedColCoefficient) {
        return false;
    }
}