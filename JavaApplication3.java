/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

/**
 *
 * @author acer
 */
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Grid extends JPanel implements KeyListener, ActionListener {
    short map[][];
    Timer timer;
    static int bullI, bullJ;
    static int charposrow=1,charposcol=1;
    
    
    Grid(short map[][])
    {
      this.map=map;
      this.setFocusable(true);
      this.setFocusTraversalKeysEnabled(false);
      this.requestFocus();
      this.addKeyListener(this);
      timer = new Timer(0, this);
      timer.setDelay(200);
      map[charposcol][charposrow] = 2;
    }
    
    boolean chkValidity()
    {
        try {
            if(map[charposcol][charposrow]==0 || map[charposcol][charposrow]==20) 
            {    JOptionPane.showMessageDialog(null, "BooHoo. You're dead sucker.");
                return false;
            }
            } catch(Exception E)
            {return false; }
            return !(charposrow == 146 || charposcol == 270);
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
                        try {
                        Shoot();
                        } catch (InterruptedException E) {}
                    break;
            }
            map[charposcol][charposrow]=2;
            repaint();
        } catch (ArrayIndexOutOfBoundsException ex) 
        {   map[charposcol][charposrow]=2;
            repaint();}
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
                }   repaint();
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
                }   repaint();
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
                }   repaint();
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
                }   repaint();
                break;
            default:
                break;
        }
        } catch (ArrayIndexOutOfBoundsException E)
        {}
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
                map[charposcol][charposrow] = 2;
                repaint();
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
                map[charposcol][charposrow] = 2;
                repaint();
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
                map[charposcol][charposrow] = 2;
                repaint();
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
                map[charposcol][charposrow] = 2;
                repaint();
                break;
            default:
                break;
        }
        } catch (ArrayIndexOutOfBoundsException E) {timer.stop();}
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
                
                switch (map[i/10][j/10]) {
                    case 1:
                        g.setColor(Color.white);
                        break;
                    case 0:
                        g.setColor(Color.black);
                        break;
                    case 10:
                        g.setColor(Color.red);
                        break;
                    case 20:
                        g.setColor(Color.GRAY);
                        break;
                    case 5:
                        g.setColor(Color.yellow);
                        break;
                    case 15:
                        g.setColor(Color.DARK_GRAY);
                        break;
                    default:
                        g.setColor(Color.blue);
                        break;
                }
                g.fillRect(i/2, j/2, 10, 10);  
            }
        }
        
    }
}

class JavaApplication3 implements ActionListener{

