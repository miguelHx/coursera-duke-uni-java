
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);//copies values from a source array to a destination array
    }

    public String wordAt(int index) { // this method returns word at specified index
        if (index < 0 || index >= myWords.length) { // if index isn't valid, an exception is thrown
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for (int k=0; k < myWords.length; k++) {
            ret += myWords[k] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        // compare me to other
        if (this.length() != other.length()) {
            return false;
        }
        for (int k = 0; k < myWords.length; k++) {
            if (! myWords[k].equals(other.wordAt(k))) {
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) {	
        //WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        String[] shifted = new String[myWords.length];
        
        for (int i = 0; i < myWords.length; i++) {
            if (i == myWords.length-1) {
                shifted[i] = word;
            }
            else {
                String nextWord = myWords[i+1];
                shifted[i] = nextWord;
            }
        }
        WordGram out = new WordGram(shifted, 0, myWords.length);
        
        return out;
    }
    
    public int hashCode() {
        String gramContents = this.toString();
        int hashNum = gramContents.hashCode();
        return hashNum;
    }

}