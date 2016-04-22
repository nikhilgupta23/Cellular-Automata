/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author acer
 */
public class DeadFrame {
    JFrame f, W;
    String U;
    int score;
    //JButton JB = new JButton("View Flood Fill");
    DB db;
    DeadFrame(String user)    
    {
        f = new JFrame();
        JPanel jPanel=new JPanel();
        jPanel.add(new JLabel(new ImageIcon("2.jpg")));
        f.add(jPanel);
        f.setSize(1366,768);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        U = user;
    }
    
    void Score(int s, JFrame W)
    {
        this.score = s;
        this.W = W;
    }
    
    void screenF()
    {
        W.setVisible(false);
        Disp D = new Disp();
        f.add(D);
        db = new DB();
        db.connDB();
        f.setVisible(true);
        if (db.viewHS(U) < score)
        {   db.updateHS(U, score);
            JOptionPane.showMessageDialog(null, "Congratulations! New High Score :DD");
            db.closeDB();
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Move on?");
            f.setVisible(false);
            Options O = new Options(U);
            db.closeDB();
        }
    }
    
    class Disp extends JPanel {
        @Override
        public void paint(Graphics g) {
            g.setColor(Color.blue);
            g.setFont(new Font("TimesRoman", Font.BOLD, 30)); 
            String str = "You're DEAD\n\n Score:"+score;
            g.drawString(str, 150, 50);  
        }
    }
}
