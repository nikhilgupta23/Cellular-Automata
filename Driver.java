/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Driver {
    int width=269,height=145; 
    JFrame J = new JFrame();
    Grid G;
    
    Driver() throws InterruptedException
    {
        DmapGen DM = new DmapGen(width, height, J);
        J.setSize(1366,768);
        J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread.sleep(2000);
        G = DM.createGrid();
        J.add(G);
        J.setVisible(true);
        AccessCheck();
        J.setVisible(true);
        J.add(G);
    }
    
    public static void main(String Args[]) throws InterruptedException 
    {
        Driver D = new Driver();
        DgosperGun SG = new DgosperGun(D.G, D.width, D.height, D.J);
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
        Grid Gf = new Grid(chkmap);
        Fl.add(Gf);
        F.fillGrid(chkmap, Fl);
         Thread.sleep(1000);
        Fl.dispatchEvent(new WindowEvent(Fl, WindowEvent.WINDOW_CLOSING));
    }
}
