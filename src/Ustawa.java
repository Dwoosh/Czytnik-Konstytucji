import java.util.ArrayList;

public class Ustawa {
    private ArrayList<Rozdział> rozdziały;

    public Ustawa(){
        this.rozdziały = new ArrayList<>();
    }

    public void addRozdział(Rozdział d){
        this.rozdziały.add(d);
    }

    public Rozdział getRozdział(String id){
        if(!jestPusty()) {
            for (Rozdział d : this.rozdziały) {
                if (d.getIdentifier().equals(id)) return d;
            }
        }
        throw new IllegalArgumentException("Nie ma dzialu o takim indeksie");
    }

    public boolean jestPusty(){return this.rozdziały.isEmpty();}

    public void spis(){
        if(!jestPusty()){
            for(Rozdział d : this.rozdziały){
                System.out.println("Rozdział " + d.getIdentifier());
                d.spis();
            }
        }
    }

    public void czytajPrzedzialArt(String lb, String rb){
        int jest = 0;
        if(!jestPusty()){
            for(Rozdział d : this.rozdziały){
                jest = d.czytajPrzedzialArt(lb,rb,jest);
            }
        }
    }

    public void CzytajRozdział(String id){
        boolean byl = false;
        if(!jestPusty()) {
            for (Rozdział d : this.rozdziały) {
                if (d.getIdentifier().equals(id)) {
                    byl = true;
                    d.CzytajTresc();
                }
            }
        }
        if(!byl)throw new IllegalArgumentException("Nie ma dzialu o takim indeksie");
    }

    public void czytajArt(String id){
        if(!jestPusty()){
            for(Rozdział d : this.rozdziały){
                d.czytajArt(id);
            }
        }
    }

    public void czytajUst(String ArtId,String id){
        if(!jestPusty()){
            for(Rozdział d : this.rozdziały){
                d.czytajUst(ArtId,id);
            }
        }
    }

    public void czytajPkt(String ArtId,String UstId,String id){
        if(!jestPusty()){
            for(Rozdział d : this.rozdziały){
                d.czytajPkt(ArtId,UstId,id);
            }
        }
    }

    public void czytajLit(String ArtId,String UstId,String PktId,String id){
        if(!jestPusty()){
            for(Rozdział d : this.rozdziały){
                d.czytajLit(ArtId,UstId,PktId,id);
            }
        }
    }

}
