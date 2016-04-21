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
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Grid extends JPanel implements KeyListener, ActionListener {
    short map[][];
    JFrame window;
    Timer timer;
    static int charposrow=0,charposcol=0;
    static int bullI, bullJ;
    Grid(short map[][], JFrame window)
    {
      this.map=map;
      this.setFocusable(true);
      this.setFocusTraversalKeysEnabled(false);
      this.requestFocus();
      this.addKeyListener(this);
      this.window = window;
      timer = new Timer(0, this);
      timer.setDelay(200);
    }
    int lastKeyPress;
    @Override
      public void keyPressed(KeyEvent e) {
        try {
            map[charposcol][charposrow]=1;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    charposcol--;
                    lastKeyPress = e.getKeyCode();
                    System.out.println("VK_LEFT pressed");
                    if (!chkValidity())
                        charposcol++;
                    break;
                case KeyEvent.VK_RIGHT:
                    charposcol++;
                    lastKeyPress = e.getKeyCode();
                    System.out.println("VK_RIGHT pressed");
                    if (!chkValidity())
                        charposcol--;
                    break;
                case KeyEvent.VK_UP:
                    charposrow--;
                    lastKeyPress = e.getKeyCode();
                    System.out.println("VK_UP pressed");
                    if (!chkValidity())
                        charposrow++;
                    break;
                case KeyEvent.VK_DOWN:
                    charposrow++;
                    lastKeyPress = e.getKeyCode();
                    System.out.println("VK_DOWN pressed"+charposrow);
                    if (!chkValidity())
                        charposrow--;
                    break;
                case KeyEvent.VK_SPACE:
                    boolean f = true;
                    for (int i = 0; i < 269; i++)
                        for (int j = 0; j < 145; j++)
                        {
                            if (map[i][j] == 15)
                            {
                                f = false;
                                break;
                            }
                        }
                    if (f)
                        Shoot();
                    break;
            }
            map[charposcol][charposrow]=2;
            window.repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
      static int dir;
    
    
    void Shoot() throws InterruptedException
    {
        try {
        switch(lastKeyPress)
        {
            case KeyEvent.VK_LEFT:
                dir = 0;
                bullI = charposcol-2;
                bullJ = charposrow;
                timer.start();
                break;
            case KeyEvent.VK_RIGHT:
                dir = 1;
                bullI = charposcol+2;
                bullJ = charposrow;
                timer.start();
                break;
            case KeyEvent.VK_DOWN:
                dir = 2;
                bullI = charposcol;
                bullJ = charposrow+2;
                timer.start();
                break;
            case KeyEvent.VK_UP:
                dir = 3;
                bullI = charposcol;
                bullJ = charposrow-2;
                timer.start();
                break;
        }
        } catch (ArrayIndexOutOfBoundsException E) {return;}
    }
    void Boom()
    {
        try {
        switch (dir) {
            case 0:
                bullI--;
                if (map[bullI][bullJ] == 0)
                {
                    map[bullI][bullJ] = 1;
                    if (map[bullI][bullJ-1] == 0)
                        map[bullI][bullJ-1] = 1;
                    if (map[bullI][bullJ+1] == 0)
                        map[bullI][bullJ+1] = 1;
                }
                else if (map[bullI-1][bullJ] == 0)
                {
                    bullI--;
                    map[bullI][bullJ] = 1;
                    if (map[bullI][bullJ-1] == 0)
                        map[bullI][bullJ-1] = 1;
                    if (map[bullI][bullJ+1] == 0)
                        map[bullI][bullJ+1] = 1;
                }   window.repaint();
                break;
            case 1:
                bullI++;
                if (map[bullI][bullJ] == 0)
                {
                    map[bullI][bullJ] = 1;
                    if (map[bullI][bullJ-1] == 0)
                        map[bullI][bullJ-1] = 1;
                    if (map[bullI][bullJ+1] == 0)
                        map[bullI][bullJ+1] = 1;
                }
                else if (map[bullI+1][bullJ] == 0)
                {
                    bullI++;
                    map[bullI][bullJ] = 1;
                    if (map[bullI][bullJ-1] == 0)
                        map[bullI][bullJ-1] = 1;
                    if (map[bullI][bullJ+1] == 0)
                        map[bullI][bullJ+1] = 1;
                }   window.repaint();
                break;
            case 2:
                bullJ++;
                if (map[bullI][bullJ] == 0)
                {
                    map[bullI][bullJ] = 1;
                    if (map[bullI-1][bullJ] == 0)
                        map[bullI-1][bullJ] = 1;
                    if (map[bullI+1][bullJ] == 0)
                        map[bullI+1][bullJ] = 1;
                }
                else if (map[bullI][bullJ+1] == 0)
                {
                    bullJ++;
                    map[bullI][bullJ] = 1;
                    if (map[bullI-1][bullJ] == 0)
                        map[bullI-1][bullJ] = 1;
                    if (map[bullI+1][bullJ] == 0)
                        map[bullI+1][bullJ] = 1;
                }   window.repaint();
                break;
            case 3:
                bullJ--;
                if (map[bullI][bullJ] == 0)
                {
                    map[bullI][bullJ] = 1;
                    if (map[bullI-1][bullJ] == 0)
                        map[bullI-1][bullJ] = 1;
                    if (map[bullI+1][bullJ] == 0)
                        map[bullI+1][bullJ] = 1;
                }
                else if (map[bullI][bullJ-1] == 0)
                {
                    bullJ--;
                    map[bullI][bullJ] = 1;
                    if (map[bullI-1][bullJ] == 0)
                        map[bullI-1][bullJ] = 1;
                    if (map[bullI+1][bullJ] == 0)
                        map[bullI+1][bullJ] = 1;
                }   window.repaint();
                break;
            default:
                break;
        }
        } catch (ArrayIndexOutOfBoundsException E)
        {}
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
        for (i = 0; i < 2690; i+=10)
        {
            for (int j = 0; j < 1450; j +=10)
            {
                
                if (map[i/10][j/10] == 1)
                    g.setColor(Color.lightGray);
                else if(map[i/10][j/10] == 0)
                    g.setColor(Color.black);
                else if(map[i/10][j/10] == 10)
                    g.setColor(Color.red);
                else if(map[i/10][j/10] == 5)
                    g.setColor(Color.yellow);
                else if(map[i/10][j/10] == 15)
                    g.setColor(Color.DARK_GRAY);
                else
                    g.setColor(Color.white);
                g.fillRect(i, j, 10, 10);  
            }
        }
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        //boolean tf = false;
        try {
        switch (dir) {
            case 0:
                if (bullI < 0)
                {   timer.stop();
                map[bullI+2][bullJ] = 1;
                return;
                }
                if (map[bullI][bullJ] == 0 || map[bullI+1][bullJ] == 0)
                {
                    timer.stop();
                    bullI += 2;
                    map[bullI][bullJ] = 1;
                    Boom();
                }
                else 
                {
                    map[bullI][bullJ] = 15;
                    if (map[bullI+2][bullJ] == 15)
                        map[bullI+2][bullJ] = 1;
                    bullI -= 2;
                }
                window.repaint();
                break;
            case 1:
                if (bullI > 269)
                {   timer.stop();
                map[bullI-2][bullJ] = 1;
                return;
                }
                if (map[bullI][bullJ] == 0 || map[bullI-1][bullJ] == 0)
                {
                    timer.stop();
                    bullI -= 2;
                    map[bullI][bullJ] = 1;
                    System.out.println("Boom");
                    Boom();
                }
                else
                {
                    map[bullI][bullJ] = 15;
                    if (map[bullI-2][bullJ] == 15)
                        map[bullI-2][bullJ] = 1;
                    bullI += 2;
                }
                window.repaint();
                break;
            case 2:
                if (bullJ > 145)
                {   timer.stop();
                map[bullI][bullJ-2] = 1;
                return;
                }
                if (map[bullI][bullJ] == 0 || map[bullI][bullJ-1] == 0)
                {
                    timer.stop();
                    bullJ -= 2;
                    map[bullI][bullJ] = 1;
                    Boom();
                }
                else 
                {
                    map[bullI][bullJ] = 15;
                    if (map[bullI][bullJ-2] == 15)
                        map[bullI][bullJ-2] = 1;
                    bullJ += 2;
                }
                window.repaint();
                break;
            case 3:
                if (bullJ < 0)
                {   timer.stop();
                map[bullI][bullJ+2] = 1;
                return;
                }
                if (map[bullI][bullJ] == 0 || map[bullI][bullJ+1] == 0 )
                {
                    timer.stop();
                    bullJ += 2;
                    map[bullI][bullJ] = 1;
                    Boom();
                }
                else 
                {
                    map[bullI][bullJ] = 15;
                    if (map[bullI][bullJ+2] == 15)
                        map[bullI][bullJ+2] = 1;
                    bullJ -= 2;
                }
                window.repaint();
                break;
            default:
                break;
        }
        } catch (ArrayIndexOutOfBoundsException E) {timer.stop();}
    }
}

class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    
static int width=269,height=145;                                   /////////////////////////////////

static float chanceToStartAlive = 0.46f;                           //////////////////////////////////
 
public static short[][] initialiseMap(short[][] map){
    for(int x=0; x<width; x++){
        for(int y=0; y<height; y++){
            if(Math.random() < chanceToStartAlive){
                map[x][y] = 1;
            }
        }
    }
    // map[0][0]=2;
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
static short chkmap[][] = new short[width][height];
static JFrame window = new JFrame();
static Grid pq;
    public static void main(String[] args) throws InterruptedException {
        initialScreen();
        placeTreasure();
        pq.map = map;
        window.repaint();
    }
    
    static void placeTreasure(){
    //How hidden does a spot need to be for treasure?
    //I find 5 or 6 is good. 6 for very rare treasure.
    int treasureHiddenLimit = 5;
    for (int x=0; x < width; x++){
        for (int y=0; y < height; y++){
            if(map[x][y] == 0){
                int nbs = countAliveNeighbours(map, x, y);
                if(nbs >= treasureHiddenLimit){
                    map[x][y] = 5;
                }
            }
        }
    }
}
    static void initialScreen() throws InterruptedException
    {
        map=initialiseMap(map);
        window.setSize(1366,768);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pq = new Grid(map, window);
        window.add(pq);
        window.setVisible(true);
        
        for(int i=0; i<10; i++)
        {
            map = doSimulationStep(map);
            pq.map=map;
            window.repaint();
            Thread.sleep(200);
        }
//        for (int i = 0; i < width; i++)
//            System.arraycopy(map[i], 0, chkmap[i], 0, height);
//        fillGridOI();
//        Thread.sleep(500);
        pq.map = map;
        window.repaint();
        Thread.sleep(200);
    }
    
    static class Sh2
    {
        short n1, n2;
        Sh2(short num1, short num2)
        {
            n1 = num1;
            n2 = num2;
        }
    }
    
    static void fillGridOI() 
    {
        Stack<Sh2> Q = new Stack<>();
        //Sh2 M[] = new Sh2[269];
        short i = 0;
        //moving rowwise
        while (i >= 0 && i < 269)
        {
            if (chkmap[i][0] == 1)     //0 means travellable
            {
                Sh2 S = new Sh2(i,(short)0);
                Q.push(S);
            }
            else 
                break;
            i++;
        }
        Sh2 S1;
        
        while (!Q.isEmpty())
        {
            S1 = Q.pop();
            chkmap[S1.n1][S1.n2] = 10;
            
            System.out.println(S1.n1+" "+S1.n2);
            try {
            if (chkmap[S1.n1][S1.n2+1] == 1)
            {
                Sh2 S2 = new Sh2(S1.n1, (short)(S1.n2+1));
                Q.push(S2);
                System.out.println(S2.n1+" "+S2.n2);
            }
            } catch (ArrayIndexOutOfBoundsException E) {}
            try {
            if (chkmap[S1.n1][S1.n2-1] == 1)
            {
                Sh2 S2 = new Sh2(S1.n1, (short)(S1.n2-1));
                Q.push(S2);
                System.out.println(S2.n1+" "+S2.n2);
            }} catch (ArrayIndexOutOfBoundsException E) {}
            try {
            if (chkmap[S1.n1+1][S1.n2] == 1)
            {
                Sh2 S2 = new Sh2((short)(S1.n1+1), S1.n2);
                Q.push(S2);
                System.out.println(S2.n1+" "+S2.n2);
                pq.map = chkmap;
                window.repaint();
            }
            } catch (ArrayIndexOutOfBoundsException E) {}
            try {
            if (chkmap[S1.n1-1][S1.n2] == 1)
            {
                Sh2 S2 = new Sh2((short)(S1.n1-1), S1.n2);
                Q.push(S2);
                System.out.println(S2.n1+" "+S2.n2);
            }
            } catch (ArrayIndexOutOfBoundsException E) {}
        }
    }
}

//0 - Dead cell, Obstruction 
//1 - Alive, Path
//2- Character
//5- Treasure
//10- Accessibility
//15- Character gun
//20- Guns