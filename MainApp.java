package se;

import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
class MainApp {
    public static void main(String[] args) throws InterruptedException{
		JFrame frame = new JFrame("The Cave");
                MapGen M = new MapGen();
                boolean finMap[][] = M.generateMap();
		KeyboardExample ke = new KeyboardExample(finMap);
		frame.add(ke);
		frame.setSize(1366,768);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Thread.sleep(1000);
                //redrawFrame(frame, ke);
	}
    /*static void redrawFrame(JFrame window, KeyBoardExample ke)
    {
        window.getContentPane().removeAll();
        window.getContentPane().add(keyboardExample);
        window.revalidate();
        window.repaint();
    }*/
}
