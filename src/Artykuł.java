import java.util.ArrayList;

public class Artykuł implements IWezel {
    private ArrayList<Ustęp> ustępy;
    private ArrayList<String> tresc;
    private String identifier;

    public Artykuł(String id){
        this.ustępy = new ArrayList<>();
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

    public void addUst(Ustęp u){
        this.ustępy.add(u);
    }

    public Ustęp getUst(String id){
        for(Ustęp u : this.ustępy){
            if(u.getIdentifier().equals(id)) return u;
        }
        throw new IllegalArgumentException("Nie ma ustepu o takim indeksie");
    }

    public void CzytajTresc(){
        System.out.println("Artykuł "+this.identifier);
        if(!this.tresc.isEmpty()){
            for(String s : this.tresc) System.out.println(s);
        }
        if(!this.ustępy.isEmpty()){
            for(Ustęp u : this.ustępy) u.CzytajTresc();
        }

    }

    public boolean jestPusty(){return this.ustępy.isEmpty();}

    public void spis(){
        if(!jestPusty()){
            for(Ustęp u : this.ustępy){
                System.out.println("Ustęp " + u.getIdentifier());
                u.spis();
            }
        }
    }

    public void czytajUst(String id){
        if(!jestPusty()){
            for(Ustęp u : this.ustępy){
                if(u.getIdentifier().equals(id))u.CzytajTresc();
            }
        }
    }

    public void czytajPkt(String UstId,String id){
        if(!jestPusty()){
            for(Ustęp u : this.ustępy){
                if(u.getIdentifier().equals(UstId))u.czytajPkt(id);
            }
        }
    }

    public void czytajLit(String UstId,String PktId,String id){
        if(!jestPusty()){
            for(Ustęp u : this.ustępy){
                if(u.getIdentifier().equals(UstId))u.czytajLit(PktId,id);
            }
        }
    }
}
