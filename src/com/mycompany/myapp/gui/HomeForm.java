        /*
         * To change this license header, choose License Headers in Project Properties.
         * To change this template file, choose Tools | Templates
         * and open the template in the editor.
         */
        package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
        import com.codename1.ui.Button;
        import com.codename1.ui.Component;
import com.codename1.ui.Display;
        import com.codename1.ui.Font;
        import com.codename1.ui.Form;
        import com.codename1.ui.Label;
        import com.codename1.ui.TextField;
        import com.codename1.ui.layouts.BoxLayout;
        import com.codename1.ui.plaf.Border;
        import com.codename1.ui.plaf.Style;
        import com.codename1.ui.util.Resources;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
        import com.mycompany.myapp.MyApplication;
        import com.mycompany.myapp.entites.Commande;
import static com.mycompany.myapp.services.ServicesCommande.generatePdfFromItems;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


import java.util.ArrayList;


        public class HomeForm extends Form {
            Resources res;
            Form current;
            
     private static void generatePdfFromItems(ArrayList<Commande> commandes) {
        Document document = new Document();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();
            PdfPTable table = new PdfPTable(2); // 2 colonnes: nom et marque
            table.addCell("Nom");
            table.addCell("Marque");
            for (Commande commande : commandes) {
                table.addCell(commande.getNom());
                table.addCell(String.valueOf(commande.getMarque()));
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

           // TextField email;

            public HomeForm(){
               current = this;

             // Définir l'arrière-plan du formulaire avec une couleur de fond rouge
          getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_LINEAR_VERTICAL);
        getUnselectedStyle().setBackgroundGradientStartColor(0x555555);
        getUnselectedStyle().setBackgroundGradientEndColor(0xB22222);

            setTitle("Gestion Commande");
            setLayout(new BoxLayout(BoxLayout.Y_AXIS));

            // Ajouter une étiquette pour indiquer à l'utilisateur de choisir une option
            Label label = new Label("Choisir une option");
            label.setAlignment(Component.CENTER);
            label.getAllStyles().setPadding(Component.TOP, 10);
            label.getAllStyles().setPadding(Component.BOTTOM, 20);
            add(label);

            // Créer les boutons avec des styles CSS personnalisés
            Button btnAddProduit = new Button("Ajouter un Commande");
            btnAddProduit.getAllStyles().setBgColor(0xFFA500); // Rose clair
            btnAddProduit.getAllStyles().setFgColor(0xFFFFFF); // Blanc
            btnAddProduit.getAllStyles().setBackgroundType(Style.BACKGROUND_GRADIENT_LINEAR_VERTICAL);
            btnAddProduit.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));

            btnAddProduit.getAllStyles().setBorder(Border.createEmpty());
            btnAddProduit.getAllStyles().setBackgroundGradientEndColor(0xFFA500);
            btnAddProduit.getAllStyles().setBackgroundGradientStartColor(0xFF6347);


            Button btnListProduit = new Button("Liste des Commande");
              btnListProduit.getAllStyles().setBgColor(0xFFA500); // Vert-bleu
    btnListProduit.getAllStyles().setFgColor(0xFFFFFF); // Blanc
    btnListProduit.getAllStyles().setBackgroundType(Style.BACKGROUND_GRADIENT_LINEAR_VERTICAL);
    btnListProduit.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));

    btnListProduit.getAllStyles().setBorder(Border.createEmpty());
    btnListProduit.getAllStyles().setBackgroundGradientEndColor(0xFFA500);
    btnListProduit.getAllStyles().setBackgroundGradientStartColor(0xFF6347);


        Button btnModifierProduit = new Button("PDF");

        btnModifierProduit.getAllStyles().setBgColor(0xFFA500); // Orange
        btnModifierProduit.getAllStyles().setFgColor(0xFFFFFF); // Blanc
        btnModifierProduit.getAllStyles().setBackgroundType(Style.BACKGROUND_GRADIENT_LINEAR_VERTICAL); // Arrière-plan gradient linéaire vertical
        btnModifierProduit.getAllStyles().setBackgroundGradientStartColor(0xFFA500); // Orange
        btnModifierProduit.getAllStyles().setBackgroundGradientEndColor(0xFF6347); // Saumon foncé

        // Ajouter les actions aux boutons
        btnAddProduit.addActionListener(e -> new AddCommandeForm(current).show());
        btnListProduit.addActionListener(e -> new ListCommandeForm(current).show());
btnModifierProduit.addActionListener(e -> {
    //ArrayList<Commande> commandes = // récupérer la liste des commandes à afficher dans le PDF
    //generatePdfFromItems(commandes);
});



        // Ajouter les boutons au formulaire
        addAll(btnAddProduit, btnListProduit, btnModifierProduit);
        Component.setSameSize(btnAddProduit, btnListProduit, btnModifierProduit);
        for (Component cmp : getContentPane()) {
            cmp.getAllStyles().setAlignment(Component.CENTER);
            }
            }}

























            // Ajouter cette ligne pour définir un arrière-plan rouge
        //    getUnselectedStyle().setBgColor(0xCFE2F3);
        //
        //    if (MyApplication.theme == null) {
        //        System.out.println("MyApplication.theme est null");
        //    } else if (MyApplication.theme.getImage("1.jpg") == null) {
        //        System.out.println("MyApplication.theme.getImage(\"1.jpg\") est null");
        //    } else {
        //        getUnselectedStyle().setBgImage(MyApplication.theme.getImage("1.jpg"));
        //    }
        //        
        //      
        //           if (MyApplication.theme == null) {
        //    System.out.println("MyApplication.theme est null");
        //} else if (MyApplication.theme.getImage("1.jpg") == null) {
        //    System.out.println("MyApplication.theme.getImage(\"1.jpg\") est null");
        //} else {
        //    getUnselectedStyle().setBgImage(MyApplication.theme.getImage("1.jpg"));
        //}
        //          
        //        setTitle("Mon application");
        //        setLayout(BoxLayout.y());
        //       
        //       
        //       setTitle("Gestion Produit");
        //       setLayout(BoxLayout.y());
        //       
        //       add(new Label("choisir une option"));
        //      Button btnAddProduit = new Button("Ajouter un produit");
        //    Button btnListProduit = new Button("List Produit") ;
        //       Button btnModifierProduit = new Button ("Modifier");
        //     
        //       btnAddProduit.addActionListener(e-> new AddCommandeForm(current).show());
        //       btnListProduit.addActionListener(e->new ListCommandeForm(current).show());
        //     
        //   
        //       addAll(btnAddProduit,btnListProduit);
        //       
        //       
        //      
        //
        //    }
        //      
        //   

