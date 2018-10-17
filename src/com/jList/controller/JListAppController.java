/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jList.controller;

import com.jList.model.Users;
import com.jList.view.JListAppView;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JTextArea;

/**
 * <h1>Application Controller section</h1>
 * The "JList" controller.
 * This is an application controller.
 * 
 * @author Jakub Mazur
 * @version 1.0
 * @since 2017-12-04
 */
public class JListAppController {
    
    /**
     * This is JListAppController frame height.
     */
    public final int HEIGHT = 400;

    /**
     *This is JListAppController frame width.
     */
    public final int WIDTH = 500;

    private EntityManager em;
    private EntityManagerFactory emf;

    void printCorrectInstruction() {
        System.out.println("\n To create new user, please use the instructions below");
        System.err.println("the correct syntax is: -name 'name_here' -lastname 'lastname_here' -dob 'yyyy-mm-dd' (without quotes)");
        System.out.println("You can also open gui version (ignore parameters)");
    }
    
    void showWelcomMessage() {
        System.out.println("\n SUL - Simple users list \n You can add new users from Gui or by using command line! \n");
    }
    /**
     *  This method will draw GUI on screen
     */
    void drawGui() {
        System.out.println("Opening GUI...");
        JListAppView jView = new JListAppView();
        jView.drawFrame();
        jView.drawPanel();
    }
    
    
    /**
     * <h1>This is description of the method addUsers.</h1>
     *     Method will insert a new user into database.
     * @param userName          Value written in txtField1 from drawPanel() method (user name)
     * @param userLastname      Value written in txtField2 from drawPanel() method (user lastname)
     * @param userDate          Value written in txtField3 from drawPanel() method (user date of birth)
     */
    public void addUsers(String userName, String userLastname, String userDate) {
        emf = Persistence.createEntityManagerFactory("JChatPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();

        Users user = new Users();
        user.setFirstname(userName);
        user.setLastname(userLastname);
        user.setDob(userDate);
        em.persist(user);
        em.getTransaction().commit();
        System.out.println("User added successfully!");
    }
    
    /**
     *  <h1>This is description of the method printUsers.</h1>
     *      Method will get all records from users table and display it into txtArea from JListAppView
     *@param txtArea            Object from JListAppView. This object contains all rows form db table.
     */
    public void printUsers(JTextArea txtArea) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    txtArea.setText("");
                    emf = Persistence.createEntityManagerFactory("JChatPU");
                    em = emf.createEntityManager();
                    em.getTransaction().begin();

                    List<Users> list = em.createNamedQuery("Users.findAll", Users.class).getResultList();

                    list.forEach((getUser) -> {
                        txtArea.append(getUser.getId() + ". " + getUser.getFirstname() + " " + getUser.getLastname() + ", " + getUser.getDob() + "\n");
                    });
                } catch (PersistenceException except) {
                    timer.cancel();
                    txtArea.setText("Could not connect to mysql!");
                    System.err.println("Could not connect to mysql. Please check db connection settings!");
                    System.err.println("Default connection: ['localhost', 'root', ''] [you can edit in persistence.xml file] ENJOY");
                }
            }
        }, 0, 4000);

    }

}
