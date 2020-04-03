package a.caps;
import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;
import java.util.List;
import java.util.Map;
import java.util.Random;



public class Game {
    CountryDB db;



    public Game(){
        this.db=new CountryDB();
    }

    public String qa(){
        List<String> capitals=db.getCapitals();
        int n=capitals.size();
        int index=(int)(n*(Math.random())) ;

        String c=capitals.get(index);

        Map<String, Country> data=db.getData();
        Country ref=data.get(c);
        String question;


        if (Math.random() < 0.5){
            question =  "What is the capital of " + ref.getName() +"?" + "\n" + ref.getCapital();


        }
        else {
            question =  ref.getCapital() + " is the capital of?" + "\n" + ref.getName();
        }



        return question;
    }
}