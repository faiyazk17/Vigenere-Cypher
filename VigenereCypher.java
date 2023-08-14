
//importing entire java.util library for future reference
import java.util.*;

/**
 * Represents the Vigenere Cypher
 *
 * @author Faiyaz Kazi
 * @version 1.0
 * @since Friday, September 16, 2022
 */
public class VigenereCypher {
    public static void main(String[] args) {

        // creating scanner object for future use
        Scanner in = new Scanner(System.in);

        // input from user for plaintext
        System.out.print("Enter message: ");
        String plaintext = in.nextLine();

        // input from user for keyword
        System.out.print("Enter keyword: ");
        String keyword = in.nextLine();

        // capitalizing and removing all non-letters from plaintext and keyword
        plaintext = plaintext.replaceAll("[^a-zA-Z]", "").toUpperCase();
        keyword = keyword.replaceAll("[^a-zA-Z]", "").toUpperCase();

        // calling the encryption of the plaintext using the keyword
        encryption(plaintext, keyword);
    }

    /**
     * This is the encryption of the plaintext using the keyword.
     *
     * @param plaintext The message to be encrypted.
     * @param keyword   The key to encrypting the message.
     */
    public static void encryption(String plaintext, String keyword) {

        // creating char objects for future use
        char[] plaintextArray = plaintext.toCharArray(); // Converting plaintext to char array for future reference
        char[] keywordLengthened = new char[plaintextArray.length]; // char array for plaintext-lengthened keyword
        char[] plaintextEncrypted = new char[plaintextArray.length]; // char array for encrypted plaintext

        // expanding keyword length to match the length of the plaintext by looping
        // through the
        // letters in the keyword and adding them to the keyword
        for (int i = 0, j = 0; i < plaintextArray.length; i++, j++) {
            // to make sure the keyword can be repeated
            if (j == keyword.length()) {
                j = 0;
            }
            // adding each char of the keyword in a char array until it reaches the length
            // of the plaintext
            keywordLengthened[i] = keyword.charAt(j);
        }

        // encrypting the plaintext by looping through each char of the plaintext, and
        // it's corresponding keyword char
        for (int i = 0; i < plaintextArray.length; i++) {
            plaintextEncrypted[i] = (char) (((plaintextArray[i] + keywordLengthened[i]) % 26) + 'A');
        }

        // outputting encrypted message
        System.out.println("Encrypted Message: " + String.valueOf(plaintextEncrypted));
    }
}