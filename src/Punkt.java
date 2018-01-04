import java.util.ArrayList;

public class Punkt implements IWezel {
    private ArrayList<Litera> litery;
    private ArrayList<String> tresc;
    private String identifier;

    public Punkt(String id){
        this.litery = new ArrayList<>();
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

    public void addLit(Litera l){
        this.litery.add(l);
    }

    public Litera getLit(String id){
        for(Litera l : this.litery){
            if(l.getIdentifier().equals(id)) return l;
        }
        throw new IllegalArgumentException("Nie ma litery o takim indeksie");
    }

    public void CzytajTresc(){
        System.out.print(this.identifier);
        if(!this.tresc.isEmpty()){
            for(String s : this.tresc) System.out.println(s);
        }
        if(!this.litery.isEmpty()){
            for(Litera l : this.litery) l.CzytajTresc();
        }

    }

    public boolean jestPusty(){return this.litery.isEmpty();}

    public void spis(){
        if(!jestPusty()){
            for(Litera l : this.litery){
                System.out.println("Litera " + l.getIdentifier());
            }
        }
    }

    public void czytajLit(String id){
        if(!jestPusty()){
            for(Litera l : this.litery){
                if(l.getIdentifier().equals(id))l.CzytajTresc();
            }
        }
    }

}
