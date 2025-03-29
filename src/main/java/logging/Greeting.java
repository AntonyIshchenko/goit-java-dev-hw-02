package logging;

public class Greeting {

    private Greeting() {
    }

    public static void greet(byte winner) {
        String greetStart;
        switch (winner) {
            case 1:
                greetStart = "You won the game!";
                break;
            case 2:
                greetStart = "You lose the game!";
                break;
            case 3:
                greetStart = "It's a draw!";
                break;
            default:
                return;
        }

        AppLogger.log(greetStart + "\n" + "Created by Shreyas Saha. Thanks for playing!");
    }

}
