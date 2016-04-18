/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public final class PlayGame {
    int width=135, height=70; 
    JFrame J = new JFrame();
    Grid G;
    
    PlayGame() throws InterruptedException 
    {
       DmapGen DM = new DmapGen(width, height, J);
        
        J.setSize(1366,768);
        J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        G = DM.createGrid();
        
        J.add(G);
        J.setVisible(true);
        //if ())
            AccessCheck();
        J.setVisible(true);
        J.add(G);
        new Time();
    }
    
    void AccessCheck() throws InterruptedException
    {
        J.setVisible(false);
        JFrame Fl = new JFrame();
        Fl.setSize(1366,768);
        Fl.setVisible(true);
        FloodFill F = new FloodFill();
        short[][] chkmap = new short[width][height];
        for (int i = 0; i < width; i++)
            System.arraycopy(G.map[i], 0, chkmap[i], 0, height);
        Grid Gf = new Grid(chkmap, width, height, Fl);
        Fl.add(Gf);
        F.fillGrid(chkmap, Fl,width, height);
    }
}
