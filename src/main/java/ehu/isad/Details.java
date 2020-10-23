package ehu.isad;

import java.util.Arrays;

public class Details {
    //json-a banatu zatitan
    String[] publishers= new String[4];
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

    public void setNumber_of_pages(Integer number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public void setPublishers(String publishers) {
        this.publishers[0] = publishers;
    }
}
