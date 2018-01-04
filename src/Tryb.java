import java.util.Scanner;

public class Tryb {

    private String value;

    public Tryb(String i){
        this.value = i;
    }

    public void uruchomTryb(Ustawa u, String arg) throws IllegalArgumentException{
        if(this.value.equals("spis")){
            wypiszSpis(u);
        }
        else if(this.value.equals("tresc")){
            wypiszTresc(u,arg);
        }
        else throw new IllegalArgumentException("Argument trybu działania musi nazywać się 'spis' lub 'tresc'");
    }

    private void wypiszSpis(Ustawa u){
        if(!u.jestPusty()){
            u.spis();
        }
    }

    private void wypiszTresc(Ustawa u,String arg){
        String artID = "";
        String ustID = "";
        String pktID = "";
        String litID = "";
        PatternMatcher pat = new PatternMatcher();
        Scanner words = new Scanner(arg);
        String w = "";
        if(pat.argDział(arg)){
            words.next();
            w = words.next();
            u.CzytajRozdział(w);
        }
        else if(pat.argArtykulyPrzedzial(arg)){
            words.next();
            String lb = words.next();
            words.next();
            String rb = words.next();
            u.czytajPrzedzialArt(lb,rb);
        }
        else if(pat.argArtykulyLista(arg)){
            words.next();
            while(words.hasNext()){
                w = words.next();
                u.czytajArt(w);
            }
        }
        else if(pat.argMix(arg)){
            while(words.hasNext()){
                w = words.next();
                if(pat.jestArtykuł(w)){
                    artID = words.next();
                }
                if(words.hasNext())w = words.next();
                if(pat.argUstep(w)){
                    ustID = words.next();
                }
                if(words.hasNext())w = words.next();
                if(pat.argPkt(w)){
                    pktID = words.next();
                }
                if(words.hasNext())w = words.next();
                if(pat.argLit(w)){
                    litID = words.next();
                }
                if(!litID.isEmpty())u.czytajLit(artID,ustID,pktID,litID);
                else if(!pktID.isEmpty())u.czytajPkt(artID,ustID,pktID);
                else if(!ustID.isEmpty())u.czytajUst(artID,ustID);
                else u.czytajArt(artID);
            }
        }
        else {
            System.out.println("Przykład argumentów do odszukania:");
            System.out.println("Rozdział X lub Rozdział X");
            System.out.println("Art. 1. - 12.");
            System.out.println("Art. 2. 5. 6.");
            System.out.println("Art. 3. Ust. 5. Pkt 2) Lit a)");
            throw new IllegalArgumentException("Podano niewlasciwy argument");
        }
    }

}
