/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.event.WindowEvent;
import javax.swing.JFrame;

class PlayGame  {
    int width=135, height=70; 
    JFrame J = new JFrame();
    Grid G;
    
    @SuppressWarnings("empty-statement")
    PlayGame(String user) throws InterruptedException 
    {
       DmapGen DM = new DmapGen(width, height, J);
        
        J.setSize(1366,768);
        J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DB db = new DB();
        db.connDB();
        short cAc = db.CacSelect(user);
        short cHc = db.ChcSelect(user);
        DeadFrame DF = new DeadFrame(user);
        db.closeDB();
        
        G = DM.createGrid();
        DM.assignP(this);
        G.chngDefaultC(cAc, cHc);
        G.assignDF(DF);
        J.add(G);
        J.setVisible(true);
        J.setVisible(true);
        J.add(G);
        Time t = new Time();
        t.assignDF(DF);
    }
    JFrame callFF()
    {
        FloodFill F = new FloodFill();
        return (F.showFrame(G.map, J, width, height));
    }
}
