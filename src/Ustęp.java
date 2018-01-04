import java.util.ArrayList;

public class Ustęp implements IWezel {
    private ArrayList<Punkt> punkty;
    private ArrayList<String> tresc;
    private String identifier;

    public Ustęp(String id){
        this.punkty = new ArrayList<>();
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

    public void addPkt(Punkt pkt){
        this.punkty.add(pkt);
    }

    public Punkt getPkt(String id){
        for(Punkt p : this.punkty){
            if(p.getIdentifier().equals(id)) return p;
        }
        throw new IllegalArgumentException("Nie ma punktu o takim indeksie");
    }

    public void CzytajTresc(){
        System.out.print("Ust. "+this.identifier);
        if(!this.tresc.isEmpty()){
            for(String s : this.tresc) System.out.println(s);
        }
        if(!this.punkty.isEmpty()){
            for(Punkt p : this.punkty) p.CzytajTresc();
        }

    }

    public boolean jestPusty(){return this.punkty.isEmpty();}

    public void spis(){
        if(!jestPusty()){
            for(Punkt p : this.punkty){
                System.out.println("Punkt " + p.getIdentifier());
                p.spis();
            }
        }
    }

    public void czytajPkt(String id){
        if(!jestPusty()){
            for(Punkt p : this.punkty){
               if(p.getIdentifier().equals(id))p.CzytajTresc();
            }
        }
    }

    public void czytajLit(String PktId,String id){
        if(!jestPusty()){
            for(Punkt p : this.punkty){
                if(p.getIdentifier().equals(PktId))p.czytajLit(id);
            }
        }
    }
}
