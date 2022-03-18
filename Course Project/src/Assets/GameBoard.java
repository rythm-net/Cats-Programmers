package Assets;

import Assets.Config.DirectionsEnum;
import Assets.Config.UnitColor;
import Assets.Heroes.Dwarf;
import Assets.Heroes.Elf;
import Assets.Heroes.Knight;
import Assets.Obstacles.Barricade;
import Assets.Obstacles.Wall;
import Assets.Util.Console;
import Assets.Util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameBoard {

    private static final int BOARD_ROW           = 7;
    private static final int BOARD_COL           = 9;
    private static final String GAME_BOARD_SPACE = "   ";
    private static int dwarfUnitRemaining        = 2;
    private static int elfUnitRemaining          = 2;
    private static int knightUnitRemaining       = 2;

    public static List<Unit> redUnitsContainer  = new ArrayList<>();
    public static List<Unit> blackUnitContainer = new ArrayList<>();
    public static List<Unit> obstacleContainer  = new ArrayList<>();

    public static int redPlayerScore   = 0;
    public static int blackPlayerScore = 0;
    private static int obstacleCount   = 0;

    private static int turns          = 0;
    private static String redDeadUnitsInOrder   = "";
    private static String blackDeadUnitsInOrder = "";
    private static int redDeadUnits   = 0;
    private static int blackDeadUnits = 0;

    public static Scanner scanner = new Scanner(System.in);

    public static Unit[][] gameBoardInitialization() {
        Unit[][] gameBoard = new Unit[BOARD_ROW][BOARD_COL];
        blackTurn(gameBoard);
        blackPhase1(gameBoard);
        System.out.println();
        redTurn(gameBoard);
        redPhase1(gameBoard);
        generateObstacles(gameBoard);
        phase2(gameBoard);
        System.out.println("1- New game");
        System.out.println("2- Exit game");
        int command=scanner.nextInt();
        if(command==1){
            gameBoardInitialization();
        }
        else{
            return gameBoard;
        }
        return gameBoard;
    }

    private static void blackTurn(Unit[][] gameBoard) {
        for (int row = 0; row < BOARD_ROW; row++) {
            for (int col = 0; col < BOARD_COL; col++) {
                Unit gameBoardElement = gameBoard[row][col];
                String sign = (gameBoardElement == null) ? GAME_BOARD_SPACE : gameBoardElement.getSign() + " ";
                if (row == 0 || row == 1) {
                    System.out.print("\u001B[41m" + sign + "\u001B[0m");
                }

                if (row > 1 && row < 5) {
                    System.out.print("\u001B[44m" + sign + "\u001B[0m");
                }

                if (row == 5 || row == 6) {
                    System.out.print("\u001B[40m" + sign + "\u001B[0m");
                }
            }
            System.out.println();
        }
    }

    private static void redTurn(Unit[][] gameBoard) {
        for (int row = BOARD_ROW - 1; row >= 0; row--) {
            for (int col = BOARD_COL - 1; col >= 0; col--) {
                Unit gameBoardElement = gameBoard[row][col];
                String sign = (gameBoardElement == null) ? GAME_BOARD_SPACE : gameBoardElement.getSign() + " ";
                if (row == 0 || row == 1) {
                    System.out.print("\u001B[41m" + sign + "\u001B[0m");
                }

                if (row > 1 && row < 5) {
                    System.out.print("\u001B[44m" + sign + "\u001B[0m");
                }

                if (row == 5 || row == 6) {
                    System.out.print("\u001B[40m" + sign + "\u001B[0m");
                }
            }
            System.out.println();
        }
    }

    private static void redPhase1(Unit[][] gameBoard){
        knightUnitRemaining = 2;
        dwarfUnitRemaining  = 2;
        elfUnitRemaining    = 2;
        while (knightUnitRemaining > 0 || elfUnitRemaining > 0 || dwarfUnitRemaining > 0) {

            System.out.println("Choose a unit to put on the board");
            System.out.println("1. Knight - " + knightUnitRemaining + " remaining");
            System.out.println("2. Elf    - " + elfUnitRemaining + " remaining");
            System.out.println("3. Dwarf  - " + dwarfUnitRemaining + " remaining");

            int command = scanner.nextInt();
            String unitSign = " ";
            if (command == 1 && knightUnitRemaining != 0) {
                unitSign = "%";
                generateUnits(unitSign, gameBoard, UnitColor.RED, redUnitsContainer);
                knightUnitRemaining--;
                redTurn(gameBoard);
            }

            if (command == 2 && elfUnitRemaining != 0) {
                unitSign = "&";
                generateUnits(unitSign, gameBoard, UnitColor.RED, redUnitsContainer);
                elfUnitRemaining--;
                redTurn(gameBoard);
            }

            if (command == 3 && dwarfUnitRemaining != 0) {
                unitSign = "@";
                generateUnits(unitSign, gameBoard, UnitColor.RED, redUnitsContainer);
                dwarfUnitRemaining--;
                redTurn(gameBoard);
            }
        }
    }

    private static void blackPhase1(Unit[][] gameBoard){
        while(knightUnitRemaining > 0 || elfUnitRemaining > 0 || dwarfUnitRemaining > 0) {

            System.out.println("Choose a unit to put on the board");
            System.out.println("1. Knight - " + knightUnitRemaining + " remaining");
            System.out.println("2. Elf    - " + elfUnitRemaining + " remaining");
            System.out.println("3. Dwarf  - " + dwarfUnitRemaining + " remaining");

            int command = scanner.nextInt();
            String unitSign = " ";
            if (command == 1 && knightUnitRemaining != 0) {
                unitSign = "%";
                generateUnits(unitSign, gameBoard, UnitColor.BLACK, blackUnitContainer);
                knightUnitRemaining--;
                blackTurn(gameBoard);
            }
            if (command == 2 && elfUnitRemaining != 0) {
                unitSign = "&";
                generateUnits(unitSign, gameBoard, UnitColor.BLACK, blackUnitContainer);
                elfUnitRemaining--;
                blackTurn(gameBoard);
            }
            if (command == 3 && dwarfUnitRemaining != 0) {
                unitSign = "@";
                generateUnits(unitSign, gameBoard, UnitColor.BLACK, blackUnitContainer);
                dwarfUnitRemaining--;
                blackTurn(gameBoard);
            }
        }
    }

    private static void generateUnits(String unitSign,Unit[][] gameBoard,UnitColor color,List<Unit>unitContainer) {

        int row = Console.input("Въведете ROW позиция");
        int col = Console.input("Въведете COL позиция");

        if(unitSign.equals("%")) {
            Knight knight = new Knight(row,col, color);
            gameBoard[row][col] = knight;
            unitContainer.add(knight);
        }

        if(unitSign.equals("&")) {
            Elf elf = new Elf(row,col,color);
            gameBoard[row][col] = elf;
            unitContainer.add(elf);
        }

        if(unitSign.equals("@")) {
            Dwarf dwarf = new Dwarf(row,col,color);
            gameBoard[row][col]= dwarf;
            unitContainer.add(dwarf);
        }
    }

    private static void generateObstacles(Unit[][] gameBoard){
        for (int row=2;row<5;row++) {
            for (int col=0;col<BOARD_COL;col++) {

                int randomNumber= Util.generateObstacle();

                if(randomNumber==3&&obstacleCount<5){
                    int obstacleType = Util.chooseObstacleType();
                    if(obstacleType==1) {
                        Barricade barricade = new Barricade(row,col);
                        gameBoard[row][col]=barricade;
                        obstacleContainer.add(barricade);
                        obstacleCount++;
                    }

                    if(obstacleType==2) {
                        Wall wall = new Wall(row,col);
                        gameBoard[row][col]=wall;
                        obstacleContainer.add(wall);
                        obstacleCount++;
                    }
                }
            }
        }

        if(obstacleCount==1) {
            int row = 0;
            int col = 0;
            for (Unit unit : obstacleContainer){
                row = unit.row;
                col = unit.col;
            }

            Wall wall=new Wall(row,col);
            obstacleContainer.remove(0);
            obstacleContainer.add(wall);
            gameBoard[row][col]=wall;
        }
        blackTurn(gameBoard);
    }

    private static void phase2(Unit[][] gameBoard){
        System.out.println("Black's turn");
        menu(gameBoard,UnitColor.BLACK);
        System.out.println("Red's turn");
        menu(gameBoard,UnitColor.RED);
        if (redDeadUnits == 6) {
            System.out.println("Black wins");
            endOfGameStats(UnitColor.BLACK);
            return;
        }

        if(blackDeadUnits==6){
            endOfGameStats(UnitColor.RED);
            return;
        }

        turns++;
        phase2(gameBoard);
    }

    private static void menu(Unit[][] gameBoard,UnitColor color) {

        System.out.println("1- Move unit");
        System.out.println("2- Attack unit");
        System.out.println("3- Heal unit");
        System.out.println("4- Destroy obstacle");
        System.out.println("5- Unit stats");
        System.out.println("6- Next turn");

        int command = scanner.nextInt();
        if(command == 1) {
            moveUnit(gameBoard);
        }

        if(command == 2) {
            attackUnit(gameBoard,color);
        }

        if(command == 3) {
            healUnit(gameBoard);
        }
        if(command == 4) {

        }
        if(command == 5) {
            unitStats(color);
        }

    }

    private static void moveUnit(Unit[][] gameBoard) {

        int selectRow = Console.input("Input ROW");
        int selectCol = Console.input("Input COL");

        Unit selectedUnit = gameBoard[selectRow][selectCol];
        DirectionsEnum movementCommand = Console.direction("Choose a direction for your unit");

        // избираме дестинация на тази фигура
        int moveRow = Console.input("Input ROW for movement");
        int moveCol = Console.input("Input COL for movement");

        if(selectedUnit.isMovePossible(movementCommand,moveRow, moveCol)) {

            // изтриваме текущата фигура от дъската
            gameBoard[selectRow][selectCol] = null;

            // извършваме придвижването на фигурата
            selectedUnit.move(moveRow, moveCol);

            // добавяме селектираната фигура към новите координати на дъската
            gameBoard[moveRow][moveCol] = selectedUnit;
        }
        else {
            System.out.println("Invalid movement");
        }
    }

    private static void attackUnit(Unit[][] gameBoard,UnitColor color) {

        int selectRow = Console.input("Input ROW");
        int selectCol = Console.input("Input COL");

        Unit selectedUnit = gameBoard[selectRow][selectCol];
        System.out.println("Choose the target of your attack");

        int enemySelectedRow = Console.input("Input ROW of target");
        int enemySelectedCol = Console.input("Input COL of target");

        Unit selectedEnemy = gameBoard[enemySelectedRow][enemySelectedCol];
        if(selectedUnit.isAttackPossible(enemySelectedRow, enemySelectedCol)) {
            int diceOne   = Util.diceForAttack();
            int diceTwo   = Util.diceForAttack();
            int diceThree = Util.diceForAttack();
            int sum = diceOne + diceTwo + diceThree;
            if(sum == selectedEnemy.healthPoints){
                System.out.println(Console.attackMissedMessage());
            }

            else if(sum == 3) {
                int damage = (selectedUnit.attack - selectedEnemy.armor) / 2;
                selectedEnemy.healthPoints -= damage;
                if(color == UnitColor.RED) {
                    redPlayerScore += damage;
                }

                if(color == UnitColor.BLACK){
                    blackPlayerScore += damage;
                }
                gameBoard[enemySelectedRow][enemySelectedCol]=selectedEnemy;
            }

            else {
                int damage = selectedUnit.attack - selectedEnemy.armor;
                selectedEnemy.healthPoints -= damage;
                if(color == UnitColor.RED) {
                    redPlayerScore += damage;
                }

                if(color == UnitColor.BLACK) {
                    blackPlayerScore += damage;
                }
                gameBoard[enemySelectedRow][enemySelectedCol]=selectedEnemy;
            }

            if (selectedEnemy.healthPoints <= 0){
                if(color == UnitColor.RED) {
                    deathOfAUnit(UnitColor.BLACK,enemySelectedRow,enemySelectedCol,gameBoard);
                } else{
                    deathOfAUnit(UnitColor.RED,enemySelectedRow,enemySelectedCol,gameBoard);
                }
            }
        } else {
            System.out.println("Invalid target");
        }
    }

    private static void healUnit(Unit[][] gameBoard) {

        int selectRow = Console.input("Input ROW");
        int selectCol = Console.input("Input COL");

        Unit selectedUnit = gameBoard[selectRow][selectCol];
        int healDice = Util.diceForAttack();
        selectedUnit.healthPoints += healDice;
    }

    private static void unitStats(UnitColor color) {

        int selectRow = Console.input("Input ROW");
        int selectCol = Console.input("Input COL");
        if(color == UnitColor.RED)
            for (Unit unit : redUnitsContainer) {
                if(selectRow == unit.row && selectCol == unit.col) {
                    unit.getUnitStats();
                }
            }

        if(color == UnitColor.BLACK) {
            for (Unit unit : blackUnitContainer) {
                if(selectRow == unit.row && selectCol == unit.col){
                    unit.getUnitStats();
                }
            }
        }
    }

    private static void deathOfAUnit(UnitColor color,int enemySelectedRow,int enemySelectedCol,Unit[][] gameBoard) {
        String unitName = "";
        int index = 0;
        if(color == UnitColor.RED){
            int temp = 0;
            for (Unit unit : redUnitsContainer){
                if(enemySelectedCol == unit.col && enemySelectedRow == unit.row) {
                    unitName=unit.unitName;
                    index=temp;
                }
                temp++;
            }
            redUnitsContainer.remove(index);
            gameBoard[enemySelectedRow][enemySelectedCol] = null;
            redDeadUnitsInOrder += unitName + " ";
            redDeadUnits++;
        }

        if(color == UnitColor.BLACK) {
            int temp = 0;
            for (Unit unit : blackUnitContainer) {
                if(enemySelectedCol == unit.col && enemySelectedRow == unit.row) {
                    unitName = unit.unitName;
                    index = temp;
                }
                temp++;
            }
            blackUnitContainer.remove(index);
            gameBoard[enemySelectedRow][enemySelectedCol] = null;
            blackDeadUnitsInOrder += unitName + " ";
            blackDeadUnits++;
        }
    }

    private static void endOfGameStats(UnitColor color) {
        if(color == UnitColor.RED) {

            System.out.println("Red wins");
            System.out.println("Number of turns: " + turns);
            System.out.println();
            System.out.println("Red score: " + redPlayerScore);
            System.out.println("Red dead units: "+ redDeadUnitsInOrder);
            System.out.println();
            System.out.println("Black score: "+ blackPlayerScore);
            System.out.println("Black dead units: "+ blackDeadUnitsInOrder);
        }

        if(color == UnitColor.BLACK) {
            System.out.println("Black wins");
            System.out.println("Number of turns: "+ turns);
            System.out.println();
            System.out.println("Black score: " + blackPlayerScore);
            System.out.println("Black dead units: " + blackDeadUnitsInOrder);
            System.out.println();
            System.out.println("Red score: " + redPlayerScore);
            System.out.println("Red dead units: " + redDeadUnitsInOrder);
        }
    }
}