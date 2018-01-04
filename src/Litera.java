import java.util.ArrayList;

public class Litera {
    private ArrayList<String> tresc;
    private String identifier;

    public Litera(String id){
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

    public void CzytajTresc(){
        System.out.print(this.identifier);
        if(!this.tresc.isEmpty()){
            for(String s : this.tresc) System.out.println(s);
        }
    }
}
