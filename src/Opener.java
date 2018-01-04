public class Opener{

    public static void main(String[] args){
        try{
            String sciezka = args[0];
            String tryb = args[1];
            String argumenty = args[2];
            Czytnik czytnik = new Czytnik(sciezka);
            czytnik.CzytajIZapisz();                        //glowna metoda ktora przeksztalca tekst na obiekty
            Tryb t = new Tryb(tryb);
            t.uruchomTryb(czytnik.getUstawa(),argumenty);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
