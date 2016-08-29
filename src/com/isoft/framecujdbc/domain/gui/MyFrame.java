package com.isoft.framecujdbc.domain.gui;

import com.isoft.framecujdbc.dao.AngajatDaoIntf;
import com.isoft.framecujdbc.db.MyDataSource;
import com.isoft.framecujdbc.domain.gui.models.AngajatiTableModel;
import com.isoft.framecujdbc.domain.gui.controlers.ApasaListenerExtern;
import com.isoft.framecujdbc.dao.impl.AngajatDaoImpl;
import com.isoft.framecujdbc.domain.Angajat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author iurasun
 */
class MyFrame extends JFrame {

    //A a = new A();
    
    JButton jButtonApasa = new JButton(" Apasa aici! ");
    JButton jButtonExit = new JButton(" Exit ");
    
    
    JButton jButtonConectare   = new JButton(" Conectare! ");
    JButton jButtonDeconectare = new JButton(" Deconectare ");
    JButton jButtonArataLista  = new JButton(" Arata Lista Angajati! ");
    
    
    
    JMenuItem jMenuItemApasa = new JMenuItem(" Apasa! ");
    JMenuItem jMenuItemExit = new JMenuItem(" Exit ");
    
    JMenuItem jMenuItemOptions = new JMenuItem(" Options ");
    JMenuItem jMenuItemAbout = new JMenuItem(" About ");
         
    
    JTextField jTextFieldNume = new JTextField(20);
    JTextField jTextFieldPrenume = new JTextField(20);
    JTextArea jTextArea=new JTextArea(10, 20   );
    
    
    JTabbedPane jTabbedPane = new JTabbedPane();
    JPanel jPanelArea =new JPanel();
    JPanel jPanel =new JPanel();
    
    JMenu jMenuFile =new JMenu("File");
    JMenu jMenuEdit =new JMenu("Edit");
    JMenu jMenuHelp =new JMenu("Help");
    
    
    JMenuBar jMenuBar =new JMenuBar();
    
    JToolBar jToolBar=new JToolBar();
    
    JLabel jLabelRezultat =new JLabel("Aici va fi mesajiul final!");
    
    
    JTable jTableAngajati;
    
    //panels
    AngajatiPanel angajatiPanel;
    DepartamentePanel  departamentePanel ;
    
    //date
    AngajatDaoIntf angajatiDao;
    List<Angajat> listaAngajati;
    
    //modele
    AngajatiTableModel angajatiTableModel;

    public MyFrame() {
        
        initCompnents();
        initModels();
        addListeners();
        
        
        
    }

    private void initModels(){
         angajatiTableModel = new AngajatiTableModel();
         jTableAngajati.setModel(angajatiTableModel);
         
         
    }
    
