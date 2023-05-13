/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.myapp.entites.Commande;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author wassimnsiri
 */
public class ServicesCommande {
        public ArrayList<Commande> Produits;
    public boolean resultOk;
    public static  ServicesCommande instance=null;
    private ConnectionRequest req;
    private boolean resultOK;
    
  public ServicesCommande(){
    req= new ConnectionRequest();
    }
    public static ServicesCommande getInstance(){
    if(instance == null){
       instance = new ServicesCommande();
    
    }
    
    return instance;
    }
        
   public boolean AddProduit(Commande l){
        //int id= l.getId();
        String nom=l.getNom();
        String marque=l.getMarque();
        String prix=l.getPrix();
        String image=l.getImage();
        Date daate_achat=l.getDaate_achat();
        int quantite=l.getQuantite();
            
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = formatter.format(daate_achat);
         String url =Statics.BASE_URL+"/addcommande?marque="+l.getMarque()+"&nom="+l.getNom()+"&quantite="+l.getQuantite()+"&prix="+l.getPrix()+"&image="+l.getImage()+"&daate_achat="+formattedDate;
       // req.setUrl(url);
     /*  String url=Statics.BASE_URL+"/addProduit/"+l.getId()+'/'+l.getNom()+'/'+l.getMarque()+'/'+l.getPrix()+'/'+l.getImage()+'/'+l.getDaate_achat()+'/'+l.getQuantite();*/
       req.setUrl(url);
        req.setPost(false);
       ConnectionRequest req= new ConnectionRequest(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){

           @Override
           public void actionPerformed(NetworkEvent evt) {
             
           resultOk=req.getResponseCode()==200;
           req.removeResponseListener(this);
           }
       
       
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
     return resultOk;
    }
    
     public ArrayList<Commande> parseTasks(String jsonText) throws ParseException {
        
         System.out.println("debut parsing");
         
         try {
            Produits = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                  Commande t = new Commande();
                float id = Float.parseFloat((obj.get("id")).toString());
                t.setId(((int) id));
                 float quantite = Float.parseFloat((obj.get("quantite")).toString());
                t.setQuantite(((int) quantite));
                if (obj.get("nom") == null) {
                    t.setNom("null");
                } else {
                    t.setNom(obj.get("nom").toString());
                }
                if (obj.get("marque") == null) {
                    t.setMarque("null");
                } else {
                    t.setMarque(obj.get("marque").toString());
                }
                if (obj.get("prix") == null) {
                    t.setPrix("null");
                } else {
                    t.setPrix(obj.get("prix").toString());
                }
                if (obj.get("daate_achat") == null) {
            t.setDaate_achat(null);
        } else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(obj.get("daate_achat").toString());
                t.setDaate_achat(date);
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
                if (obj.get("image") == null) {
                    t.setImage("null");
                } else {
                    t.setImage(obj.get("image").toString());
                }
                
                Produits.add(t);
            }
            
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());
        }
          System.out.println(Produits);
        return Produits;
    }
         public ArrayList<Commande> getAllLProduit() {
       String url = Statics.BASE_URL +"/commande/Affichercommande";
      System.out.println(url);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
 
            @Override
            
            public void actionPerformed ( NetworkEvent evt) {
                System.out.println("connexuion");
                try {
                    Produits = parseTasks(new String(req.getResponseData()));
                } catch (ParseException ex) {
                  //  Logger.getLogger(ServicesCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produits;
    }

 public boolean deleteProduit(int id) {
    String url = Statics.BASE_URL + "/Supprimercommande/" + id;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    request.addResponseListener((e) -> {
        if (request.getResponseCode() == 200) {
            // la suppression a été effectuée avec succès
            resultOk = true;
        } else {
            // la suppression a échoué
            resultOk = false;
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(request);
    return resultOk;
}
  public boolean modifierProduit(Commande l){
     
        String url =Statics.BASE_URL+"/update/"+l.getId()+ "?marque=" + l.getMarque() +"&nom="+l.getNom()+"&quantite="+l.getQuantite()+"&prix="+l.getPrix()+"&image="+l.getImage()+"&daate_achat="+l.getDaate_achat();
    req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            private boolean resultOK;

            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
/*public boolean modifierProduit(Commande l) {
  
    String url = Statics.BASE_URL +"/updateProduit/?id="+l.getId()+ "&marque=" + l.getMarque() +"&nom="+l.getNom()+"&quantite="+l.getQuantite()+"&prix="+l.getPrix()+"&image="+l.getImage()+"&daate_achat="+l.getDaate_achat();
    req.setUrl(url);
       req.setPost(false);
      // ConnectionRequest req= new ConnectionRequest(url);
    req.setHttpMethod("PUT");
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
}*/
/*public boolean modifierProduit(Commande l) {
    final boolean[] resultOK = {false};
    String url = Statics.BASE_URL +"/updateProduit/"+l.getId()+ "?marque=" + l.getMarque() +"&nom="+l.getNom()+"&quantite="+l.getQuantite()+"&prix="+l.getPrix()+"&image="+l.getImage()+"&daate_achat="+l.getDaate_achat();
    
    ConnectionRequest req= new ConnectionRequest(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK[0] = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueue(req);
    return resultOK[0];
}*/

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public static void generatePdfFromItems(ArrayList<Commande> Produit) {
        Document document = new Document();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();
            PdfPTable table = new PdfPTable(2); // 2 columns: item name and price
            table.addCell("Item Name");
            table.addCell("Price");
            for (Commande item : Produit) {
                table.addCell(item.getNom());
                table.addCell(String.valueOf(item.getMarque()));
            }
            document.add(table);
            document.close();
            byte[] pdfBytes = baos.toByteArray();
            String fileName = "myPdfFile.pdf";
            FileSystemStorage fs = FileSystemStorage.getInstance();
            String filePath = fs.getAppHomePath() + fileName;
            fs.openOutputStream(filePath).write(pdfBytes);
            Display.getInstance().execute(filePath);

        } catch (DocumentException | IOException e) {
            Log.e(e);
        }
    }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}

