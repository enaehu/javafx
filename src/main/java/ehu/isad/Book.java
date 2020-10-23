package ehu.isad;

import ehu.isad.Details;

public class Book {

    private String title;
private String isbn;
private Details details;
private String thumbnail_url;
private  String info_url;
private  String bib_key;
private  String preview_url;

    @Override
    public String toString() {
        return title ;
    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIzena(){
           return title ;
    }
    public void setIzena(String izena) {
        this.title = izena;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
    public String getThumbnail_url() {
        return thumbnail_url;
    }
}
