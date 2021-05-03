
import java.util.*;

public class BullsCows {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");

        String fromUser = scanner.nextLine();
        int lengthWant;

        try {
            lengthWant = Integer.parseInt(fromUser);
        } catch (Exception e) {
            System.out.printf("Error: %s isn't a valid number",fromUser);
            return;
        }

        if (lengthWant > 35) {
            System.out.println("Error");
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");

        String fromUser1 = scanner.nextLine();
        int lengthpossibleSym;

        try {
            lengthpossibleSym = Integer.parseInt(fromUser1);
        } catch (Exception e) {
            System.out.printf("Error: %s isn't a valid number",fromUser1);
            return;
        }

        if (lengthpossibleSym < lengthWant) {
            System.out.printf("Error: it's not possible to generate a code with a length %d with %d unique symbols", lengthWant, lengthpossibleSym);
            return;
        }

        if (lengthWant == 0) {
            System.out.println("Error");
            return;
        }

        if (lengthpossibleSym > 36) {
            System.out.println("Error");
            return;
        }


        String ans = generateRandomguess(lengthWant,lengthpossibleSym);
        String secret = new String(new char[lengthWant]).replace("\0", "*");
        String message = "0-9";

        if (lengthpossibleSym > 10) {
            char c = (char) (lengthpossibleSym - 1 + 87);
            message += ", a-" + c;
        }

        System.out.printf("The secret is prepared: %s (%s).%n", secret , message);
        System.out.println("Okay, let's start a game!");

        int attemptNum = 1;

        String guessUser = "";
        while (!guessUser.equals(ans)) {
            System.out.printf("Turn %d:\n", attemptNum);
            guessUser = scanner.nextLine();
            String response = predictionCheck(guessUser, ans);
            System.out.println(response);
            if (guessUser.equals(ans)){ 
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            attemptNum += 1;
        }



    }

    public static String generateRandomguess(int lengthSecret,int possibleSymbol) {

        String sb = "";

        Random random = new Random(possibleSymbol);

        while (sb.length() < lengthSecret) {
            int num = random.nextInt(possibleSymbol);
            num = num < 10 ? num + 48: num + 87;
            char c = (char) num;
            String numStr = c + "";
            if (!sb.contains(numStr)) {
                sb += c;
            }

        }

        return sb;

    }



    public static String predictionCheck(String guess, String correct) {
        int cows = 0;
        int bulls = 0;

        for (int j = 0; j < correct.length(); j++) {
            for (int i = 0; i < guess.length(); i++) {
                if (guess.charAt(i) == correct.charAt(j)) {
                    if (i == j) {
                        bulls += 1;
                    } else {
                        cows += 1;
                    }
                }
            }
        }

        if (cows + bulls == 0) {
            String msg = "Grade: " + "None";
            return msg;
        } else {
            if (bulls > 0 && cows > 0) {
                String result = "" + bulls + " bull(s) and " + cows + " cow(s)";
                String msg = "Grade: " + result ;
                return msg;
            } else if (bulls > 0) {
                String result = "" + bulls + " bull(s)";
                String msg = "Grade: " + result ;
                return msg;
            } else if (cows > 0) {
                String result = "" + cows + " cow(s)";
                String msg = "Grade: " + result ;
                return msg;
            }
        }
    return "";

    }

}