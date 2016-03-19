/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

/**
 *
 * @author acer
 */
public class MapGen {
    int width=300,height=300;                                   /////////////////////////////////
    boolean[][] cellmap = new boolean[width][height];

    static float chanceToStartAlive = 0.45f;                           //////////////////////////////////
 
    void initialiseMap(){
    for(int x=0; x<width; x++){
        for(int y=0; y<height; y++){
            if(Math.random() < chanceToStartAlive){
                cellmap[x][y] = true;
            }
        }
    }
}

void doSimulationStep(){
    boolean[][] newMap = new boolean[width][height];
    //Loop over each row and column of the map
    for(int x=0; x<cellmap.length; x++){
        for(int y=0; y < cellmap[0].length; y++){
            int nbs = countAliveNeighbours(x, y);
            //The new value is based on our simulation rules
            //First, if a cell is alive but has too few neighbours, kill it.
            if(cellmap[x][y]){
                if(nbs < 4){
                    newMap[x][y] = false;
                }
                else{
                    newMap[x][y] = true;
                }
            } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
            else{
                if(nbs > 4){
                    newMap[x][y] = true;
                }
                else{
                    newMap[x][y] = false;
                }
            }
        }
    }
    cellmap = newMap;
}
//Returns the number of cells in a ring around (x,y) that are alive.
public int countAliveNeighbours(int x, int y){
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
            else if(neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= cellmap.length || neighbour_y >= cellmap[0].length){
                count = count + 1;
            }
            //Otherwise, a normal check of the neighbour
            else if(cellmap[neighbour_x][neighbour_y]){
                count = count + 1;
            }
        }
    }
    return count;
}

public boolean[][] generateMap(){
    //Create a new map
//    boolean[][] map = new boolean[width][height];
    //Set up the map with random values
    
    //And now run the simulation for a set number of steps
        //////////////////////////////////////////Noofsteps
        
        initialiseMap();
        for (int i = 0; i < 1000; i++)
            doSimulationStep();
        return cellmap;
}

}
