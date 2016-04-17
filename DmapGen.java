
package javaapplication3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author acer
 */
class DmapGen implements ActionListener {

    Timer timer;
    Grid G;
    MapGen M;
    JFrame W;
    int width;
    int height;
    float chanceToStartAlive = 0.45f;
    short map[][];
    int count;
    
    DmapGen(int width, int height, JFrame W)
    {
        timer = new Timer(0, this);
        timer.setDelay(50);
        this.width = width;
        this.height = height;
        this.W = W;
        count = 0;
        map = new short[width][height];
        M = new MapGen(width, height, chanceToStartAlive);
        map =  M.initialiseMap(map);
        G = new Grid(map, width, height, W);
        W.add(G);
        timer.start();
    }
    
    Grid createGrid()
    {
        return G;
    }
         
    @Override
    public void actionPerformed(ActionEvent e) {
        G.map = M.doSimulationStep(G.map);
        count++;
        if (count > 10)
        {   
            timer.stop();
            M.placeTreasure(G.map);
        }
        W.repaint();
    }
}
