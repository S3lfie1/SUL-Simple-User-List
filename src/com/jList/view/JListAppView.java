/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jList.view;

import com.jList.controller.JListAppController;
import com.jList.model.Users;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * <h1>Application View section</h1>
 * The "JList" view.
 * This is Gui
 * 
 * @author Jakub Mazur
 * @version 1.0
 * @since 2017-12-04
 */
public class JListAppView extends JFrame{
    private JFrame frame;
    private JPanel panel;
    private JListAppController jController = new JListAppController();
    private JButton[] btn = new JButton[2];
    
    /**
     *  This method will draw JFrame.
     */
    public void drawFrame(){
        frame = new JFrame("SUL - Simple Users List");
        frame.setSize(jController.WIDTH, jController.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        drawPanel();
     
        frame.setVisible(true);
    }
    
    /**
     *  This method will draw JPanel in JFrame.
     */
    public void drawPanel() {
        panel = new JPanel();
        panel.setBackground(Color.ORANGE);
        panel.setLayout(null);
        panel.setSize(jController.WIDTH, jController.HEIGHT);
        
        JTextArea txtArea = new JTextArea();
        txtArea.setEditable(false);
        panel.add(txtArea);
        
        jController.printUsers(txtArea);
        
        JScrollPane scroll = new JScrollPane (txtArea, 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(10, 10, jController.WIDTH-25, jController.HEIGHT-90);
        panel.add(scroll);

        JLabel lbl1 = new JLabel();
        lbl1.setText("Name:");
        lbl1.setBounds(10, 330, 80, 30);
        panel.add(lbl1);
        
        JTextField txtField1 = new JTextField();
        txtField1.setBounds(50, 330, 80, 30);
        panel.add(txtField1);
        
        JLabel lbl2 = new JLabel();
        lbl2.setText("Lastname:");
        lbl2.setBounds(135, 330, 80, 30);
        panel.add(lbl2);
        
        JTextField txtField2 = new JTextField();
        txtField2.setBounds(195, 330, 80, 30);
        panel.add(txtField2);
        
        JLabel lbl3 = new JLabel();
        lbl3.setText("Dob:");
        lbl3.setBounds(280, 330, 50, 30);
        panel.add(lbl3);
        
        JTextField txtField3 = new JTextField();
        txtField3.setBounds(305, 330, 70, 30);
        panel.add(txtField3);
        
        btn[1] = new JButton("Add user");
        btn[1].setBounds(385, 330, 100, 30);
        btn[1].addActionListener((e)->{
            
            jController.addUsers(txtField1.getText(), txtField2.getText(), txtField3.getText());
        
        });
        panel.add(btn[1]);
        frame.add(panel);
    }

}
