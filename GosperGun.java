/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;


class GosperGun {
    
    int width;
    int height;
    Grid G;
    
    GosperGun(int w, int h, Grid G)
    {
        width = w;
        height = h;
        this.G = G;
    }
    
    public void introducegun(short cells[][],int x_offset, int y_offset)
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
    
    short[][] doSimulationStepGun(short[][] oldMap){
    
    short[][] newMap = new short[width][height];
    for(int x=0;x<oldMap.length;x++)
        for(int y=0; y<oldMap[0].length; y++){     //Required for making the border 1(alive-white)
   //         System.out.print(oldMap[x][y]+",");
            if(oldMap[x][y]!=2)
                newMap[x][y]=oldMap[x][y];
            else
            {//System.out.println(G.charposcol+" "+G.charposrow);
                if(y == G.charposrow && x == G.charposcol)
                {    
                   newMap[x][y]=2;
                    //System.out.println("x"+x+" y"+y);
                }
                else
                {
                    newMap[x][y]=1;
                    //System.out.println("x="+x+" y="+y);
                }
        }}
    //System.out.println();
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
                    //System.out.println("Here20");
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
    int countAliveNeighboursGun(short[][] map, int x, int y){
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
}
