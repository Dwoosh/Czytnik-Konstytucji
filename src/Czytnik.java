import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Czytnik {

    private Scanner plik;
    private Ustawa ustawa;

    public Czytnik(String sciezka) throws FileNotFoundException {
        File file = new File(sciezka);
        if(!file.exists() && !file.isFile() && !file.canRead())
            throw new FileNotFoundException("File does not exist or cannot be read");
        this.plik = new Scanner(file);
        this.ustawa = new Ustawa();
    }

    public String GetLine(){
        return this.plik.nextLine();
    }

    public boolean IsLine() {
        return this.plik.hasNextLine();
    }

    public Ustawa getUstawa() {
        return ustawa;
    }

    public void CzytajIZapisz() throws IllegalArgumentException{
        PatternMatcher pat = new PatternMatcher();         //numery dzialu,artykulu itd i po prostu dolaczac do obiektu pod tym numerem
        String dzialID = "";
        String artID = "";
        String ustID = "";
        String pktID = "";
        String litID = "";
        boolean ignoreRoz = false;
        boolean doDzialu = false;
        boolean doArt = false;
        boolean doUst = false;
        boolean doPkt = false;
        boolean doLit = false;
        boolean bylUst = false;
        boolean bylPkt = false;
        int lineInd = -1;
        while(IsLine()){                                //dopoki jest linia
            String linia = GetLine();                   //pobiera linie
            if(!pat.isDump(linia)) {                    //jesli linia zawiera cos znaczacego
                lineInd++;
                Scanner wordGetter = new Scanner(linia);       //scanner do linii
                while(wordGetter.hasNext()) {                  //dopoki w linii sa slowa
                    String word = wordGetter.next();            //pobiera slowo
                    if(pat.jestDział(word)){                    //jesli jest dzial
                        doDzialu = true;                        //ustawia odpowiednie boole
                        doArt = false;
                        doUst = false;
                        doPkt = false;
                        doLit = false;
                        ignoreRoz = true;
                        word = wordGetter.next();
                        dzialID = word;                         //zapisuje id działu
                        if(wordGetter.hasNext())lineInd = 0;
                        else lineInd = -1;
                        this.ustawa.addRozdział(new Rozdział(dzialID));     //tworzy nowy dzial
                        continue;
                    }
                    if(pat.jestRozdział(word) && !ignoreRoz){       //podobnie dla rozdzialu i innych
                        doDzialu = true;
                        doArt = false;
                        doUst = false;
                        doPkt = false;
                        doLit = false;
                        word = wordGetter.next();
                        dzialID = word;
                        if(wordGetter.hasNext())lineInd = 0;
                        else lineInd = -1;
                        this.ustawa.addRozdział(new Rozdział(dzialID));
                        continue;
                    }
                    if(pat.jestArtykuł(word)){
                        doDzialu = false;
                        doArt = true;
                        doUst = false;
                        doPkt = false;
                        doLit = false;
                        bylUst = false;
                        bylPkt = false;
                        word = wordGetter.next();
                        artID = word;
                        if(wordGetter.hasNext())lineInd = 0;
                        else lineInd = -1;
                        this.ustawa.getRozdział(dzialID).addArt(new Artykuł(artID));
                        continue;
                    }
                    if(pat.jestUstęp(linia,word)){
                        doDzialu = false;
                        doArt = false;
                        doUst = true;
                        doPkt = false;
                        doLit = false;
                        bylUst = true;
                        bylPkt = false;
                        ustID = word;
                        if(wordGetter.hasNext())lineInd = 0;
                        else lineInd = -1;
                        this.ustawa.getRozdział(dzialID).getArt(artID).addUst(new Ustęp(ustID));
                        continue;
                    }
                    if(pat.jestPunkt(word) && bylUst){
                        doDzialu = false;
                        doArt = false;
                        doUst = false;
                        doPkt = true;
                        doLit = false;
                        bylPkt = true;
                        pktID = word;
                        if(wordGetter.hasNext())lineInd = 0;
                        else lineInd = -1;
                        this.ustawa.getRozdział(dzialID).getArt(artID).getUst(ustID).addPkt(new Punkt(pktID));
                        continue;
                    }
                    if(pat.jestLitera(word) && bylPkt){
                        doDzialu = false;
                        doArt = false;
                        doUst = false;
                        doPkt = false;
                        doLit = true;
                        litID = word;
                        if(wordGetter.hasNext())lineInd = 0;
                        else lineInd = -1;
                        this.ustawa.getRozdział(dzialID).getArt(artID).getUst(ustID).getPkt(pktID).addLit(new Litera(litID));
                        continue;
                    }
                    if(doDzialu){          //jesli znaleziono dzial to tekst dodajemy do dzialu
                        this.ustawa.getRozdział(dzialID).dodajTresc(word,lineInd);
                    }
                    if(doArt){      //podobnie do artykulu i innych
                        this.ustawa.getRozdział(dzialID).getArt(artID).dodajTresc(word,lineInd);
                    }
                    if(doUst){
                        this.ustawa.getRozdział(dzialID).getArt(artID).getUst(ustID).dodajTresc(word,lineInd);
                    }
                    if(doPkt){
                        this.ustawa.getRozdział(dzialID).getArt(artID).getUst(ustID).getPkt(pktID).dodajTresc(word,lineInd);
                    }
                    if(doLit){
                        this.ustawa.getRozdział(dzialID).getArt(artID).getUst(ustID).getPkt(pktID).getLit(litID).dodajTresc(word,lineInd);
                    }
                }
            }
        }
    }
}
