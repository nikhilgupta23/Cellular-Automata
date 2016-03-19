package se;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;

@SuppressWarnings("serial")
class KeyboardExample extends JPanel {
	
	int cx;
        int cy;
        int D;
        boolean map[][];
        public KeyboardExample(boolean map[][]) {
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
                cx = 1;
                cy = 1;
                this.map = map;
                while (true)
                {
                    if (map[cx][cy+1] && map[cx+1][cy])
                        cx++;
                    else
                        break;
                }
	}

	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
                        int k = e.getKeyCode();
                        switch(k)
                        {
                            case KeyEvent.VK_RIGHT:
                                D = 1;
                                break;
                            case KeyEvent.VK_LEFT:
                                D = 2;
                                break;
                            case KeyEvent.VK_UP:
                                D = 3;
                                break;
                            case KeyEvent.VK_DOWN:
                                D = 4;
                                break;
                        }
                        
                            chCO();
                            repaint();
                            //System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
		}
	}
        
        /*ArrayList<Integer> retDir()
        {
           ArrayList<Integer> I = new ArrayList<Integer>();
           I.add(cx);
           I.add(cy);
           I.add(D);
           return I;
        }*/
        
        boolean chkValidity()
        {
            if (map[cx][cy])
                return false;
            return true;
        }
        void chCO()
        {
            switch(D)
            {
                case 1:
                    cx++;
                    if (cx > 768)
                        cx = 768;
                    if (!chkValidity())
                        cx--;
                    break;
                case 2:
                    cx--;
                    if (cx < 0)
                        cx = 0;
                    if (!chkValidity())
                        cx++;
                    break;
                case 3:
                    cy--;
                    if (cy < 0)
                        cy = 0;
                    if (!chkValidity())
                        cy++;
                    break;
                case 4:
                    cy++;
                    if (cy > 1366)
                        cy = 1366;
                    if (!chkValidity())
                        cy--;
                    break;
            }
        }
        
        public void paint(Graphics g) { 
//        JavaApplication3 ja3=new JavaApplication3();
//        short map[][]=ja3.generateMap();
        int i;
        for (i = 0; i < 3000; i+=10)
        {
            for (int j = 0; j < 3000; j +=10)
            {
                if (map[i/10][j/10])
                    g.setColor(Color.black);
                else 
                    g.setColor(Color.white);
                g.fillRect(i, j, 10, 10);  
            }
        }
        g.setColor(Color.red);
        g.fillRect(cx, cy, 10, 10);
    }
}

