import edu.duke.*;

public class WordPlay {
    public boolean isVowel(char ch) {
        String vowels = "aeiou";
        String s = Character.toString(ch);
        s = s.toLowerCase();
        for (int i = 0; i < vowels.length(); i++) {
            int index = vowels.indexOf(i);
            if (s.equals(vowels.substring(i, i+1))) {
                return true;
            }
    
        }
        return false;
    }
    
    public void testIsVowel() {
        System.out.println("Testing char 'F' for vowel");
        if (isVowel('F')) {
            System.out.println("True");
        }
        else {
            System.out.println("False");
        }
        System.out.println("Testing char 'a' for vowel");
        if (isVowel('a')) {
            System.out.println("True");
        }
        else {
            System.out.println("False");
        }
    
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder phraseInput = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = phraseInput.charAt(i);
            
            if (isVowel(currChar)) { // if the character is a vowel
                //replace the ith character with the character from the parameter
                phraseInput.setCharAt(i, ch);
            }
        }
        return phraseInput.toString();
    
    }
    
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*'));
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder phraseInput = new StringBuilder(phrase);
        String phraseLower = phrase.toLowerCase();
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = phraseLower.charAt(i);
            int index = phraseLower.indexOf(ch);
            
            if (currChar == ch) { 
                if ((i+1)%2 == 0){// even number
                    phraseInput.setCharAt(i, '+');
                }
                else {
                phraseInput.setCharAt(i, '*');
                }
            }
       }
        
        return phraseInput.toString();
    }
    
    public void testEmphasize() {
        System.out.println("Testing emphasize using string “dna ctgaaactga” with char 'a'");
        System.out.println();
        System.out.println("Result is: " + emphasize("dna ctgaaactga", 'a'));
        System.out.println("Result should be dn* ctg+*+ctg+");
        System.out.println();
        
        System.out.println("Test 2 with “Mary Bella Abracadabra” and char ‘a’ ");
        System.out.println();
        System.out.println("Result is: " + emphasize("Mary Bella Abracadabra", 'a'));
        System.out.println("Result should be M+ry Bell+ +br*c*d*br+");
        System.out.println();
        
    }
    
    
    
    
    
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
}



