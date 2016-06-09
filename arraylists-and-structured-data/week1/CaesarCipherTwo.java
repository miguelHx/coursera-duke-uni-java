
/**
 * Assignment 2: Two Keys
 * 
In this assignment, you will put together the CaesarCipherTwo class that encrypts a message with 
two keys (the same way as the previous lesson: key1 is used to encrypt every other letter, 
starting with the first, and key2 is used to encrypt every other letter, starting with the second), 
and also decrypts the same message. In addition you will create a second class, TestCaesarCipherTwo 
to test examples that use the CaesarCipherTwo class, including writing a method that will automatically decrypt an 
encrypted file by determining the two keys that were used to encrypt it.
 * 
 * @author Miguel Hernandez
 * @version nill
 */
import edu.duke.*;

public class CaesarCipherTwo {
    
    private String alphabet;
    private String shiftedUpperKey1;
    private String shiftedLowerKey1;
    private String shiftedUpperKey2;
    private String shiftedLowerKey2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //make the lower case version of the alphabet
        String alphabetLower = alphabet.toLowerCase();
        shiftedUpperKey1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedLowerKey1 = shiftedUpperKey1.toLowerCase();
        
        shiftedUpperKey2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        shiftedLowerKey2 = shiftedUpperKey2.toLowerCase();
        
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {
        
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //Find the index of currChar in the lowercase alphabet (called it idxLower)
            int idxLower = alphabet.toLowerCase().indexOf(currChar);
            //If currChar is in the alphabet
            
            if(idx != -1){
                if (i % 2 == 0) {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedUpperKey1.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
                else {
                    char newChar2 = shiftedUpperKey2.charAt(idx);
                    encrypted.setCharAt(i, newChar2);
                }
            }
            //Otherwise: check if currChar is in the lowercase alphabet
            else if (idxLower != -1) {
                if (i % 2 == 0) {
                    //get the idxUpperth character of shifted lowerAlphabet
                    char newCharLower = shiftedLowerKey1.charAt(idxLower);
                    //Replace the ith character of encrypted with newCharLower
                    encrypted.setCharAt(i, newCharLower);
                }
                else {
                    char newCharLower2 = shiftedLowerKey2.charAt(idxLower);
                    encrypted.setCharAt(i, newCharLower2);
                }
            }
            
        }
        //Answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2); //object for decryption with inverse key
        return ccTwo.encrypt(input);
    }
    
}
