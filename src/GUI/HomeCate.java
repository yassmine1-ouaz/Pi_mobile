/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author octanet
 */
public class HomeCate extends Form{

    public HomeCate() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choisir"));
        
        Button btnAjouter = new Button("Ajouter oeuvre");
    
        Button btnList = new Button("List des oeuvres");
        
        btnAjouter.addActionListener((evt) -> {});
         btnList.addActionListener((evt) -> {});

       // add(btnAjouter);
        // add(btnShow);
        addAll(btnAjouter,btnList);
    }
    
}
