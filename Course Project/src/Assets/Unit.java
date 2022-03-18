package Assets;

import Assets.Config.DirectionsEnum;
import Assets.Config.UnitColor;

public abstract class Unit {
    protected String sign;
    protected int row;
    protected int col;
    protected int attack;
    protected int armor;
    protected int healthPoints;
    protected int attackRange;
    protected int moveSpeed;
    protected UnitColor color;
    protected String unitName;

    public Unit(String sign,int row,int col,int attack,int armor,int healthPoints,int attackRange,int moveSpeed,UnitColor color){
        this.sign         = sign;
        this.row          = row;
        this.col          = col;
        this.attack       = attack;
        this.armor        = armor;
        this.healthPoints = healthPoints;
        this.attackRange  = attackRange;
        this.moveSpeed    = moveSpeed;
        this.color        = color;
    }

    public Unit(String sign,int row,int col){
        this.sign = sign;
        this.row  = row;
        this.col  = col;
    }

    public String getColorSign() {
        if(this.color == UnitColor.BLACK) {
            return "b";
        }

        if(this.color == UnitColor.RED) {
            return "r";
        }
        return " ";
    }

    public String getSign() {
        return this.getColorSign() + this.sign;
    }

    public void move(int moveRow, int moveCol) {
        this.row = moveRow;
        this.col = moveCol;
    }

    public void getUnitStats(){
        System.out.println("Unit class type " + this.unitName);
        System.out.println("Unit health points " + this.healthPoints);
        System.out.println("Unit armor " + this.armor);
        System.out.println("Unit attack " + this.attack);
        System.out.println("Attack range " + this.attackRange);
        System.out.println("Movement speed " + this.moveSpeed);
    }

    public abstract boolean isMovePossible(DirectionsEnum command, int toRow, int toCol);

    public abstract boolean isAttackPossible(int toRow, int toCol);
}