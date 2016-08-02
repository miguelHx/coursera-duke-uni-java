public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    
    public PhraseFilter(String where, String phrase) {
        this.where = where;
        this.phrase = phrase;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String info = qe.getInfo();
        if (where == "start") {
            return (info.startsWith(phrase));
        }
        if (where == "end") {
            return (info.endsWith(phrase));
        }
        if (where == "any") {
            return (info.indexOf(phrase) != -1);
        }
        return false;
    }
    
    public String getName() {
        return "Phrase";
    }
}
