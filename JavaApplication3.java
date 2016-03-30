/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import static javaapplication3.Grid.charposcol;
import static javaapplication3.Grid.charposrow;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Grid extends JPanel implements KeyListener {
    short map[][];
    
  static int charposrow=0,charposcol=0;
    Grid(short map[][])
    {
      this.map=map;
      this.setFocusable(true);
      this.setFocusTraversalKeysEnabled(false);
      this.requestFocus();
      this.addKeyListener(this);
    }
    
    @Override
      public void keyPressed(KeyEvent e) {
          map[charposcol][charposrow]=1;
         switch (e.getKeyCode()) {
         case KeyEvent.VK_LEFT:
            charposcol--;
            System.out.println("VK_LEFT pressed");
            if (!chkValidity())
                charposcol++;
            break;
         case KeyEvent.VK_RIGHT:
            charposcol++;
            System.out.println("VK_RIGHT pressed");
            if (!chkValidity())
                charposcol--;
            break;
         case KeyEvent.VK_UP:
            charposrow--;
            System.out.println("VK_UP pressed");
            if (!chkValidity())
                charposrow++;
            break;
         case KeyEvent.VK_DOWN:
            charposrow++;
            System.out.println("VK_DOWN pressed"+charposrow);
            if (!chkValidity())
                charposrow--;
            break;
         }
         map[charposcol][charposrow]=2;
         repaint();
      }
    
    boolean chkValidity()
    {
        try {
            if(map[charposcol][charposrow]==0) 
            {    JOptionPane.showMessageDialog(null, "BooHoo. You're dead sucker.");
                return false;
            }
            } catch(Exception E)
            {return false; }
            return !(charposrow == 146 || charposcol == 270);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) { }
    
    @Override
    public void paint(Graphics g) { 
        int i;
        for (i = 0; i < 3000; i+=10)
        {
            for (int j = 0; j < 3000; j +=10)
            {
                
                if (map[i/10][j/10] == 1)
                    g.setColor(Color.white);
                else if(map[i/10][j/10] == 0)
                    g.setColor(Color.black);
                else
                    g.setColor(Color.red);
                g.fillRect(i/2, j/2, 5, 5);  
            }
        }
        
    }
}
/**
 *
 * @author Nikhil Gupta
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    
static int width=300,height=300;                                   /////////////////////////////////
short[][] cellmap = new short[width][height];

static float chanceToStartAlive = 0.50f;                           //////////////////////////////////
 
public static short[][] initialiseMap(short[][] map){
    for(int x=0; x<width; x++){
        for(int y=0; y<height; y++){
            if(Math.random() < chanceToStartAlive){
                map[x][y] = 1;
            }
        }
    }
     map[0][0]=2;
    return map;
}

public static short[][] doSimulationStep(short[][] oldMap){
    short[][] newMap = new short[width][height];
    //Loop over each row and column of the map
    for(int x=0; x<oldMap.length; x++){
        for(int y=0; y<oldMap[0].length; y++){
            if(oldMap[x][y]==2) {newMap[x][y]=2;continue;}
            int nbs = countAliveNeighbours(oldMap, x, y);
            //The new value is based on our simulation rules
            //First, if a cell is alive but has too few neighbours, kill it.
            if(oldMap[x][y] == 1){
                if(nbs < 3){
                    newMap[x][y] = 0;
                }
                else{
                    newMap[x][y] = 1;
                }
            } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
            else if(oldMap[x][y] == 0){
                if(nbs > 4){
                    newMap[x][y] = 1;
                }
                else{
                    newMap[x][y] = 0;
                }
            }
        }
    }
    return newMap;
}
//Returns the number of cells in a ring around (x,y) that are alive.
public static int countAliveNeighbours(short[][] map, int x, int y){
    int count = 0;
    for(int i=-1; i<2; i++){
        for(int j=-1; j<2; j++){
            int neighbour_x = x+i;
            int neighbour_y = y+j;
            //If we're looking at the middle point
            if(i == 0 && j == 0){
                //Do nothing, we don't want to add ourselves in!
            }
            //In case the index we're looking at it off the edge of the map
            else if(neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= map.length || neighbour_y >= map[0].length){
                count = count + 1;
            }
            //Otherwise, a normal check of the neighbour
            else if(map[neighbour_x][neighbour_y] == 1){
                count = count + 1;
            }
        }
    }
    return count;
}

public static short[][] generateMap(short map[][])
{
    short map1[][];
    map1 = doSimulationStep(map);
    return map1;
}


static short map[][] = new short[width][height];
static JFrame window = new JFrame();
    public static void main(String[] args) throws InterruptedException {
        
        map=initialiseMap(map);
        
        window.setSize(1366,768);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Grid pq=new Grid(map);
        window.add(pq);
        window.setVisible(true);
        
        for(int i=0; i<10; i++)
        {
            map = doSimulationStep(map);
            pq.map=map;
            window.repaint();
            Thread.sleep(200);
        }
    }
}
