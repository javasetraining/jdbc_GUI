
package com.isoft.framecujdbc.domain.gui;

import java.awt.Component;
import javax.swing.JOptionPane;



/**
 *
 * @author iurasun
 */
public class ArataText {
    public static void  showMesaj(Component component, String text,String titlul,  boolean isInformation){
    
        if (isInformation) {
            JOptionPane.showMessageDialog(component, text, titlul, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(component, text, titlul, JOptionPane.ERROR_MESSAGE);
        }
         
    }
    
    public static void main(String[] args) {
        showMesaj(null,"Text de proba", "Titlul meu", true);  // cu import static sau dina clasa data
        ArataText.showMesaj(null,"Text de proba rau", "Titlul meu rau", false);
    }
}
