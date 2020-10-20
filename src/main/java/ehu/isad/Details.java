package ehu.isad;

import java.util.Arrays;

public class Details {
    String[] publishers;
    Integer number_of_pages;
    String title;

    public Details(int n, String s){
        number_of_pages=n;
        publishers[0] = s; //Suposatuko dugu publisher bakarra duela
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
}