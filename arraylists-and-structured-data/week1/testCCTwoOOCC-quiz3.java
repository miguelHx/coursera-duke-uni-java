
/**
 * Tester class
 * 
 * @author Miguel Hernandez 
 * @version nill m8
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    private int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alph.indexOf(ch);
            if(index != -1) {
                counts[index] += 1;
            }
        }
        return counts;
    }
    
    private int maxIndex(int[] iarr) {
        int maxIndex = 0;
        for (int i = 0; i < iarr.length; i++) {
            if (iarr[i] > iarr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    private String halfOfString(String message, int start) {
        StringBuilder msg = new StringBuilder(message);
        StringBuilder half = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char currChar = msg.charAt(i);
            int idx = message.indexOf(start);
            if ((start + i) < message.length()) {
                if (i % 2 == 0) {
                    char newChar = message.charAt(start + i);
                    half.append(newChar);
                }
            }
        }
        return half.toString();
    
    }
    
    private int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxIndex = maxIndex(freqs);
        int decryptKey = maxIndex - 4;
        if (maxIndex < 4) {
            decryptKey = 26 - (4-maxIndex);
        }
    
        return decryptKey;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String test = fr.asString();
        int key1 = 17;
        int key2 = 3;
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(key1, key2);
        
        System.out.println("String to be encrypted: \n" + test);
        String encrypted = ccTwo.encrypt(test);
        System.out.println("String after encryption: \n" + encrypted);
        
        System.out.println("Now, to decrypt the message...");
        String decrypted = ccTwo.decrypt(encrypted);
        System.out.println("Decrypted message: \n" + decrypted);
        
        System.out.println("Choose an encrypted file to convert to string and use breakCaesarCipher to figure out the keys");
        FileResource fr2 = new FileResource();
        String test2 = fr2.asString();
        System.out.println("Calling breakCaesarCipher...");
        breakCaesarCipher(test2);
        
    }
    
    public void breakCaesarCipher(String input) {
        String half1 = halfOfString(input, 0);
        String half2 = halfOfString(input, 1);
        
        int keyHalf1 = getKey(half1);
        int keyHalf2 = getKey(half2);
        
        System.out.println("Key for " + half1 + " is " + keyHalf1);
        System.out.println("Key for " + half2 + " is " + keyHalf2);
        
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(keyHalf1, keyHalf2);
        String message = ccTwo.decrypt(input);
        System.out.println("Decrypted message is: \n" + message);
    }
    
}
