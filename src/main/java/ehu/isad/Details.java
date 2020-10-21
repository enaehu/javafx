package ehu.isad;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Details {
    String[] publishers = new String[10];
    Integer number_of_pages;
    String title;
    private int pubKop = 0;

    public Details(int n, String s){
        number_of_pages=n;
        sartuPublisher(s); //Suposatuko dugu publisher bakarra duela
    }

    @Override
    public String toString() {
        return "Details{" +
                "publishers=" + Arrays.toString(publishers) +
                ", number_of_pages=" + number_of_pages +
                ", title='" + title + '\'' +
                '}';
    }

    public String getArgitaretxea() {
        return publishers[0];
    }

    public String getTitle() {
        return title;
    }

    public Integer getPages(){
        return number_of_pages;
    }

    private void sartuPublisher(String s){
        publishers[pubKop] = s;
        pubKop++;
    }
}