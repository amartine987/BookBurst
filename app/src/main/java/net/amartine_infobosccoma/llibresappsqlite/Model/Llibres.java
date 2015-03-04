package net.amartine_infobosccoma.llibresappsqlite.Model;

import java.io.Serializable;


/**
 * Created by aitor on 12/02/2015.
 */
public class Llibres implements Serializable {
    private int id;
    private String nom;
    private String tipus;
    //private int preu;
    private String autor;
    private String descripcio;
    private int img;
    private int rating;

    public Llibres(){

    }
    public Llibres(int id, String nom,String autor, String tipus,String descripcio, int img, int rating){
        this.id = id;
        this.nom = nom;
        this.autor = autor;
        this.tipus = tipus;
        // this.preu = preu;
        this.descripcio = descripcio;
        this.img = img;
        this.rating = rating;
    }



    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;

    }
    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;

    }
    public String getNom(){
        return nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public String getTipus(){
        return tipus;
    }
    public void setTipus(String tipus){
        this.tipus = tipus;
    }
    //    public int getPreu(){
//        return preu;
//    }
//    public void setPreu(int preu){
//        this.preu = preu;
//    }
    public int getImg(){
        return img;
    }
    public void setImg(int img){
        this.img = img;
    }
    public String getDescripcio(){
        return descripcio;
    }
    public void setDescripcio(String descripcio){
        this.descripcio = descripcio;
    }
    public String toString(){
        return "Id: " + id + "Nom: " + nom + "Tipus: " + tipus + "Autor :" + autor + "Image: " + img + "Ratting" + rating;
    }
}
