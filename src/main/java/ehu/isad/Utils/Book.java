package ehu.isad.Utils;

public class Book {
private String izena;
private String isbn;
private Details xehetasunak;
private String thumbnail_url;
private  String info_url;
private  String bib_key;
private  String preview_url;

    @Override
    public String toString() {
        return izena ;
    }

    public Book(String izena, String isbn) {
        this.izena = izena;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIzena(){
           return izena ;
    }
    public void setIzena(String izena) {
        this.izena = izena;
    }

    public Details getXehetasunak() {
        return xehetasunak;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }
}
