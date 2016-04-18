/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 *
 * @author Nikhil Gupta
 */
public class Time extends JComponent {

    public static int seconds = 100;
 
    public Time()
    {
     //   this.setOpaque(false);
        Timer timer = new Timer(100000, (ActionEvent evt) -> {
            JOptionPane.showMessageDialog(null, "BooHoo. tIME'S UP");
            seconds=100;
        });
        timer.start();
        Timer periodicTime = new Timer(1000, (ActionEvent evt) -> {
            seconds--;
        });
        periodicTime.start();
    }

}
