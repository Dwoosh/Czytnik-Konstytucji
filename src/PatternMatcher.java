import java.util.regex.Pattern;

public class PatternMatcher {

    public boolean jestRozdział(String linia){
        return (Pattern.matches("Rozdział",linia)); //np Rozdział
    }

    public boolean jestDział(String linia){
        return Pattern.matches("DZIAŁ",linia);
    }

    public boolean jestArtykuł(String linia){
        return (Pattern.matches("Art\\.",linia));   //np Art.
    }

    public boolean jestUstęp(String linia, String word){
        return (Pattern.matches("\\A\\d+\\.",word) && !(Pattern.matches(".*\\d+\\.$",linia)));   //np 2. na poczatku inputu ale nie wystepuje na koncu linii
    }

    public boolean jestPunkt(String linia){
        return (Pattern.matches("\\A\\d+\\)",linia));   //np 1) na poczatku inputu
    }

    public boolean jestLitera(String linia){
        return (Pattern.matches("\\A[a-zA-Z]{1}\\)",linia));   //np a) na poczatku inputu
    }
    public boolean isDump(String linia){
        return  (Pattern.matches("©Kancelaria.*",linia) || Pattern.matches("\\d{4}-\\d{2}-\\d{2}",linia) || Pattern.matches("\\w",linia));
    }

    public boolean argDział(String linia){
        return (Pattern.matches("^Rozdział\\p{Blank}\\w+",linia) || Pattern.matches("\\ADział\\p{Blank}\\w+",linia));
    }

    public boolean argArtykulyPrzedzial(String linia){
        return (Pattern.matches("Art\\.\\p{Blank}\\d+\\.\\p{Blank}-\\p{Blank}\\d+\\.",linia)); //np. Art. 2. - 12.
    }

    public boolean argArtykulyLista(String linia){
        return Pattern.matches("Art\\.(\\p{Blank}\\d+\\.)+",linia); //np. Art. 1. 2. 4.
    }

    public boolean argUstep(String linia){
        return Pattern.matches("Ust\\.",linia);
    }

    public boolean argPkt(String linia){
        return Pattern.matches("Pkt\\.",linia);
    }

    public boolean argLit(String linia){
        return Pattern.matches("Lit\\.",linia);
    }

    public boolean argMix(String linia){ //np. Art. 1. Ust. 3. Pkt 4) Lit a)
        return Pattern.matches("Art\\. \\d+\\.( Ust\\. \\d+\\.( Pkt\\. \\d+\\)( Lit\\. \\w\\))?)?)?",linia);
    }

}
