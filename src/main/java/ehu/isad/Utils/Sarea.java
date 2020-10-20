package ehu.isad.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class Sarea {
//json-a lortu

    private static Gson gson;


    public static Book URLlortu(String s) throws Exception {
        String inputLine="";
        try{
            URL isbnWeb= new URL("https://openlibrary.org/api/books?bibkeys=ISBN:"+s+"&jscmd=details&format=json");
            URLConnection iw= isbnWeb.openConnection();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(iw.getInputStream()));
            inputLine=bufferedReader.readLine();
            bufferedReader.close();

            String[] zatitu;//{"ISBN:9781491906187":
            zatitu = inputLine.split("\"ISBN:"+s+"\": "); //{ jartzen bada errorea ematen du
            Gson gson=new Gson();
            bufferedReader.close();
            return gson.fromJson(inputLine,Book.class);


        }
        catch(IOException e){
            e.printStackTrace();
        }
        Gson gson=new Gson();
        return gson.fromJson(inputLine,Book.class);

    }
    }


