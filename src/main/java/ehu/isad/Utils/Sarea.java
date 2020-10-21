package ehu.isad.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLOutput;

import com.google.gson.Gson;
import ehu.isad.Book;

public class Sarea {
//json-a lortu

    private static Gson gson;

        public static Book URLlortu(String ISBN) throws Exception {
            URL openlibrary = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:"+ISBN+"&jscmd=details&format=json");
            URLConnection yc = openlibrary.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = in.readLine();
            in.close();

            String[] zatiak = inputLine.split("ISBN:"+ISBN+"\":");
            String emaitza = zatiak[1].substring(1, zatiak[1].length()-1);//LEHENENGO HUTSUNEA ETA AZKENEKO GILTZA KENTZEKO
            System.out.println("EM "+emaitza);
            Gson gson = new Gson();
            return gson.fromJson(emaitza, Book.class);
        }
    }