    private void initCompnents(){
        /////// menu ////////////////
        jMenuFile.add(jMenuItemApasa);
        jMenuFile.add(jMenuItemExit);
        
        jMenuEdit.add(jMenuItemOptions);
        
        jMenuHelp.add(jMenuItemAbout);
           
        
        jMenuBar.add(jMenuFile);
        jMenuBar.add(jMenuEdit);
        jMenuBar.add(jMenuHelp);
        
        
        setJMenuBar(jMenuBar);
        

        /////////tool bar////////////
        jToolBar.add(jButtonApasa);
        jToolBar.add(jButtonExit);
        
        jToolBar.addSeparator(new Dimension(80, 0)); // x, y
        
        jToolBar.add(jButtonConectare);
        jToolBar.add(jButtonDeconectare);
        
        jToolBar.add(jButtonArataLista);
        
        jToolBar.setFloatable(false);
        this.add(jToolBar, BorderLayout.NORTH);
        
        
        /////// panou central ///////
        jPanel.add(new JLabel(" Numele "));
        jPanel.add(jTextFieldNume);
        
        jPanel.add(new JLabel("    "));
        
        jPanel.add(new JLabel(" Preumele "));
        jPanel.add(jTextFieldPrenume);
        
        jPanel.add(jLabelRezultat);
        
        //this.add(jPanel, BorderLayout.CENTER);// implicit cand e pus pe frame
        
        
        angajatiPanel = new AngajatiPanel();
        
        
        jTableAngajati=new JTable();
        
        jPanelArea.setLayout(new BorderLayout());
               
        
        jPanelArea.add(new JScrollPane(jTextArea), BorderLayout.SOUTH);
        jPanelArea.add(angajatiPanel, BorderLayout.WEST);
        jPanelArea.add(new JScrollPane(jTableAngajati), BorderLayout.CENTER);
        
        
        departamentePanel  =new DepartamentePanel();
        jTabbedPane.add("Departamente", departamentePanel);
        jTabbedPane.add("Angajati", jPanelArea);
        
        
        
        this.add(jTabbedPane, BorderLayout.CENTER);// implicit cand e pus pe frame
        
        //////// farme  ///
        
        setTitle("Demo clase interne de tip eveniment");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
    }
    private void addListeners() {

       //ApasaListenerExtern aL  =new ApasaListenerExtern();
       ApasaListenerExtern aL  =new ApasaListenerExtern(jTextFieldNume, jTextFieldPrenume, jLabelRezultat);
       
       jButtonApasa.addActionListener(aL);
       jMenuItemApasa.addActionListener(aL);
       
       
       jButtonExit.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
               
               System.exit(0);
           }
       });
       
    
        jMenuItemExit.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
               
               JOptionPane.showMessageDialog(rootPane, "Salut! ne pare rau ca iesi asa repede... ");
               System.exit(0);
           }
       });
        
        
         jMenuItemAbout.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
               
               JOptionPane.showMessageDialog(rootPane, "IUCOSOFT 2015 Frame cu Singlton ");
              
           }
       });
         
       
       jButtonConectare.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   MyDataSource dataSource =  MyDataSource.getInstance();
                   conn=dataSource.getConnection();
                   if(conn !=null){
                       ArataText.showMesaj(rootPane, "Conectarea obtinuta cu success", "Success", true);
                       
                       angajatiPanel.setButoaneAcesibile(true);
                       departamentePanel.setButoaneAcesibile(true);
                       
                       
                       if(angajatiDao ==null){
                          angajatiDao=new AngajatDaoImpl(conn);
                       }
                       angajatiPanel.setAngajatiDao(angajatiDao);
                       
                       if(angajatiTableModel==null){
                        angajatiTableModel = new AngajatiTableModel();
                       }
                       angajatiPanel.setAngajatiTableModel(angajatiTableModel);
                   } 
                  } catch (Exception ex) {
                   Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                   ArataText.showMesaj(rootPane, ex.getMessage(),  "Error", false);
               }
           }
       });
       
        jButtonDeconectare.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   MyDataSource dataSource =  MyDataSource.getInstance();
                   Connection conn=dataSource.getConnection();
                   if(conn !=null && !conn.isClosed()){
                       conn.close();
                       ArataText.showMesaj(rootPane, "DEConectare success", "Success", true);
                   } 
                  } catch (Exception ex) {
                   Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                   ArataText.showMesaj(rootPane, ex.getMessage(),  "Error", false);
               }
           }
       });
        
        
        jButtonArataLista.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   angajatiDao=new AngajatDaoImpl(conn);
                   listaAngajati =  angajatiDao.findAll();
                   arataLista(listaAngajati);
               } catch (Exception ex) {
                   Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                   ArataText.showMesaj(rootPane, ex.getMessage(), "Error", false);
               }
           }
       });
        
        
        jMenuItemOptions.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               
               try {  
                   new MyOptionsDialog(MyFrame.this).setVisible(true);
               } catch (IOException ex) {
                   Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
               }
               
           }
       }); 
        
        jTabbedPane.addChangeListener(new ChangeListener() {

           @Override
           public void stateChanged(ChangeEvent e) {
              if(jTabbedPane.getSelectedIndex()==1){
                 angajatiPanel.clearForm();
              }        
           }
       });
        
    }//add listeners
    
    private void arataLista(List<Angajat> listaAngajati) {
        jTextArea.setText("");
//        for (Angajat angajat : listaAngajati) {
//            jTextArea.append(angajat.toString());
//            jTextArea.append("\n");
//        }
        
        for (int i = 0; i <  listaAngajati.size(); i++) {
            Angajat angajat = listaAngajati.get(i);
            jTextArea.append(angajat.toString());
            jTextArea.append("\n");
            
        }
        
        jTabbedPane.setSelectedIndex(1);
        ////
        
        
        //aratam in tabela
        angajatiTableModel.refreshData(listaAngajati);
        
        
        
    }
    //clasa interna   - inner
    class ApasaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

           String nume=jTextFieldNume.getText();
           String prenume=jTextFieldPrenume.getText();
           
           String mesaj="Salut "+nume+" "+prenume+"!";
           jLabelRezultat.setText(mesaj);
            
        }
    
    }//end class
    
     Connection conn;
}


