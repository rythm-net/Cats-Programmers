package Assets.Heroes;

import Assets.Config.DirectionsEnum;
import Assets.Config.UnitColor;
import Assets.Unit;

public class Knight extends Unit {
    public Knight(int row,int col , UnitColor color) {
        super("%",row,col,8,3,15,1,1,color);
        this.color = color;
        this.unitName = "Knight";
    }

    public boolean isMovePossible(DirectionsEnum command, int toRow, int toCol) {
        if(command == DirectionsEnum.s) {
            return isPossible(toRow, toCol, 1, 0);
        }

        if(command == DirectionsEnum.w) {
            return isPossible(toRow, toCol, -1, 0);
        }

        if(command == DirectionsEnum.a) {
            return isPossible(toRow, toCol, 0, -1);
        }

        if(command == DirectionsEnum.d) {
            return isPossible(toRow, toCol, 0, 1);
        }
        return isPossible(toRow, toCol, 0, 0);
    }

    public boolean isAttackPossible(int toRow, int toCol) {
        return isPossible(toRow, toCol, 1, 0);
    }

    private boolean isPossible(int toRow, int toCol, int expectedRowCoefficient, int expectedColCoefficient) {
        int rowCoefficient = Math.abs(toRow - this.row);
        int colCoefficient = Math.abs(toCol - this.col);

        return  rowCoefficient == expectedRowCoefficient && colCoefficient == expectedColCoefficient;
    }
}