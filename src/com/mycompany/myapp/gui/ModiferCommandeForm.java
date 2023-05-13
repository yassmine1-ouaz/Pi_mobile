    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package com.mycompany.myapp.gui;

    
    import com.codename1.l10n.SimpleDateFormat;
    import com.codename1.ui.Button;
    import com.codename1.ui.ComboBox;

    import com.codename1.ui.Display;
    import com.codename1.ui.FontImage;
    import com.codename1.ui.Form;
    import com.codename1.ui.Label;
import com.codename1.ui.TextField;

    import com.codename1.ui.Toolbar;

    import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
    import com.codename1.ui.spinner.Picker;

    import com.mycompany.myapp.entites.Commande;
    import com.mycompany.myapp.services.ServicesCommande;
  
    /**
     *
     * @author wassimnsiri
     */


 public class ModiferCommandeForm extends Form {

    private Form previous;
    private Commande produit;


  public ModiferCommandeForm(Form previous, Commande produit) {
        super("Modifier Commande", BoxLayout.y());
        this.previous = previous;
        this.produit = produit;

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);

       TextField tfNom = new TextField(produit.getNom(), "Nom ");
        TextField tfMarque = new TextField(produit.getMarque(), "Prenom ");
        TextField tfQuantite = new TextField(String.valueOf(produit.getQuantite()), "Quantité du Commande");
        TextField tfPrix = new TextField(String.valueOf(produit.getPrix()), "Prix du Commande");
       // TextField tfImage = new TextField(produit.getImage(), "Image du produit");

        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));

        

        Button btnModifier = new Button("Modifier");
        btnModifier.getStyle().setFgColor(0xff7fff);
        btnModifier.setUIID("buttonWhiteCenter");
        
         Style modifierStyle = new Style(btnModifier.getUnselectedStyle());
         
         modifierStyle.setFgColor(0xff7fff); 
        btnModifier.addActionListener(l -> {
            produit.setNom(tfNom.getText());
            produit.setMarque(tfMarque.getText());
             // produit.setImage(tfImage.getText());
            produit.setQuantite(Integer.parseInt(tfQuantite.getText()));
            produit.setPrix(tfPrix.getText());
            //produit.setImage(tfImage.getText());
            // produit.setQuantite(quantiteCombo.getSelectedIndex());

            if (ServicesCommande.getInstance().modifierProduit(produit)) {
                new ListCommandeForm(previous).show();
            }
        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> new ListCommandeForm(previous).show());

        Label l2 = new Label("");
        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("");
        Label l1 = new Label("");

        addAll(tfNom, tfMarque, tfQuantite, tfPrix, btnModifier, btnAnnuler, l1, l2, l3, l4, l5);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }
}






/* public class ModiferCommandeForm extends Form {

    private Form previous;
    private Commande produit;

    public ModiferCommandeForm(Form previous , Commande produit) {
        setTitle("Modifier Categorie");
        setLayout(BoxLayout.y());
        
    TextField tfid = new TextField(String.valueOf(produit.getId()), " id ");
       TextField tfNom = new TextField(produit.getNom(), "Nom du produit");
        TextField tfMarque = new TextField(produit.getMarque(), "Marque du produit");
        TextField tfQuantite = new TextField(String.valueOf(produit.getQuantite()), "Quantité du produit");
        TextField tfPrix = new TextField(String.valueOf(produit.getPrix()), "Prix du produit");
        TextField tfImage = new TextField(produit.getImage(), "Image du produit");

        
        Button btnValider = new Button("Edit ");
          
         
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   try {
                       float id = Float.parseFloat(tfid.getText().toString());
                        float quantite = Float.parseFloat(tfQuantite.getText().toString());
                        Commande t;
                        t = new Commande((int) id,(int)quantite,tfMarque.getText().toString(),tfImage.getText().toString(),tfPrix.getText().toString(),tfNom.getText().toString());
                        if( ServicesCommande.getInstance().modifierProduit(t))
                        {
                           Dialog.show("Success","Congrats!!",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
           }
        });
        
        addAll(tfid ,tfNom,tfMarque,tfQuantite,tfPrix,tfImage,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
             
    }
    }*/


