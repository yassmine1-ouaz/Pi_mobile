/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author octanet
 */
public class Oeuvre {
    private int id  ;
  private String titreOeuvre ,artiste ,  prixOeuvre ,image  ;
  private String etat , famille;

    public Oeuvre() {
    }

    public Oeuvre(int id, String titreOeuvre, String artiste, String prixOeuvre, String image, String etat, String famille) {
        this.id = id;
        this.titreOeuvre = titreOeuvre;
        this.artiste = artiste;
        this.prixOeuvre = prixOeuvre;
        this.image = image;
        this.etat = etat;
        this.famille = famille;
    }

    public Oeuvre(String titreOeuvre, String artiste, String prixOeuvre, String image, String etat, String famille) {
        this.titreOeuvre = titreOeuvre;
        this.artiste = artiste;
        this.prixOeuvre = prixOeuvre;
        this.image = image;
        this.etat = etat;
        this.famille = famille;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitreOeuvre() {
        return titreOeuvre;
    }

    public void setTitreOeuvre(String titreOeuvre) {
        this.titreOeuvre = titreOeuvre;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getPrixOeuvre() {
        return prixOeuvre;
    }

    public void setPrixOeuvre(String prixOeuvre) {
        this.prixOeuvre = prixOeuvre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    @Override
    public String toString() {
        return "Oeuvre{" + "titreOeuvre=" + titreOeuvre + ", artiste=" + artiste + ", prixOeuvre=" + prixOeuvre + ", image=" + image + ", etat=" + etat + ", famille=" + famille + '}';
    }

 
  

  
   
  
  
  
  
}
