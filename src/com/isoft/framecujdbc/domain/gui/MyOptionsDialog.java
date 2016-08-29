package com.isoft.framecujdbc.domain.gui;

import com.isoft.framecujdbc.db.Constants;
import com.isoft.framecujdbc.db.DbProperties;
import com.isoft.framecujdbc.db.DbPropertiesUtil;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import se.datadosen.component.RiverLayout;

/**
 *
 * @author iurasun
 */
public class MyOptionsDialog extends JDialog{
   JFrame parent;
   
   JTextField jTextFieldUrl=new JTextField();
   JTextField jTextFieldUser=new JTextField();
   JPasswordField jTextFieldPassword=new JPasswordField();
   
   
   JButton jButton    = new JButton("Save and Close");
   DbProperties props=new DbProperties();
           
    public MyOptionsDialog(JFrame  parent) throws IOException {
      
        super(parent, true);
        
        
        DbPropertiesUtil propsService=DbPropertiesUtil.getInstance();
       
        props = propsService.citeste(Constants.PROPS_FILE_NAME);
        
        jTextFieldUrl.setText(props.getDburl());
        jTextFieldUser.setText(props.getDbusername());
        jTextFieldPassword.setText(props.getDbpassword());
        
        
        
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAndExit();
            }
        });
                
        
        this.setLayout(new RiverLayout());
        JLabel jLabelTitlu = new JLabel();
        
        
        jLabelTitlu.setForeground(Color.red);
        jLabelTitlu.setFont(new Font("Arial", Font.BOLD, 36));
        jLabelTitlu.setText("Panou de redactare a proprietatilor DB");
        
        
        add("center", jLabelTitlu);
        add("p left", new JLabel("UrlDB"));
        add("tab hfill", jTextFieldUrl);
//        
        add("p left", new JLabel("Username"));
        add("tab hfill", jTextFieldUser);
        
        add("p left", new JLabel("parola"));
        add("tab hfill", jTextFieldPassword);
//        
        add("p center", jButton);
        add("p center", new JLabel(""));
        add("p center", new JLabel(""));
        
        
        
        pack();       
        setLocationRelativeTo(parent);
      
        
    }
    
    private void saveAndExit() {
       
       try {
           props.setDburl(jTextFieldUrl.getText());
           props.setDbusername(jTextFieldUser.getText());
           props.setDbpassword(new String(jTextFieldPassword.getPassword()));
           
           DbPropertiesUtil.getInstance().scrie(props, Constants.PROPS_FILE_NAME);
           ArataText.showMesaj(this, "Salvat in fisier cu succes", "Succes", true);
       } catch (IOException ex) {
          ArataText.showMesaj(this, "Eroare la salvare", "Error", false);
           
       }
       
       this.dispose();
       
    }
    
}


