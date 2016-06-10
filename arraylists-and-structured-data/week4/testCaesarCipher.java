
/**
 * Tester class for vigenere cipher
 * 
 * @author Miguel Hernandez
 * @version Nil Version
 */
import java.util.*;
import edu.duke.*;
public class Tester {
    public void testCaesarCipher() {
        int key = 13;
        CaesarCipher cc = new CaesarCipher(key);
        FileResource fr = new FileResource();
        
        String textFile = fr.asString();
        
        System.out.println("Testing encrypt and decrypt methods...");
        String encrypted = cc.encrypt(textFile);
        System.out.println("Encrypted message is \n" + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted message is \n" + decrypted);
        
        System.out.println("Testing encryptLetter and decryptLetter methods...");
        StringBuilder sb = new StringBuilder(textFile);
        System.out.println("Encrypting even indexed letters...");
        for (int i = 0; i < sb.length(); i++) { // encrypts even indexed letters with key above
            char currChar = sb.charAt(i);
            if (i % 2 == 0 && Character.isLetter(currChar)) {// even index and must be a letter, no spaces
                char encryptedLetter = cc.encryptLetter(currChar);
                sb.setCharAt(i, encryptedLetter);
            }
        }
        encrypted = sb.toString();
        System.out.println("Encrypted message with even indexed letters is \n" + encrypted);
        System.out.println("Decrypting even indexed letters...");
        int i = 0;
        while (i < sb.length()) { // does same as for loop above but is while loop and decrypts instead
            char currChar = sb.charAt(i);
            if (i % 2 == 0 && Character.isLetter(currChar)) { // even index and must be a letter, no space
                char decryptedLetter = cc.decryptLetter(currChar);
                sb.setCharAt(i, decryptedLetter);
            }
            i++;
        }
        
        decrypted = sb.toString();
        System.out.println("Decrypted even letters message is \n" + decrypted);
        
    }
    
    public void testVigenereCipher() {
        String key = "rome";
        int[] testKeys = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(testKeys);
        
        FileResource fr = new FileResource();
        String textFile = fr.asString();
        
        String encrypted = vc.encrypt(textFile);
        System.out.println("Encrypted file with Vigenere key \"rome\" is \n" + encrypted);
        
        String decrypted = vc.decrypt(encrypted);
        System.out.println("Decrypted file with Vigenere key \"rome\" is \n" + decrypted);
        
    }
    
    public void testsliceString() {
        VigenereBreaker vcb = new VigenereBreaker();
        String testStr = "abcdefghijklm";
        String test1 = vcb.sliceString(testStr, 0, 3);
        String test2 = vcb.sliceString(testStr, 1, 3);
        String test3 = vcb.sliceString(testStr, 2, 3);
        String test4 = vcb.sliceString(testStr, 0, 4);
        String test5 = vcb.sliceString(testStr, 1, 4);
        String test6 = vcb.sliceString(testStr, 2, 4);
        String test7 = vcb.sliceString(testStr, 3, 4);
        String test8 = vcb.sliceString(testStr, 0, 5);
        String test9 = vcb.sliceString(testStr, 1, 5);
        String test10 = vcb.sliceString(testStr, 2, 5);
        String test11 = vcb.sliceString(testStr, 3, 5);
        String test12 = vcb.sliceString(testStr, 4, 5);
        System.out.println("test1 should be adgjm. output is " + test1);
        System.out.println("test2 should be behk. output is " + test2);
        System.out.println("test3 should be cfil. output is " + test3);
        System.out.println("test4 should be aeim. output is " + test4);
        System.out.println("test5 should be bfj. output is " + test5);
        System.out.println("test6 should be cgk. output is " + test6);
        System.out.println("test7 should be dhl. output is " + test7);
        System.out.println("test8 should be afk. output is " + test8);
        System.out.println("test9 should be bgl. output is " + test9);
        System.out.println("test10 should be chm. output is " + test10);
        System.out.println("test11 should be di. output is " + test11);
        System.out.println("test12 should be ej. output is " + test12);
    }
    
    public void testMostCommonCharIn() {
        System.out.println("Choose a dictionary");
        FileResource fr = new FileResource();
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> dict = vb.readDictionary(fr);
        
        char mostCommonChar = vb.mostCommonCharIn(dict);
        System.out.println("Most common char is " + mostCommonChar);
    }
    
}
