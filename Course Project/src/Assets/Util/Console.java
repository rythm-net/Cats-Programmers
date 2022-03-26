package Assets.Util;

import Assets.Config.DirectionsEnum;

import java.util.Random;
import java.util.Scanner;

public class Console {
    public static int input(String message) {

        System.out.print(message + " ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static DirectionsEnum direction(String message){
        System.out.print(message + " ");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        if(command.equals("w")) {
            return DirectionsEnum.w;
        }

        if(command.equals("s")) {
            return DirectionsEnum.s;
        }

        if(command.equals("d")) {
            return DirectionsEnum.d;
        }

        if(command.equals("a")) {
            return DirectionsEnum.a;
        }
        return DirectionsEnum.No;
    }

    public static String attackMissedMessage(){
        Random random = new Random();
        int randomNumber = random.nextInt(5)+1;
        if(randomNumber == 1) {
            return "Your unit saw a butterfly and missed his attack";
        }

        if(randomNumber == 2) {
            return "Your unit is a pacifist this round";
        }

        if(randomNumber == 3) {
            return "Your unit missed because why not";
        }

        if(randomNumber == 4) {
            return "The enemy dodged";
        }
        return "ULTRA RAPID STRI- it missed";
    }
}