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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
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
    private boolean resultOK;
    private ArrayList<Oeuvre> oeuvres;
     public ArrayList<Oeuvre> oeuvre;
    
    
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
        
        req.setUrl(url); //// Insertion de l'URL de notre demande de connexion
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    //affichage
    
    /*public ArrayList<Oeuvre>affichageOeuvre() {
        
        ArrayList<Oeuvre> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/oeuvreJSON/displayoeuvre";
        req.setUrl(url);
        
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser(); //// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
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
  */  
    
    public ArrayList<Oeuvre> parseoeuvre(String jsonText) {
        
    ArrayList<Oeuvre> oeuvres = new ArrayList<>();
    
    try {
        JSONParser parser = new JSONParser(); // Instanciation d'un objet JSONParser permettant le parsing du résultat json
        Map<String, Object> jsonMap = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> oeuvreList = (List<Map<String, Object>>) jsonMap.get("root");

        for (Map<String, Object> oeuvreData : oeuvreList) {
            //Création d'une nouvelle instance de Oeuvre et récupération de ses données depuis le JSON
            Oeuvre oeuvre = new Oeuvre();

            Double idOeuvre = Double.parseDouble(oeuvreData.get("id").toString());
            oeuvre.setId(idOeuvre.intValue());

            String titre = oeuvreData.get("titreOeuvre").toString();
            oeuvre.setTitreOeuvre(titre);

            String artiste = oeuvreData.get("artiste").toString();
            oeuvre.setArtiste(artiste);

            String prix = oeuvreData.get("prixOeuvre").toString();
            oeuvre.setPrixOeuvre(prix);

            String image = oeuvreData.get("image").toString();
            oeuvre.setImage(image);

            String etat = oeuvreData.get("etat").toString();
            oeuvre.setEtat(etat);

            String famille = oeuvreData.get("famille").toString();
            oeuvre.setFamille(famille);

            //Ajouter l'oeuvre extraite de la réponse JSON à la liste
            oeuvres.add(oeuvre);
        }

    } catch (IOException ex) {
        ex.printStackTrace();
    }
    return oeuvres;
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
    
    
    ////// all oeuvre
    
    public ArrayList<Oeuvre> getAlloeuvres() {

        String url = Statics.BASE_URL + "/oeuvreJSON/displayoeuvre";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                oeuvres = parseoeuvre(new String(req.getResponseData()));
                System.out.println(oeuvres);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return oeuvres;
    }
    
    
    
    public void deleteOeuvre(int idOeuvre) {

        Dialog d = new Dialog();
        if (d.show("Delete oeuvre"
                + "..", "Do you really want to remove this oeuvre", "Yes", "No")) {

            req.setUrl(Statics.BASE_URL + "/oeuvreJSON/supprimeroeuvreJSON" + idOeuvre);

            NetworkManager.getInstance().addToQueueAndWait(req);
            d.dispose();
        }
    }
    
    public boolean editOeuvre(Oeuvre o) {
        String url = Statics.BASE_URL + "oeuvreJSON/modifieroeuvreJSON/" + o.getId() + "?nom=" + o.getTitreOeuvre() + "&prenom=" + o.getArtiste() + "&sexe=" + o.getPrixOeuvre() + "&dateNaissance=" + o.getImage() + "&cin=" + o.getEtat() +"&sexe=" + o.getFamille();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
}