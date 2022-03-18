package Assets.Obstacles;

import Assets.Config.DirectionsEnum;
import Assets.Unit;

public class Wall extends Unit {
    public Wall(int row,int col) {
        super("#", row, col);
        this.healthPoints = 99999;
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