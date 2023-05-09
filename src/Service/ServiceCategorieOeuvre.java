/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Oeuvre;
import Utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;

/**
 *
 * @author octanet
 */
public class ServiceCategorieOeuvre{
    
    
      //singleton 
    public static ServiceCategorieOeuvre instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceCategorieOeuvre getInstance() {
        if(instance == null )
            instance = new ServiceCategorieOeuvre();
        return instance ;
    }
    
    
      
    public ServiceCategorieOeuvre() {
        req = new ConnectionRequest();
        
    }
    
     //ajout 
   /* public void ajoutOeuvre(Oeuvre oeuvre) {
        
        String url =Statics.BASE_URL+"/reclamation/addReclamationJSON?objet="+oeuvre.getObjet()+"&description="+oeuvre.getDescriptionR()+"&type="+oeuvre.getTypeR();//+"&user="+reclamation.getIduser(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }*/
    
}
