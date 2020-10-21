package ehu.isad;

import java.util.Arrays;

public class Details {
    //json-a banatu zatitan
    String[] publishers;
    Integer number_of_pages;
    String title;

    @Override
    public String toString() {
        return "Details{" +
                "publishers=" + Arrays.toString(publishers) +
                ", number_of_pages=" + number_of_pages +
                ", title='" + title + '\'' +
                '}';
    }

    public String getArgitaletxea() {
        return publishers[0];
    }

    public Integer getOrrikop() {
        return number_of_pages;
    }

    public String getIzenburua() {
        return title;
    }

}
