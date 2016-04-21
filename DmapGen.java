package se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    boolean isRun;
    PlayGame P;

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
        isRun = true;
        timer.start();
    }
    
    void assignP(PlayGame P)
    {
        this.P = P;
    }
    
    JFrame FF;

    Grid createGrid()
    {
        return G;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (count == 100)
        {    timer.stop();
            FF.setVisible(false);
            G.map = M.placeTreasure(G.map);
            W.repaint();
            W.setVisible(true);
            return;
        }
        if (count == 12)
            count++;
        else {
        G.map = M.doSimulationStep(G.map);
        count++;
        if (count == 11)
        {
            timer.stop();
            count++;
            for(int p=0;p<3;p++)
            {
                G.map = M.doSmoothSimulationStep(G.map);
            }
        //G.map = M.placeTreasure(G.map);
        FF = P.callFF();
        timer.start();
        }        
            W.repaint();
        }
    }
}
