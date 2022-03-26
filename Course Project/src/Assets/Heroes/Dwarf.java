package Assets.Heroes;

import Assets.Config.DirectionsEnum;
import Assets.Config.UnitColor;
import Assets.Unit;

public class Dwarf extends Unit {
    public Dwarf(int row,int col,UnitColor color) {
        super("@",row,col, 6 , 2 , 12 , 2 , 2,color);
        this.color = color;
        this.unitName = "Dwarf";
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
        return isPossible(toRow, toCol, 2, 0);
    }

    private boolean isPossible(int toRow, int toCol, int expectedRowCoefficient, int expectedColCoefficient) {
        int rowCoefficient = Math.abs(toRow - this.row);
        int colCoefficient = Math.abs(toCol - this.col);

        return  rowCoefficient == expectedRowCoefficient && colCoefficient == expectedColCoefficient;
    }
}