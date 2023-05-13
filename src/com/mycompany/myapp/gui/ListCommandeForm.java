/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entites.Commande;
import com.mycompany.myapp.services.ServicesCommande;
import java.util.ArrayList;

/**
 *
 * @author wassimnsiri
 */
public class ListCommandeForm extends Form{
    Form current;
  

    public ListCommandeForm(Form previous) {
    
       
    
        setTitle("Liste des Commande");
        setLayout(BoxLayout.y());
        setUIID("ListCommandetForm");

        ArrayList<Commande> Produits = ServicesCommande.getInstance().getAllLProduit();

        Container container = null;
        Button trie = new Button("Actualiser");
        trie.getStyle().setFgColor(0xff7fff);
        trie.setUIID("buttonWhiteCenter");
        Style modifierStyle = new Style(trie.getUnselectedStyle());
        modifierStyle.setFgColor(0xff7fff);

        trie.addActionListener(e -> new ListCommandeForm(previous).show());
        add(trie);
for (Commande l : Produits) {
    container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // define container here

    Label idLabel = new Label("ID : " + l.getId());
    idLabel.getStyle().setFgColor(0x555555);
    idLabel.getStyle().setPadding(0, 0, 5, 0);
    Label nomLabel = new Label("Prix : " + l.getNom());
    nomLabel.getStyle().setFgColor(0x555555);
    nomLabel.getStyle().setPadding(0, 0, 5, 0);
    Label quantiteLabel = new Label("Quantité : " + l.getQuantite());
    quantiteLabel.getStyle().setFgColor(0x555555);
    quantiteLabel.getStyle().setPadding(0, 0, 5, 0);
    Label marqueLabel = new Label("Prenom : " + l.getMarque());
    marqueLabel.getStyle().setFgColor(0x555555);
    marqueLabel.getStyle().setPadding(0, 0, 5, 0);
    Label prixLabel = new Label("Nom : " + l.getPrix());
    prixLabel.getStyle().setFgColor(0x555555);
    prixLabel.getStyle().setPadding(0, 0, 5, 0);
//    Label imageLabel = new Label("Image : " + l.getImage());
//    imageLabel.getStyle().setFgColor(0x555555);
//    imageLabel.getStyle().setPadding(0, 0, 5, 0);
    Label labelSupprimer = new Label(" ");
    labelSupprimer.setUIID("NewsTopLine");
    labelSupprimer.getStyle().setFgColor(0xf21f1f);

    FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, labelSupprimer.getStyle());
    labelSupprimer.setIcon(suprrimerImage);
    labelSupprimer.setTextPosition(Component.RIGHT);

    Label dateAchatLabel = new Label("Date d'achat : ");
    dateAchatLabel.getStyle().setFgColor(0x555555);
    dateAchatLabel.getStyle().setPadding(0, 0, 5, 0);
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateAchatLabel.setText("Date d'achat : " + dateFormat.format(l.getDaate_achat()));
    } catch (Exception e) {
        dateAchatLabel.setText("Date d'achat : 10-03-2023");
    }

    Button buttonSupprimer = new Button("Supprimer");

    //supprimer button
    buttonSupprimer.setUIID("NewsTopLine");
    buttonSupprimer.setIcon(suprrimerImage);
    buttonSupprimer.setTextPosition(Component.RIGHT);

    //click delete icon
    final Container finalContainer = container;
    buttonSupprimer.addPointerPressedListener(e -> {
        Dialog dig = new Dialog("Suppression");
        if (dig.show("Suppression", "Vous voulez supprimer ce Commande ?", "Annuler", "Oui")) {
            dig.dispose();
        } else {
            dig.dispose();
        }
        // supprimer le produit en utilisant le service Commande
        if (ServicesCommande.getInstance().deleteProduit(l.getId())) {
            // supprimer le conteneur parent du bouton supprimer
            e.getComponent().getParent().remove();
            refreshTheme();
        }
    });

            Commande r = l;
            Label rModifier = new Label(" ");
            rModifier.setUIID("NewsTopLine");
            FontImage modifierImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, rModifier.getStyle());
            rModifier.setIcon(modifierImage);
            rModifier.setTextPosition(LEFT);

            rModifier.addPointerPressedListener(m -> {
                // System.out.println("hello update");
                new ModiferCommandeForm(current,l).show();
            });
        
        

      //final Container  container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // initialize container here
        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
        container.add(idLabel);
        container.add(nomLabel);
        container.add(marqueLabel);
        container.add(prixLabel);
     //   container.add(imageLabel);
        container.add(dateAchatLabel);
        container.add(quantiteLabel);
        container.add(buttonSupprimer);
         container.add(rModifier);  

        add(container);
    }
          
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
         getToolbar().addCommandToSideMenu("Liste de Commande", null, ev-> new HomeForm().show());

        getToolbar().addCommandToSideMenu("Home", null, ev-> new AddCommandeForm(current).show());
        
}
    
}           

 