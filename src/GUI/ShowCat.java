/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author octanet
 */
public class ShowCat extends Form{

    public ShowCat(Form previous ) {
        
        setTitle("List");
        setLayout(BoxLayout.y());
        
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> { previous.showBack();
         });
    }
    
}
