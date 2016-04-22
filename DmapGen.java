package se;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
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
            G.map=fillCaveWithWater(G.map,15);
            G.map=addCaveWaterfalls(G.map,10);
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
    
    public short[][] fillCaveWithWater(short map[][], int depth)
  {
    int x, y;
    int lowestPoint = 0;
    AB: for (y = height - 1; y > 0; y--)
        for (x = 0; x < width; x++)
            if (lowestPoint == 0 && map[y][x] == 1)
            {
                lowestPoint = y;
                break AB;
            }
    for (y = lowestPoint; y > lowestPoint - depth; y--)
        for (x = 0; x < width; x++)
            {
                if (map[x][y] == 1)
                    map[x][y] = 30;
            }
    return map;
  }

  public short[][] addCaveWaterfalls(short map[][], int numWaterFalls)
  {
    int x,y;
    Stack<Point> waterFallSpawns = new Stack<>();
    while (waterFallSpawns.size() < numWaterFalls)
    {
        x = (int)(Math.random()*width);
        y = (int)(Math.random()*height);
        if (map[x][y] == 1 && y - 1 >= 0 && map[x][y-1] == 0)
        {
            waterFallSpawns.push(new Point(x, y));
        }
    }
    int i;
    Point waterfall;
    while(!waterFallSpawns.isEmpty())
    {
        waterfall = waterFallSpawns.pop();
        map[waterfall.x][waterfall.y] = 30;
        while (waterfall.y+1 < height && map[waterfall.x][waterfall.y+1] == 1)
        {
            waterfall.y++;
            map[waterfall.x][waterfall.y] = 30;
        }
        if (waterfall.x-1 >=0 && map[waterfall.x - 1][waterfall.y] == 1)
        {
            waterFallSpawns.push(new Point(waterfall.x -1, waterfall.y));

        }
        if (waterfall.x+1 < width && map[waterfall.x + 1][waterfall.y] == 1)
        {
            waterFallSpawns.push(new Point(waterfall.x + 1, waterfall.y));
        }
    }
    return map;
  }
    
}
