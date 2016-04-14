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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Grid extends JPanel implements KeyListener, ActionListener {
    short map[][];
    Timer timer;
    int bullI, bullJ;
    int charposrow = 1,charposcol = 1;
    Color cave;
    Color ch;
    
    Grid(short map[][])
    {
      this.map=map;
      Initialise();
    }
    
    Grid (short map[][], short caveColor, short charColor)
    {
        this.map=map;
        Initialise();
        initialiseC(caveColor, charColor);
    }
    
    void initialiseC(short caveColor, short charColor)
    {
        
    }
    
    void Initialise()
    {
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