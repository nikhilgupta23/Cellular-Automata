/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
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
        timer.setDelay(50);
        //this.G = G;
        this.W = W;
        //W.add(G);
        Gun = new GosperGun(width, height, G);
        //Gun.introducegun(G.map,20,30);
        //Gun.introducegun(G.map,65,10);
//        for (int i = 0; i < width; i++)
//            for (int j = 0;j < height; j++)
//                if (G.map[i][j] == 20)
//                    System.out.println("20 There");
        //Gun.introducegun(G.map,50,100);
        timer.start();
    }
    
     public  short[][] simulateWaterfalls(short map[][])
  {
    Stack<Point> stack= new Stack<>();
    for(int p=0;p<width;p++)
    {
        for(int q=0;q<height-1;q++)
        {
            if(map[p][q]==30 && map[p][q+1]==1)
            {
                int r=q+1;
                while(map[p][r] == 1)
                {
                    map[p][r]=30;
                    r++;
                }
                if(map[p][r] == 0)
                stack.push(new Point(p,r-1));
            }
        }
    }
    while(!stack.isEmpty())
    {
        Point waterfall = stack.pop();
        map[waterfall.x][waterfall.y] = 30;
        while (waterfall.y+1 < height && map[waterfall.x][waterfall.y+1] == 1)
        {
            waterfall.y++;
            map[waterfall.x][waterfall.y] = 30;
        }
        if (waterfall.x -1 >=0 && map[waterfall.x - 1][waterfall.y] == 1)
        {
            stack.push(new Point(waterfall.x -1, waterfall.y));

        }
        if (waterfall.x + 1<width && map[waterfall.x + 1][waterfall.y] == 1)
        {
            stack.push(new Point(waterfall.x + 1, waterfall.y));
        }
    }
    return map;
  }
    
    @Override
    public void actionPerformed(ActionEvent e) {
            G.map = Gun.doSimulationStepGun(G.map);
            //System.out.println("Here");
            G.map = simulateWaterfalls(G.map);
            W.repaint();
    }
}
