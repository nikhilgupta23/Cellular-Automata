/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Grid extends JPanel implements KeyListener, ActionListener {
    short map[][];
    Timer timer;
    int bullI, bullJ;
    int charposrow = 1,charposcol = 1;
    Color cave = Color.BLACK;
    Color ch = Color.blue;
    int width, height;
    int lives = 3;
    JFrame W;
    int noOfTreasure = 0;
    DgosperGun Dg;
    DeadFrame D;
    
    Grid(short map[][], int width, int height, JFrame W)
    {
      this.map=map;
      this.width = width;
      this.height = height;
      this.W = W;
      Initialise();
    }
    
    void assignDF(DeadFrame D)
    {
        this.D = D;
    }

    void chngDefaultC(short caveColor, short charColor)
    {
        switch (caveColor)
        {
            case 0:
                cave = Color.black;
                break;
            case 1:
                cave = Color.red;
                break;
            case 2:
                cave = Color.blue;
                break;
            case 3:
                cave = Color.green;
                break;
            case 4:
                cave = Color.black;
                break;
        }
        switch (charColor)
        {
            case 0:
                ch = Color.black;
                break;
            case 1:
                ch = Color.red;
                break;
            case 2:
                ch = Color.blue;
                break;
            case 3:
                ch = Color.green;
                break;
            case 4:
                ch = Color.black;
                break;
        }
    }

    void Initialise()
    {
      this.setFocusable(true);
      this.setFocusTraversalKeysEnabled(false);
      this.requestFocus();
      this.addKeyListener(this);
      timer = new Timer(0, this);
      timer.setDelay(40);
      map[charposcol][charposrow] = 2;
      Dg = new DgosperGun(this, width, height, W);
    }

    boolean chkValidity()
    {
        try {
            if(map[charposcol][charposrow]==0 || map[charposcol][charposrow] == 20)
            {   lives--;
                System.out.println(lives);
                if (lives <= 0)
                {
                    float time = (float) 1.5;
                    D.Score(noOfTreasure*10);
                    W.setVisible(false);
                }
                return false;
            }
            if (map[charposcol][charposrow] == 5)
                noOfTreasure++;
            } catch(ArrayIndexOutOfBoundsException E)
            {   return false; }
            return !(charposrow == height+1 || charposcol == width+1);
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
                    for (int i = 0; i < width; i++)
                        for (int j = 0; j < height; j++)
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
        switch (dir) {
            case 0:
                bullI--;
                break;
            case 1:
                bullI++;
                break;
            case 2:
                bullJ++;
                break;
            case 3:
                bullJ--;
                break;
            default:
                break;
        }
        
        if (width > (36+bullI) && height > (8+bullJ))
            Dg.Gun.introducegun(map, bullI, bullJ);
        else {
        try {
        switch (dir) {
            case 0:
                //bullI--;
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
                //bullI++;
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
                //bullJ++;
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
                //bullJ--;
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
    }
    
    void remove15()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if(map[i][j] == 15)
                    map[i][j] = 1;
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
                    remove15();
                    map[charposcol][charposrow] = 2;
                    map[bullI+1][bullJ] = 1;
                return;
                }
                if (map[bullI][bullJ] == 0)
                {
                    timer.stop();
                    bullI++;
                    remove15();
                    map[bullI][bullJ] = 1;
                    Boom();
                }
                else
                {
                    map[bullI][bullJ] = 15;
                    if (map[bullI+1][bullJ] == 15)
                        map[bullI+1][bullJ] = 1;
                    bullI --;
                }
                map[charposcol][charposrow] = 2;
                repaint();
                break;
            case 1:
                if (bullI > width)
                {   timer.stop();
                remove15();
                map[charposcol][charposrow] = 2;
                map[bullI-1][bullJ] = 1;
                return;
                }
                else if (map[bullI][bullJ] == 0)
                {
                    timer.stop();
                    bullI --;
                    remove15();
                    map[bullI][bullJ] = 1;
                    Boom();
                }
                else
                {
                    map[bullI][bullJ] = 15;
                    if (map[bullI-1][bullJ] == 15)
                        map[bullI-1][bullJ] = 1;
                    bullI++;
                }
                map[charposcol][charposrow] = 2;
                repaint();
                break;
            case 2:
                if (bullJ > height)
                {   timer.stop();
                remove15();
                map[charposcol][charposrow] = 2;
                map[bullI][bullJ-1] = 1;
                return;
                }
                else if (map[bullI][bullJ] == 0)
                {
                    timer.stop();
                    bullJ -= 2;
                    remove15();
                    map[bullI][bullJ] = 1;
                    Boom();
                }
                else
                {
                    map[bullI][bullJ] = 15;
                    if (map[bullI][bullJ-1] == 15)
                        map[bullI][bullJ-1] = 1;
                    bullJ++;
                }
                map[charposcol][charposrow] = 2;
                repaint();
                break;
            case 3:
                if (bullJ < 0)
                {   timer.stop();
                remove15();
                map[charposcol][charposrow] = 2;
                map[bullI][bullJ+2] = 1;
                return;
                }
                else if (map[bullI][bullJ] == 0)
                {
                    timer.stop();
                    bullJ++;
                    remove15();
                    map[bullI][bullJ] = 1;
                    Boom();
                }
                else
                {
                    map[bullI][bullJ] = 15;
                    if (map[bullI][bullJ+1] == 15)
                        map[bullI][bullJ+1] = 1;
                    bullJ--;
                }
                map[charposcol][charposrow] = 2;
                repaint();
                break;
            default:
                break;
        }
        } catch (ArrayIndexOutOfBoundsException E) 
        {   timer.stop();
            map[charposcol][charposrow] = 2;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void paint(Graphics g) {
        int i;
        for (i = 0; i < width*10; i+=10)
        {
            for (int j = 0; j < height*10; j +=10)
            {

                switch (map[i/10][j/10]) {
                    case 1:
                        g.setColor(Color.white);
                        break;
                    case 0:
                        g.setColor(cave);
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
                        g.setColor(ch);
                        break;
                }
                g.fillRect(i, j, 10, 10);
            }
        }
        g.setColor(Color.ORANGE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
        String str = "Time Left: "+Time.seconds ;
        g.drawString(str, 1000, 20);
        g.setColor(Color.MAGENTA);
        str = "Lives left: "+lives;
        g.drawString(str, 1002, 40);
    }
}
