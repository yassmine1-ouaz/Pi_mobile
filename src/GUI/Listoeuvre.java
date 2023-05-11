/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Oeuvre;
import Service.ServiceOeuvre;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author octanet
 */
    public class Listoeuvre extends Form {

    private ServiceOeuvre serviceOeuvre = new ServiceOeuvre();
    private ArrayList<Oeuvre> oeuvres;

    public Listoeuvre() {
        super("Liste des oeuvres");
        
        
        getToolbar().addMaterialCommandToRightBar("New oeuvre", FontImage.MATERIAL_ADD,
                e -> {
                    new AjouterOeuvre().show();
                });
    ///   oeuvres = serviceOeuvre.getAlloeuvres();
      /*  for (Oeuvre oeuvre : oeuvres) {
            add(createOeuvreContainer(oeuvre));
        }
        */
    
       
        oeuvres = ServiceOeuvre.getInstance().getAlloeuvres();
        System.out.println(oeuvres);
        Container oeuvreContainer = new Container();

        for (Oeuvre oeuvre : oeuvres) {
            Container oeuvreRow = new Container(new BorderLayout());
            oeuvreRow.setUIID("oeuvreBox");

            Label titreLabel = new Label("Titre : " + oeuvre.getTitreOeuvre());
            titreLabel.setUIID("titreLabel");

            Label artisteLabel = new Label("Artiste : " + oeuvre.getArtiste());
            artisteLabel.setUIID("artisteLabel");

            Label imageLabel = new Label("Image : " + oeuvre.getImage());
            imageLabel.setUIID("dateLabel");

            Label etatLabel = new Label("Etat : " + oeuvre.getEtat());
            etatLabel.setUIID("typeLabel");

            Label familleLabel = new Label("Famille : " + oeuvre.getFamille());
            familleLabel.setUIID("typeLabel");
            // Create buttons to edit and delete the oeuvre
            Button editBtn = new Button("Modifier");
            editBtn.addActionListener(e -> {
               // new Editoeuvre(oeuvre).show();
                oeuvreContainer.removeComponent(oeuvreRow);
            });

            Button deleteBtn = new Button("Supprimer");
            deleteBtn.addActionListener(a -> {
                ServiceOeuvre.getInstance().deleteOeuvre(oeuvre.getId());

                oeuvreContainer.removeComponent(oeuvreRow);
            });

            // Add the labels to the oeuvre row
            Container labelsContainer = new Container(new GridLayout(4, 1));
            labelsContainer.add(titreLabel);
            labelsContainer.add(artisteLabel);
            labelsContainer.add(imageLabel);
            labelsContainer.add(etatLabel);
             labelsContainer.add(familleLabel);

            labelsContainer.add(editBtn);
            labelsContainer.add(deleteBtn);

            oeuvreRow.add(BorderLayout.CENTER, labelsContainer);

            oeuvreContainer.add(oeuvreRow);
        }

        // Add the container to the form
        add(oeuvreContainer);
    }

   /* public void showOeuvreList() {
        OeuvreListForm form = new OeuvreListForm();
        form.show();
    }*/


    

   /* private Container createOeuvreContainer(Oeuvre oeuvre) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.add(new Label("Titre : " + oeuvre.getTitreOeuvre()));
        container.add(new Label("Artiste : " + oeuvre.getArtiste()));
        container.add(new Label("Prix : " + oeuvre.getPrixOeuvre()));
        container.add(new Label("Etat : " + oeuvre.getEtat()));
        container.add(new Label("Famille : " + oeuvre.getFamille()));
        return container;
    }*/
       public void showEvenementList() {
        Listoeuvre form = new Listoeuvre();
        form.show();
    }
}

    
