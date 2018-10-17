/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jList.controller;

/**
 * <h1>Application Main Class</h1>
 * The "JList" main method.
 * This method will check all parameters and starts an application controller.
 * 
 * @author Jakub Mazur
 * @version 1.0
 * @since 2017-12-04
 */
public class JListAppRunner {

    public static void main(String[] args) {
        JListAppController jController = new JListAppController();

        try {           
            if ((args[0].equals("-name")) && (args[2].equals("-lastname")) && (args[4].equals("-dob"))){
                if(!args[1].isEmpty() || !args[3].isEmpty() || !args[5].isEmpty()){
                jController.addUsers(args[1], args[3], args[5]);
                }
            } else {
                jController.printCorrectInstruction();
            }
        } catch (IndexOutOfBoundsException except) {
            jController.drawGui();
        }finally{
            jController.showWelcomMessage();
        }

    }

}
