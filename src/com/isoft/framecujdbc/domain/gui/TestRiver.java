/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isoft.framecujdbc.domain.gui;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import se.datadosen.component.RiverLayout;

/**
 *
 * @author iurasun
 */
public class TestRiver {

    public static void main(String[] args) {
        JFrame f = new JFrame("Our window");
        Container c = f.getContentPane();
        c.setLayout(new RiverLayout());

        c.add("center", new JLabel("Registration form"));
        c.add("p left", new JLabel("Name"));
        c.add("tab hfill", new JTextField());
        c.add("br", new JLabel("Age"));
        c.add("tab", new JTextField(3));
        c.add("br vtop", new JLabel("Comment"));
        c.add("tab hfill vfill", new JScrollPane(new JTextArea(20, 80)));
        c.add("p center", new JButton("Ok"));
        f.pack();
        
//        f.setSize(600, 400);
        
        f.setVisible(true);
    }
}
