/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.Timer;

class FloodFill implements ActionListener {
JFrame W;
int width;
int height;
JFrame Fl;
Timer t;

    FloodFill() {
        this.t = new Timer(0, this);
        t.setDelay(1000);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Fl.repaint();
    }
    
    class Sh2
    {
        short n1, n2;
        Sh2(short num1, short num2)
        {
            n1 = num1;
            n2 = num2;
        }
    }

    void fillGrid(short[][] arr)
    {
        W.setVisible(false);
        Fl = new JFrame();
        Fl.setSize(1366,768);
        Grid FG = new Grid(arr, width, height, Fl);
        Fl.add(FG);
        Fl.setVisible(true);
        Stack<Sh2> Q = new Stack<>();
        //Sh2 M[] = new Sh2[269];
        short i = 0;
        //moving rowwise
        while (i >= 0 && i < width)
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
            if (arr[S1.n1][S1.n2+1] == 1 )
            {
                Sh2 S2 = new Sh2(S1.n1, (short)(S1.n2+1));
                Q.push(S2);
                System.out.println(S2.n1+" "+S2.n2);
                Fl.repaint();
            }
            } catch (ArrayIndexOutOfBoundsException E) {}
            try {
            if (arr[S1.n1][S1.n2-1] == 1 )
            {
                Sh2 S2 = new Sh2(S1.n1, (short)(S1.n2-1));
                Q.push(S2);
                System.out.println(S2.n1+" "+S2.n2);
                FG.map = arr;
                Fl.repaint();
            }} catch (ArrayIndexOutOfBoundsException E) {}
            try {
            if (arr[S1.n1+1][S1.n2] == 1 )
            {
                Sh2 S2 = new Sh2((short)(S1.n1+1), S1.n2);
                Q.push(S2);
                System.out.println(S2.n1+" "+S2.n2);
                FG.map = arr;
                Fl.repaint();
            }
            } catch (ArrayIndexOutOfBoundsException E) {}
            try {
            if (arr[S1.n1-1][S1.n2] == 1)
            {
                Sh2 S2 = new Sh2((short)(S1.n1-1), S1.n2);
                Q.push(S2);
                System.out.println(S2.n1+" "+S2.n2);
                FG.map = arr;
                Fl.repaint();
                //Fl.validate();
            }
            } catch (ArrayIndexOutOfBoundsException E) {}
        }
        t.stop();
    }
    
    JFrame showFrame(short map[][], JFrame W, int width, int height)
    {
        this.W = W;
        this.width = width;
        this.height = height;
        short[][] chkmap = new short[width][height];
        for (int i = 0; i < width; i++)
            System.arraycopy(map[i], 0, chkmap[i], 0, height);
        t.start();
        fillGrid(chkmap);
        return Fl;
    }
}
