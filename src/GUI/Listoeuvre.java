/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Oeuvre;
import Service.ServiceOeuvre;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author octanet
 */
    public class Listoeuvre extends Form {

    private ServiceOeuvre serviceOeuvre = new ServiceOeuvre();
    private ArrayList<Oeuvre> oeuvres;

    public Listoeuvre(Resources res) {
        super("Liste des oeuvres");
        
        
        getToolbar().addMaterialCommandToRightBar("New oeuvre", FontImage.MATERIAL_ADD,
                e -> {
                    new AjouterOeuvre().show();
                });
     //   oeuvres = serviceOeuvre.getAlloeuvres();
        for (Oeuvre oeuvre : oeuvres) {
            add(createOeuvreContainer(oeuvre));
        }
    }

    private Container createOeuvreContainer(Oeuvre oeuvre) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.add(new Label("Titre : " + oeuvre.getTitreOeuvre()));
        container.add(new Label("Artiste : " + oeuvre.getArtiste()));
        container.add(new Label("Prix : " + oeuvre.getPrixOeuvre()));
        container.add(new Label("Etat : " + oeuvre.getEtat()));
        container.add(new Label("Famille : " + oeuvre.getFamille()));
        return container;
    }
}

    
