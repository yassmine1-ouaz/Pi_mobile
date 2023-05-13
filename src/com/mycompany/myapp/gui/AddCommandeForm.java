    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package com.mycompany.myapp.gui;
    import com.codename1.l10n.SimpleDateFormat;
    import com.codename1.ui.Button;
    import com.codename1.ui.Command;
    import com.codename1.ui.Container;
    import com.codename1.ui.Dialog;
    import com.codename1.ui.Display;
    import com.codename1.ui.FontImage;
    import com.codename1.ui.Form;
    import com.codename1.ui.Image;
    import com.codename1.ui.TextField;
    import com.codename1.ui.events.ActionEvent;
    import com.codename1.ui.events.ActionListener;
    import com.codename1.ui.layouts.BoxLayout;
    import com.codename1.ui.plaf.Style;
    import com.codename1.ui.spinner.Picker;
    import com.mycompany.myapp.entites.Commande;
    import com.mycompany.myapp.services.ServicesCommande;
    import java.io.IOException;
    import java.util.Date;


    /**
     *
     */
    public class AddCommandeForm extends Form{
        Form current;
        public AddCommandeForm(Form previous) {

                  super("Add new Commande");
          //  this.previousForm = previousForm;

            // Create the input fields and buttons
            
            TextField tfPrenom = new TextField("", "Prenom");
            TextField tfNom = new TextField("", "Nom");
            Picker datePicker = new Picker();
            datePicker.setType(Display.PICKER_TYPE_DATE);
            datePicker.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
            TextField tfQuantite = new TextField("", "Quantity");
            TextField tfPrix = new TextField("", "Price");
            Button btnValider = new Button("Add Commande");
            btnValider.setUIID("buttonWhiteCenter");
            
            

            // Set the input fields and buttons styles
            Style inputStyle = new Style(tfNom.getUnselectedStyle());
            inputStyle.setMarginTop(10);
            inputStyle.setMarginBottom(10);
            tfNom.setUnselectedStyle(inputStyle);
            tfPrenom.setUnselectedStyle(inputStyle);
            tfQuantite.setUnselectedStyle(inputStyle);
            tfPrix.setUnselectedStyle(inputStyle);

            // Set the Add Product button style
            Style btnStyle = new Style(btnValider.getUnselectedStyle());
            btnStyle.setFgColor(0xff7fff);
            btnValider.setUnselectedStyle(btnStyle);



             btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    //if (tfNom.getText().length() == 0 || tfdescriptionCat.getText().length() == 0) {
                       // Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                   // } else {
                      try {

                            int quantite = Integer.parseInt(tfQuantite.getText());
                            Date date = datePicker.getDate();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            String dateString = formatter.format(date);



                            Commande c = new Commande(quantite, tfNom.getText(), tfPrenom.getText(), date, tfPrix.getText());
                        if (ServicesCommande.getInstance().AddProduit(c)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("ERROR", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }

            });


            addAll(tfNom,tfPrenom,datePicker,tfPrix,tfQuantite,btnValider);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
            getToolbar().addCommandToSideMenu("Home", null, ev-> new HomeForm().show());
           //  getToolbar().addCommandToSideMenu("Liste de livraison", null, ev-> new ListLivraisonForm(current).show());

        }
    }


