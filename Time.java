/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 *
 * @author Nikhil Gupta
 */
public class Time extends JComponent {

    public static int seconds = 30;
    DeadFrame D;
    int score;
    //JFrame W;
    
    void assignDF(DeadFrame D)
    {
        this.D = D;
    }
    
    void assignScore(int s)
    {
        score = s;
    }
    
    public Time()
    {
     //   this.setOpaque(false);
        Timer timer = new Timer(30000, (ActionEvent evt) -> {
            JOptionPane.showMessageDialog(null, "BooHoo. Time's up.");
            //seconds=100;
            D.screenF();
        });
        timer.start();
        Timer periodicTime = new Timer(1000, (ActionEvent evt) -> {
            seconds--;
        });
        periodicTime.start();
    }
}
