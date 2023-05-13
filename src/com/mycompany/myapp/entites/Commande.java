/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

import java.util.Date;

/**
 *
 * @author wassimnsiri
 */
public class Commande {
        private int id,quantite;
    private String nom,marque,prix,image;
    private Date daate_achat;
    public Commande(int id, int quantite, String nom, String marque, String prix, String image, Date daate_achat) {
        this.id = id;
        this.quantite = quantite;
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.image = image;
        this.daate_achat = daate_achat;
    }

    public Commande(int quantite, String nom, String marque, Date daate_achat, String image, String prix) {
         this.quantite = quantite;
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.image = image;
        this.daate_achat = daate_achat; //To change body of generated methods, choose Tools | Templates.
    }

    public Commande(int id, int quantite,  String nom, String marque, Date daate_achat, String image, String prix) {
          this.id = id;
        this.quantite = quantite;
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.image = image;
        this.daate_achat = daate_achat;
    }

    public Commande(int id , int quantite, String marque, String image, String prix, String nom) {
         this.id = id;
        this.quantite = quantite;
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.image = image; 
    }

    public Commande(int quantite, String marque, String prix, Date daate_achat, String nom) {
         this.quantite = quantite;
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
         this.daate_achat = daate_achat;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDaate_achat() {
        return daate_achat;
    }

    public void setDaate_achat(Date daate_achat) {
        this.daate_achat = daate_achat;
    }

    public Commande(int quantite, String nom, String marque, String prix, String image, Date daate_achat) {
        this.quantite = quantite;
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.image = image;
        this.daate_achat = daate_achat;
    }

    public Commande() {
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", quantite=" + quantite + ", nom=" + nom + ", marque=" + marque + ", prix=" + prix + ", image=" + image + ", daate_achat=" + daate_achat + '}';
    }
   
}
