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
class MapGen {
    
    int width;
    int height;
    float chanceToStartAlive; //= 0.45f;     
    
    MapGen (int w, int h, float c)
    {
        width = w;
        height = h;
        chanceToStartAlive = c;
    }
    
    short[][] initialiseMap(short[][] map){
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
    
    short[][] generateMap(short map[][])
    {
        short map1[][];
        map1 = doSimulationStep(map);
        return map1;
    }
    
    short[][] doSimulationStep(short[][] oldMap){
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

void placeTreasure(short[][] map){
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

    //Returns the number of cells in a ring around (x,y) that are alive.
    int countAliveNeighbours(short[][] map, int x, int y){
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
}
