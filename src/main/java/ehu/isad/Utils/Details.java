package ehu.isad.Utils;

import java.util.Arrays;

public class Details {
    //json-a banatu zatitan
    String[] argitaletxea;
    Integer orrikop;
    String izenburua;

    @Override
    public String toString() {
        return "Details{" +
                "publishers=" + Arrays.toString(argitaletxea) +
                ", number_of_pages=" + orrikop +
                ", title='" + izenburua + '\'' +
                '}';
    }

    public String getArgitaletxea() {
        return argitaletxea[0];
    }

    public Integer getOrrikop() {
        return orrikop;
    }

    public String getIzenburua() {
        return izenburua;
    }

}
