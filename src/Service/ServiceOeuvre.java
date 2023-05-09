/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Oeuvre;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author octanet
 */
public class ServiceOeuvre {
      //singleton 
    public static ServiceOeuvre instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceOeuvre getInstance() {
        if(instance == null )
            instance = new ServiceOeuvre();
        return instance ;
    }
    
    
      
    public ServiceOeuvre() {
        req = new ConnectionRequest();
        
    }
    
     //ajout 
    public void ajoutOeuvre(Oeuvre oeuvre) {
        
        String url =Statics.BASE_URL+"/oeuvreJSON/newoJSON?image="+oeuvre.getImage()+"&titreOeuvre="+oeuvre.getTitreOeuvre()+"&artiste="+oeuvre.getArtiste()+"&prixOeuvre="+oeuvre.getPrixOeuvre()+"&famille="+oeuvre.getFamille()+"&etat="+oeuvre.getEtat();//+"&categorieOeuvre="+oeuvre.getId_cat_oeuv();//+"&user="+reclamation.getIduser(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    //affichage
    
    public ArrayList<Oeuvre>affichageOeuvre() {
        
        ArrayList<Oeuvre> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/oeuvreJSON/displayoeuvre";
        req.setUrl(url);
        
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
            try {
                Map<String,Object>mapOeuvres = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapOeuvres.get("root");
                
                for(Map<String, Object> obj : listOfMaps) {
                    
                    Oeuvre o = new Oeuvre();
                    
                    //dima id fi codename one float 5outhouha
                    float id = Float.parseFloat(obj.get("id").toString());
                    
                    String titreOeuvre = obj.get("titreOeuvre").toString();
                    
                    String artiste = obj.get("artiste").toString();
                    
                    String prixOeuvre = obj.get("prixOeuvre").toString();
                    
                    String image = obj.get("image").toString();
                    
                    String famille = obj.get("famille").toString();
                    
                    String etat = obj.get("etat").toString();
                    
                    
                      //  o.setDescriptionR(mt3 java(description hethy mt3symfony);
                    o.setId((int)id);
                    o.setTitreOeuvre(titreOeuvre);
                     o.setArtiste(artiste);
                    o.setPrixOeuvre(prixOeuvre);
                     o.setImage(image);
                      o.setFamille(famille);
                        o.setEtat(etat);
                    
                     
                    //insert data into ArrayList result
                    result.add(o);
                    
                    
                }
            
            }catch(Exception ex) {
                
                ex.printStackTrace();
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    


//Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Oeuvre DetailOeuvre( int id , Oeuvre oeuvre) {
        
        String url = Statics.BASE_URL+"/oeuvreJSON/detailoeuvrejson?id="+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                oeuvre.setTitreOeuvre(obj.get("titreOeuvre").toString());
                oeuvre.setArtiste(obj.get("artiste").toString());
                oeuvre.setPrixOeuvre(obj.get("prixOeuvre").toString());
                  oeuvre.setImage(obj.get("image").toString());
                    oeuvre.setFamille(obj.get("famille").toString());
                      oeuvre.setEtat(obj.get("etat").toString());
              
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return oeuvre;
        
        
    }
}