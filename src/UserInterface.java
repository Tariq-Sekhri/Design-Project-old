import java.util.Scanner;

public class UserInterface {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        String rules = "nothing yet";
        System.out.println("hello and welcome to go fish");
        System.out.println("here are the rules" + rules);
        System.out.println("please enter the number of players");
        int numberOfPlayer  = Integer.parseInt(in.nextLine());
        String[] PlayerNames = new String[numberOfPlayer];  
        System.out.println("please enter the name of the first player");
        PlayerNames[0] = in.nextLine();
        for (int i = 1; i < numberOfPlayer; i++) {
             System.out.println("please enter the name of player "+(i+1));
            PlayerNames[i] = in.nextLine();
        }
         GameMaster game = new GameMaster(numberOfPlayer, PlayerNames);
        

    }
}