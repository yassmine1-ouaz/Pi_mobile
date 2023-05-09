/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Oeuvre;
import Service.ServiceOeuvre;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author octanet
 */

   // public class AjouterOeuvreForm extends Form {

   // Form current;

  /*  public AjouterOeuvreForm(Resources res, Form previous) {
        super("Ajouter une oeuvre", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });

        current = this;

        getTitleArea().setUIID("Container");

        getContentPane().setScrollVisible(false);

        // Champs de saisie pour les informations de l'oeuvre
        TextField titreOeuvre = new TextField("", "titreOeuvre", 20, TextField.ANY);
        TextField artiste = new TextField("", "artiste", 20, TextField.ANY);
       
        TextField image = new TextField("", "URL de l'image", 20, TextField.URL);
        TextField prixOeuvre = new TextField("", "prixOeuvre", 20, TextField.NUMERIC);
TextField etat = new TextField("", "etat", 20, TextField.ANY);
TextField famille = new TextField("", "famille", 20, TextField.ANY); 
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);

        // onClick button event 
        btnAjouter.addActionListener((e) -> {

            try {

                if (titreOeuvre.getText().equals("") || artiste.getText().equals("")|| prixOeuvre.getText().equals("")  || image.getText().equals("")|| famille.getText().equals("")|| etat.getText().equals("") ) {
                    Dialog.show("Veuillez vérifier les données", "", "Annuler", "OK");
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();

                    Oeuvre o = new Oeuvre(titreOeuvre.getText(), artiste.getText(),prixOeuvre.getText(), image.getText(), famille.getText(), etat.getText());
                    System.out.println("Données oeuvre : " + o);

                    // Appelle la méthode ajouterOeuvre() du service Oeuvre pour ajouter l'oeuvre dans la base de données
                    ServiceOeuvre.getInstance().ajoutOeuvre(o);

                    iDialog.dispose();

                    Dialog.show("Ajout avec succès", "L'oeuvre a été ajoutée avec succès.", "OK", null);

                    // Retour à la liste des oeuvres après l'ajout
                    ListOeuvreForm a = new ListOeuvreForm(res, current);
                    a.show();
                    refreshTheme();
                }

            } catch (NumberFormatException ex) {
                Dialog.show("Erreur", "Veuillez saisir un prix valide.", "OK", null);
            }
        });

        // Ajout des champs de saisie dans le formulaire
        addStringValue("titreOeuvre :", titreOeuvre);
        addStringValue("artiste :", artiste);
        addStringValue("prixOeuvre :", prixOeuvre);
        addStringValue("URL de l'image :", image);
        addStringValue("famille :", famille);
         addStringValue("etat :", etat);
    }

    // Ajoute une valeur au formulaire
    private void addStringValue(String s, Component v) {
        add(BorderLayout.
                west(new Label(s, "PaddedLabel")).
                center(v));
        add(createLineSeparator(0xeeeeee));
    }
}*/

    public class AjouterOeuvre extends Form {

    private final TextField titreOeuvreField, artisteField, prixOeuvreField, imageField, familleField, etatField;
    private final Button ajouterBtn;

    public AjouterOeuvre(Resources res) {
        super("Ajouter une oeuvre", BoxLayout.y());

          // Create text fields and a combo box for the oeuv
        titreOeuvreField = new TextField("", "Titre de l'oeuvre");
         titreOeuvreField.getStyle().setFgColor(154245);
        artisteField = new TextField("", "Nom de l'artiste");
         artisteField.getStyle().setFgColor(154245);
        prixOeuvreField = new TextField("", "Prix de l'oeuvre");
         prixOeuvreField.getStyle().setFgColor(154245);
        imageField = new TextField("", "URL de l'image");
         imageField.getStyle().setFgColor(154245);
        familleField = new TextField("", "Famille ");
         familleField.getStyle().setFgColor(154245);
        etatField = new TextField("", "Etat de l'oeuvre"); 
        etatField.getStyle().setFgColor(154245);

        ajouterBtn = new Button("Ajouter");

        ajouterBtn.addActionListener(e -> {
            
             // Validate the entered values
            try {
                String titreOeuvre = titreOeuvreField.getText();
                titreOeuvreField.getStyle().setFgColor(154245);
              String artiste = artisteField.getText();
                String prixOeuvre = prixOeuvreField.getText();
                String image = imageField.getText();
                String famille = familleField.getText();
                String etat = etatField.getText();
             
                
                // Check that the entered values are valid
               if (titreOeuvre.isEmpty() || artiste.isEmpty() || prixOeuvre.isEmpty() || image.isEmpty() || famille.isEmpty()|| etat.isEmpty()) {
                    Dialog.show("Veuillez vérifier les données", "", "Annuler", "OK");
                    
                    return;
                }
            
                // Create a new oeu with the entered information
             Oeuvre oeuvre = new Oeuvre();
            oeuvre.setTitreOeuvre(titreOeuvreField.getText());
            oeuvre.setArtiste(artisteField.getText());
            oeuvre.setPrixOeuvre(prixOeuvreField.getText());
            oeuvre.setImage(imageField.getText());
            oeuvre.setFamille(familleField.getText());
            oeuvre.setEtat(etatField.getText());

            ServiceOeuvre.getInstance().ajoutOeuvre(oeuvre);
              // new CvListForm().show();
             } catch (NumberFormatException ex) {
                // Handle the case where the entered values are not valid numbers
                Dialog.show("Error", "Please enter valid inputs", "OK", null);
            }
            
            ToastBar.showInfoMessage("Oeuvre ajoutée avec succès");
        });

        addAll(titreOeuvreField, artisteField, prixOeuvreField, imageField, familleField, etatField, ajouterBtn);
    }

    AjouterOeuvre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     
}


