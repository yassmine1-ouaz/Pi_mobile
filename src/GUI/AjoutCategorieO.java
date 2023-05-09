/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author octanet
 */
public class AjoutCategorieO extends Form {

    public AjoutCategorieO(Form previous) {
        
        setTitle("AjoutCategorie");
        setLayout(BoxLayout.y());
   
         TextField nom= new TextField("Nom_cat_oeuv");
        TextField description= new TextField("description_cat_oeuv");
        
        Button btnAjouter= new Button("Ajouter");
         btnAjouter.addActionListener((evt) -> {});
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> { previous.showBack();
         });
    addAll( nom,description,btnAjouter);
}
    

}
