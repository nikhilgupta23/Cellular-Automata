/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author acer
 */
class DgosperGun implements ActionListener {
    Timer timer;
    Grid G;
    GosperGun Gun;
    JFrame W;
    int width, height;

    DgosperGun(Grid G, int width, int height, JFrame W)
    {
        this.width = width;
        this.height = height;
        this.G = G;
        timer = new Timer(0, this);
        timer.setDelay(10);
        //this.G = G;
        this.W = W;
        //W.add(G);
        Gun = new GosperGun(width, height, G);
        Gun.introducegun(G.map,20,30);
        Gun.introducegun(G.map,65,10);
//        for (int i = 0; i < width; i++)
//            for (int j = 0;j < height; j++)
//                if (G.map[i][j] == 20)
//                    System.out.println("20 There");
        //Gun.introducegun(G.map,50,100);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            G.map = Gun.doSimulationStepGun(G.map);
            DmapGen dmg=new DmapGen(width,height,W);
            G.map = dmg.simulateWaterfalls(G.map);
            W.repaint();
    }
}
