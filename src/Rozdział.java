import java.util.ArrayList;

public class Rozdział implements IWezel {
    private ArrayList<Artykuł> artykuły;
    private ArrayList<String> tresc;
    private String identifier;

    public Rozdział(String id){
        this.artykuły = new ArrayList<>();
        this.tresc = new ArrayList<>();
        this.identifier = id;
    }

    public void dodajTresc(String linia, int index){
        String line = "";
        if(this.tresc.size()-1 >= index)line = this.tresc.get(index);
        line = line + " " + linia;
        if(this.tresc.size()-1 >= index)this.tresc.set(index,line);
        else this.tresc.add(index,line);
    }

    public String getIdentifier(){return this.identifier;}

    public void addArt(Artykuł art){
        this.artykuły.add(art);
    }

    public Artykuł getArt(String id){
        for(Artykuł a : this.artykuły){
            if(a.getIdentifier().equals(id)) return a;
        }
        throw new IllegalArgumentException("Nie ma artykulu o takim indeksie");
    }

    public void CzytajTresc(){
        System.out.println("Rozdział: " + this.identifier);
        if(!this.tresc.isEmpty()){
            for(String s : this.tresc) System.out.println(s);
        }
        if(!this.artykuły.isEmpty()){
            for(Artykuł a : this.artykuły) a.CzytajTresc();
        }

    }

    public boolean jestPusty(){return this.artykuły.isEmpty();}

    public void spis(){
        if(!jestPusty()){
            for(Artykuł a : this.artykuły){
                System.out.println("Artykuł " + a.getIdentifier());
                a.spis();
            }
        }
    }

    public int czytajPrzedzialArt(String lb, String rb, int jest){
        if(!jestPusty()){
            for(Artykuł a : this.artykuły){
                if(a.getIdentifier().equals(lb) || (jest == 1)){
                    jest = 1;
                    a.CzytajTresc();
                }
                if(a.getIdentifier().equals(rb)) jest = 2;
            }
        }
        return jest;
    }

    public void czytajArt(String id){
        if(!jestPusty()){
            for(Artykuł a : this.artykuły){
                if(a.getIdentifier().equals(id))a.CzytajTresc();
            }
        }
    }

    public void czytajUst(String ArtId,String id){
        if(!jestPusty()){
            for(Artykuł a : this.artykuły){
                if(a.getIdentifier().equals(ArtId))a.czytajUst(id);
            }
        }
    }

    public void czytajPkt(String ArtId,String UstId,String id){
        if(!jestPusty()){
            for(Artykuł a : this.artykuły){
                if(a.getIdentifier().equals(ArtId))a.czytajPkt(UstId,id);
            }
        }
    }

    public void czytajLit(String ArtId,String UstId,String PktId,String id){
        if(!jestPusty()){
            for(Artykuł a : this.artykuły){
                if(a.getIdentifier().equals(ArtId))a.czytajLit(UstId,PktId,id);
            }
        }
    }

}
