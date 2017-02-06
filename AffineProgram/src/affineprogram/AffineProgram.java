/*
 * Name: James Vanaselja
 * Course: CIS 3362 - Fall 2016
 * Assignment Title:
 * Date:
 * Copyright James Vanaselja 2016. All Rights Reserved.
 * WARNING: Unauthorized use of this program is prohibited by law.
 */
package affineprogram;

import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author James
 */
public class AffineProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //a is alpha, b is beta.
        int a = 0;
        int inputA = 0;
        int inputB = 0;
        int choiceB = 0;
        int choice = 0;
        int b = 0;
        ArrayList<Integer> AArray = new ArrayList<>();
        ArrayList<Integer> BArray = new ArrayList<>();
        ArrayList decryptedtext = new ArrayList();
        Random rand = new Random(); 
        int randA = (int) (Math.random() * (12-1));
        int randB = (int) (Math.random() * (26-1));
        System.out.println("Random A is: " + randA +                 
                           " Random B is: " + randB);
        //System.out.println("words in dictionary: " + dictionary);

        //input
        System.out.println("What would you like to do?\n" + "1. Encrypt\n" 
                + "2. Decrypt\n" + "3. Brute Force Decrypt\n" + "4. Exit");
        choice = scan.nextInt();

        if (choice == 1) {
            try {
                
        String message = "";
        String input="";
                //run encrypt
                System.out.println("If alpha or beta are blank, a random key will be generated.");
                System.out.println("What is alpha?");
                inputA = scan.nextInt();
                System.out.println("What is beta?");
                inputB = scan.nextInt();
                //check input to make sure it doesnt have prohibted alpha or beta values
                //System.out.println("Alpha is " + a + " "+ "Beta is " + b + " ");

                if (inputA > 0 && inputA < 26) {
                    if (inputA % 2 != 0 && inputA != 13) {
                        //System.out.println("Alpha is " +a);
                        a = inputA;
                    }
                } else if (inputA == 0){
                  //  rand
                }
                else {
                    System.out.println("Alpha is invalid. Try again.");

                }

                if (inputB >= 0 && inputB < 26) {
                    //system.out.println();
                    b = inputB;
                } else {
                    System.out.println("Beta is invalid. Try again");
                }

                System.out.println("Please enter your message: ");
               input = scan.nextLine();
                message = input.toLowerCase();
                String cipher = encrypt(message, a, b);

                System.out.println("The encrypted text is: " + cipher);
                // System.out.println("Program is now exiting...");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.exit(0);
            //break;
        } else if (choice == 2) {
            //run decyrpt
            System.out.println("What is alpha?");
            inputA = scan.nextInt();
            System.out.println("What is beta?");
            inputB = scan.nextInt();
            //check input to make sure it doesnt have prohibted alpha or beta values
            //System.out.println("Alpha is " + a + " "+ "Beta is " + b + " ");

            if (inputA > 0 && inputA < 26) {
                if (inputA % 2 != 0 && inputA != 13) {
                    //System.out.println("Alpha is " +a);
                    a = inputA;
                }
            } else {
                System.out.println("Alpha is invalid. Try again.");

            }

            if (inputB >= 0 && inputB < 26) {
                //system.out.println();
                b = inputB;
            } else {
                System.out.println("Beta is invalid. Try again");
            }

            System.out.println("Please enter your ciphertext: ");
            message = scan.nextLine();
            message.replaceAll(" ", "");
            String plaintext = decrypt(message, a, b);
            System.out.println("The decrypted text is: " + plaintext);
            // break;
        } else if (choice == 3) {
            //run brute force decrypt
            System.out.println("Please enter your ciphertext: ");
            scan.nextLine();
            message = scan.nextLine();

            message = message.toLowerCase().replaceAll("\\s", "_");;

            for (int k = 0; k < 27; k++) {
                //build arrays
                AArray.add(k);
                BArray.add(k);
                // System.out.println("A Numbers: " + AArray.get(k));
            }

            for (Iterator<Integer> iterator = AArray.iterator(); iterator.hasNext();) {
                //iterate through array list to remove even numbers and 13.
                Integer number = iterator.next();
                if (number % 2 == 0 || number == 13 || number == 0) {
                    iterator.remove();
                }

            }
            for (Iterator<Integer> iterator = BArray.iterator(); iterator.hasNext();) {
                //iterate through array list to remove even numbers and factors of 26
                Integer number = iterator.next();
                if (number == 0) {
                    iterator.remove();
                }

            }
            /*debug
             for (int z = 0; z < BArray.size(); z++) {
             System.out.println("B Numbers: " + BArray.get(z));
             }
             */
            String output = "";
            int count = 0;

            for (int y = 0; y < AArray.size(); y++) {
                for (int z = 0; z < BArray.size(); z++) {

                    try {
                        String plaintext = decrypt(message, AArray.get(y), BArray.get(z));
                        plaintext = plaintext.replaceAll("_", " ");
                        decryptedtext.add(plaintext);

                        count++;
                        //System.out.println("A: " + AArray.get(y) + " B: " + BArray.get(z) + " " + plaintext);
                        //System.out.println(count + ": " + plaintext);

                    } catch (Exception e) {
                        //System.out.println("error: " + e);
                    }

                }
            }
            System.out.println("Search for terms? "
                    + "\n 1. Yes \n 2. No");

            choiceB = scan.nextInt();

            if (choiceB == 1) {

                System.out.println("Enter Search Term: ");
                String searchterm = scan.next();
                int resultcounter = 0;
                for (Object decryptedtext1 : decryptedtext) {

                    if (decryptedtext1.toString().contains(searchterm)) {
                        resultcounter++;
                        System.out.println("Result " + resultcounter + ": " + decryptedtext1);
                    }
                }
            }
            if (choiceB == 2) {
                int resultcounter2 = 0;
                for (Object decryptedtext1 : decryptedtext) {
                    resultcounter2++;
                    
                    System.out.println("Result " + resultcounter2 + ": " + decryptedtext1);
                }
            } else {
                System.out.println("Program is now exiting...");
                System.exit(0);
            }

        } else if (choice == 4) {
            System.out.println("Program is now quitting...");
            System.exit(0);
        } else {
            System.out.println("Choose a valid option.\n");
        }

    }

    static String encrypt(String message, int a, int b) {
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char letter = message.charAt(i);
            if (Character.isLetter(letter)) {
                letter = (char) ((a * (letter - 'a') + b) % 26 + 'a');
            }
            build.append(letter);
        }
        return build.toString();
    }

    static String decrypt(String message, int a, int b) {
        StringBuilder build = new StringBuilder();
        BigInteger inverse = BigInteger.valueOf(a).modInverse(BigInteger.valueOf(26));
        for (int j = 0; j < message.length(); j++) {
            char letter = message.charAt(j);
            if (Character.isLetter(letter)) {
                int decrypted = inverse.intValue() * (letter - 'a' - b + 26);
                letter = (char) (decrypted % 26 + 'a');

            }
            build.append(letter);
        }
        return build.toString();
    }
}