    /**
     * @param args the command line arguments
     */
    
static int width=269,height=145;                                   /////////////////////////////////
static Timer timer;
static float chanceToStartAlive = 0.45f;                           //////////////////////////////////

JavaApplication3()
{
    timer = new Timer(0, this);
    timer.setDelay(10);
    for(int i=0; i<10; i++)
        {
            map = doSimulationStep(map);
            pq.map=map;
            window.repaint();
            try {
            Thread.sleep(200);
            } catch (InterruptedException E) {}
        }
    introducegun(map,100,50);
    introducegun(map,50,100);
    timer.start();
}
 

public static void introducegun(short cells[][],int x_offset, int y_offset)
    {
            cells[1+x_offset][5+y_offset]=20;
            cells[1+x_offset][6+y_offset]=20;
            cells[2+x_offset][5+y_offset]=20;
            cells[2+x_offset][6+y_offset]=20;
            cells[11+x_offset][5+y_offset]=20;
            cells[11+x_offset][6+y_offset]=20;
            cells[11+x_offset][7+y_offset]=20;
            cells[12+x_offset][4+y_offset]=20;
            cells[12+x_offset][8+y_offset]=20;
            cells[13+x_offset][3+y_offset]=20;
            cells[13+x_offset][9+y_offset]=20;
            cells[14+x_offset][3+y_offset]=20;
            cells[14+x_offset][9+y_offset]=20;
            cells[15+x_offset][6+y_offset]=20;
            cells[16+x_offset][4+y_offset]=20;
            cells[16+x_offset][8+y_offset]=20;
            cells[17+x_offset][5+y_offset]=20;
            cells[17+x_offset][6+y_offset]=20;
            cells[17+x_offset][7+y_offset]=20;
            cells[18+x_offset][6+y_offset]=20;
            cells[21+x_offset][3+y_offset]=20;
            cells[21+x_offset][4+y_offset]=20;
            cells[21+x_offset][5+y_offset]=20;
            cells[22+x_offset][3+y_offset]=20;
            cells[22+x_offset][4+y_offset]=20;
            cells[22+x_offset][5+y_offset]=20;
            cells[23+x_offset][2+y_offset]=20;
            cells[23+x_offset][6+y_offset]=20;
            cells[25+x_offset][1+y_offset]=20;
            cells[25+x_offset][2+y_offset]=20;
            cells[25+x_offset][6+y_offset]=20;
            cells[25+x_offset][7+y_offset]=20;
            cells[35+x_offset][3+y_offset]=20;
            cells[35+x_offset][4+y_offset]=20;
            cells[36+x_offset][3+y_offset]=20;
            cells[36+x_offset][4+y_offset]=20;
//            cells[35+x_offset][22+y_offset]=20;
//            cells[35+x_offset][23+y_offset]=20;
//            cells[35+x_offset][25+y_offset]=20;
//            cells[36+x_offset][22+y_offset]=20;
//            cells[36+x_offset][23+y_offset]=20;
//            cells[36+x_offset][25+y_offset]=20;
//            cells[36+x_offset][26+y_offset]=20;
//            cells[36+x_offset][27+y_offset]=20;
//            cells[37+x_offset][28+y_offset]=20;
//            cells[38+x_offset][22+y_offset]=20;
//            cells[38+x_offset][23+y_offset]=20;
//            cells[38+x_offset][25+y_offset]=20;
//            cells[38+x_offset][26+y_offset]=20;
//            cells[38+x_offset][27+y_offset]=20;
//            cells[39+x_offset][23+y_offset]=20;
//            cells[39+x_offset][25+y_offset]=20;
//            cells[40+x_offset][23+y_offset]=20;
//            cells[40+x_offset][25+y_offset]=20;
//            cells[41+x_offset][24+y_offset]=20;
    }
 
 
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

void placeTreasure(){
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

public static short[][] doSimulationStepGun(short[][] oldMap){
    
    short[][] newMap = new short[width][height];
    for(int x=0;x<oldMap.length;x++)
        for(int y=0; y<oldMap[0].length; y++){     //Required for making the border 1(alive-white)
   //         System.out.print(oldMap[x][y]+",");
            if(oldMap[x][y]!=2)
                newMap[x][y]=oldMap[x][y];
            else
            {System.out.println(Grid.charposcol+" "+Grid.charposrow);
                if(y==Grid.charposrow && x==Grid.charposcol)
                    
                {newMap[x][y]=2;
                System.out.println("x"+x+" y"+y);
                }
                else
                {newMap[x][y]=1;
                System.out.println("x="+x+" y="+y);
                }
        }}
    System.out.println();
    //Loop over each row and column of the map
    for(int x=1; x<oldMap.length-1; x++){
        for(int y=1; y<oldMap[0].length-1; y++){
            if(newMap[x][y]==2) {newMap[x][y]=2;continue;}
            int nbs = countAliveNeighboursGun(oldMap, x, y);
            //The new value is based on our simulation rules
            //First, if a cell is alive but has too few neighbours, kill it.
            if(newMap[x][y] == 20){         //20 is alive in this, 0 and 1 are dead
                if(nbs == 2 || nbs == 3){
                    newMap[x][y] = 20;
                }
                else{
                    newMap[x][y] = 1;
                }
            } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
            else if(newMap[x][y] == 1 || newMap[x][y] == 0){
                if(nbs == 3){
                    newMap[x][y] = 20;
                }
                else{
                    newMap[x][y] = newMap[x][y];
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
            //counting alive cells
            else if(map[neighbour_x][neighbour_y] == 1){
                count = count + 1;
            }
        }
    }
    return count;
}

public static int countAliveNeighboursGun(short[][] map, int x, int y){
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
            else if(map[neighbour_x][neighbour_y] == 20){
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
static Grid pq;
    public static void main(String[] args) throws InterruptedException {
        
        map=initialiseMap(map);
        window.setSize(1366,768);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pq=new Grid(map);
        window.add(pq);
        window.setVisible(true);
        JavaApplication3 J = new JavaApplication3();
        
        J.placeTreasure();
        short chkmap[][] = new short[width][height];
        chkmap = map;
        //fillGridOI(chkmap, pq);
        pq.map = map;
        
        
        //introducegun(map,150,150);
        //window.repaint();
//        Thread.sleep(3000);
        
        //Thread.sleep(200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            map = doSimulationStepGun(map);
            pq.map=map;
            window.repaint();
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
    
    static void fillGridOI(short[][] arr, Grid pq) 
    {
        Stack<Sh2> Q = new Stack<>();
        //Sh2 M[] = new Sh2[269];
        short i = 0;
        //moving rowwise
        while (i >= 0 && i < 269)
        {
            if (arr[i][0] == 1)     //0 means travellable
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
            arr[S1.n1][S1.n2] = 10;
            
            System.out.println(S1.n1+" "+S1.n2);
            try {
            if (arr[S1.n1][S1.n2+1] == 1)
            {
                Sh2 S2 = new Sh2(S1.n1, (short)(S1.n2+1));
                Q.push(S2);
                System.out.println(S2.n1+" "+S2.n2);
            }
            } catch (ArrayIndexOutOfBoundsException E) {}
            try {
            if (arr[S1.n1][S1.n2-1] == 1)
            {
                Sh2 S2 = new Sh2(S1.n1, (short)(S1.n2-1));
                Q.push(S2);
                System.out.println(S2.n1+" "+S2.n2);
            }} catch (ArrayIndexOutOfBoundsException E) {}
            try {
            if (arr[S1.n1+1][S1.n2] == 1)
            {
                Sh2 S2 = new Sh2((short)(S1.n1+1), S1.n2);
                Q.push(S2);
                System.out.println(S2.n1+" "+S2.n2);
                pq.map = arr;
                window.repaint();
            }
            } catch (ArrayIndexOutOfBoundsException E) {}
            try {
            if (arr[S1.n1-1][S1.n2] == 1)
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
