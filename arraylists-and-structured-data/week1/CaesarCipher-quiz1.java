
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //make the lower case version of the alphabet
        String alphabetLower = alphabet.toLowerCase();
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //Make a lower case version of the shifted alphabet
        String shiftedLower = shiftedAlphabet.toLowerCase();
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //Find the index of currChar in the lowercase alphabet (called it idxLower)
            int idxLower = alphabetLower.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: check if currChar is in the lowercase alphabet
            else if (idxLower != -1) {
                //get the idxUpperth character of shifted lowerAlphabet
                char newCharLower = shiftedLower.charAt(idxLower);
                //Replace the ith character of encrypted with newCharLower
                encrypted.setCharAt(i, newCharLower);
            }
            
        }
        //Answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public void testCaesar() {
        /*int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        */
       System.out.println("Testing the encryption to handle upper and lower case");
       System.out.println("Inputting 'First Legion' with the key 23");
       System.out.println();
       System.out.println("Result should be Cfopq Ibdflk.  Result is " + encrypt("First Legion", 23));
       System.out.println();
       
       System.out.println("Quiz question #5, encrypt with the key 15 of 'At noon be in the conference room with your hat on for a surprise party. YELL LOUD!'");
       System.out.println("Result is " + encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
           
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //make the lower case version of the alphabet
        String alphabetLower = alphabet.toLowerCase();
        String shiftedUpperKey1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedLowerKey1 = shiftedUpperKey1.toLowerCase();
        
        String shiftedUpperKey2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        String shiftedLowerKey2 = shiftedUpperKey2.toLowerCase();
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //Find the index of currChar in the lowercase alphabet (called it idxLower)
            int idxLower = alphabetLower.indexOf(currChar);
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
    
    public void testEncryptTwoKeys() {
        System.out.println("String is 'First Legion,' key 1 is 23 and key 2 is 17");
        System.out.println("Expected result is Czojq Ivdzle");
        System.out.println();
        System.out.println("Testing encryptTwoKeys...");
        System.out.println("Result is " + encryptTwoKeys("First Legion", 23, 17));
        System.out.println();
        
        System.out.println("Quiz question #6, encrypt with two keys, key1 = 8 and key2 = 21");
        System.out.println("String being encrypted is 'At noon be in the conference room with your hat on for a surprise party. YELL LOUD!'");
       System.out.println("Result is " + encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
       
    }
    
    
}

