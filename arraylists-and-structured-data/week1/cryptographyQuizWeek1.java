
/**
 * week 1 quiz
 * 
 * @author Miguel Hernandez 
 * @version 1
 */
import edu.duke.*;


public class cryptographyQuizWeek1 {
    public void quizWeek1() {
        CaesarCipher cipher = new CaesarCipher();
        
        System.out.println("Question 1: Encrypt the following phrase " +
        "with the Caesar Cipher algorithm, using key 15. " +
        "Can you imagine life WITHOUT the internet AND computers in your pocket? \n" +
        "What is the encrypted string?");
        int keyQ1 = 15;
        
        String strQ1 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
    
        String encrypted = cipher.encrypt(strQ1, keyQ1);
        System.out.println("Encrypted string for Q1 is:");
        System.out.println(encrypted);
    
        System.out.println("\n Question 2: Encrypt the following phrase with the algorithm " +
        "described for using two Caesar Cipher keys, with key1 = 21 and key2 = 8. " + 
        "Can you imagine life WITHOUT the internet AND computers in your pocket? \n" +
        "What is the encrypted string?");
        
        int key1Q2 = 21;
        int key2Q2 = 8;
        
        encrypted = cipher.encryptTwoKeys(strQ1, key1Q2, key2Q2);
        System.out.println("Encrypted string for Q2 is: \n" + encrypted + "\n");
    
        System.out.println("Q4: Consider the file errors.txt. " +
        "What is the most common word length (ignoring the punctuation of the first and last " + 
        "character of each group of characters)?\n" + "Choose the file errors.txt");
        
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    
        System.out.println("Q5: same as Q4, but instead choose the file manywords.txt" + "\n");
        wl.testCountWordLengths();
    
        System.out.println("Q6: The following phrase was encrypted with the two-key encryption method " +
        "we discussed using the two keys 14 and 24. What is the decrypted message? " +
        "Hfs cpwewloj loks cd Hoto kyg Cyy.");
        
        int key1Q6 = 14;
        int key2Q6 = 24;
        encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String decrypted = cipher.encryptTwoKeys(encrypted, 26 - 14, 26 - 24);
        
        System.out.println("Decrypted message is: \n" + decrypted + "\n");
        
        System.out.println("Q7: The following phrase was encrypted with the two-key encryption method " +
        "described in this course. You will need to figure out which keys were used to encrypt it. " +
        "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx! \n" + "What is the original message?");
        
        encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        
        CaesarBreaker cb = new CaesarBreaker();
        decrypted = cb.decryptTwoKeys(encrypted);
        System.out.println("Original message is: \n" + decrypted + "\n");
        
        System.out.println("Q8: Decrypt the encrypted file mysteryTwoKeysQuiz.txt. " +
        "This file is encrypted with the two-key encryption method we discussed. " +
        "You’ll need to decrypt the complete file by figuring out which keys were used to decrypt it. \n" +
        "What are the first five decrypted words?\n" + "Choose the file mysteryTwoKeysQuiz.txt");
        
        FileResource fr = new FileResource();
        encrypted = fr.asString();
        decrypted = cb.decryptTwoKeys(encrypted);
        System.out.println(decrypted);
        System.out.println("Q9: get the two keys from Q8 (should print out through the last method call");
    }
}
