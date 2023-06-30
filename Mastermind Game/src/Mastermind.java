import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arrayPassword = new int[4];
        int[] arrayAnswer = new int[4];
        int attempts = 0;
        boolean correctPassword = false;
        int correctDigits, shiftedDigits;

        HomeScreen.defaltHomeScreen();

        fillArrayPassword(arrayPassword);

        while (attempts < 10 && !correctPassword) {
            System.out.print("Attempt " + (attempts + 1) + "\n");

            fillArrayAnswer(arrayAnswer, scanner);

            System.out.print("Attempt: ");
            toShow(arrayAnswer);
            System.out.println();

            correctDigits = showCorretDigits(arrayPassword.clone(), arrayAnswer.clone());
            shiftedDigits = shiftedDigits(arrayPassword.clone(), arrayAnswer.clone());

            System.out.println("Correct Digits: " + correctDigits);
            System.out.println("Shifted Digits: " + shiftedDigits);
            System.out.println();

            attempts++;

            if (correctDigits == 4) {
                correctPassword = true;
                HomeScreen.winnerEndScreen();
                break;
            }
            if (attempts == 10) {
                HomeScreen.gameOverEndScreen();
            }
        }
    }

    public static void fillArrayAnswer(int[] arrayAnswer, Scanner scanner) {
        System.out.print("Enter your attempt (4 numbers separated by space): ");
        for (int counter = 0; counter < 4; counter++) {
            arrayAnswer[counter] = scanner.nextInt();
        }

    }

    public static void fillArrayPassword(int[] array) {
        Random rand = new Random();
        for (int counter = 0; counter < array.length; counter++) {
            array[counter] = rand.nextInt(6) + 1;
        }
    }

    public static void toShow(int[] array) {
        for (int counter = 0; counter < array.length; counter++) {
            System.out.print(array[counter] + " ");
        }
        System.out.println();
    }

    public static int shiftedDigits(int[] arrayPassword, int[] arrayAnswer) {
        int shiftedDigits = 0;
        int[] tempPassword = Arrays.copyOf(arrayPassword, arrayPassword.length);
        int[] tempAnswer = Arrays.copyOf(arrayAnswer, arrayAnswer.length);

        for (int countPassword = 0; countPassword < tempPassword.length; countPassword++) {
            if (tempAnswer[countPassword] == tempPassword[countPassword]) {
                tempAnswer[countPassword] = -1;
                tempPassword[countPassword] = -2;
            }
        }

        for (int countAnswer = 0; countAnswer < tempAnswer.length; countAnswer++) {
            if (tempAnswer[countAnswer] != -1) {
                for (int countPassword = 0; countPassword < tempPassword.length; countPassword++) {
                    if (tempAnswer[countAnswer] == tempPassword[countPassword] && tempAnswer[countAnswer] != -2) {
                        shiftedDigits++;
                        tempAnswer[countAnswer] = -1;
                        break;
                    }
                }
            }
        }

        return shiftedDigits;
    }

    public static int showCorretDigits(int[] arrayPassword, int[] arrayAnswer) {
        int correctDigits = 0;

        for (int countPassword = 0; countPassword < arrayPassword.length; countPassword++) {
            if (arrayPassword[countPassword] == arrayAnswer[countPassword]) {
                correctDigits++;
                arrayPassword[countPassword] = -1;
                arrayAnswer[countPassword] = -1;
            }
        }
        return correctDigits;
    }
}