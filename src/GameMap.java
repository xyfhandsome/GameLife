

public class GameMap {
    private int row,col;
    private Cell[][] cells=new Cell[100][100];

    public GameMap(int row,int col){
        this.row=row;
        this.col=col;
        for(int x = 0; x < row; x++)
            for(int y=0;y<col;y++){
                cells[x][y]=new Cell(x,y);
                cells[x][y].setIsLive(false);
            }
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public boolean getCellIsLive(int x, int y){
        return cells[x][y].getIsLive();
    }
    //Ëæ»ú³õÊ¼»¯Ï¸°û
    public void randonInitCell(){
        for(int x=0;x<row;x++)
            for(int y=0;y<col;y++)
                cells[x][y].setIsLive(Math.random() > 0.5);
    }

    public void updateOfCell(){
        Cell[][] ce=new Cell[row][col];
        //cell[][] ce=cell;
        for(int x=0;x<row;x++) {
            for (int y = 0; y < col; y++) {
                ce[x][y]=new Cell(x,y);
            }
        }
        for(int x=0;x<row;x++) {
            for (int y = 0; y < col; y++){
                int c=0;
                for(int i=x-1;i<=x+1;i++){
                    for(int j=y-1;j<=y+1;j++){
                        if(i>=0&&i<row&&j>=0&&j<col&&cells[i][j].getIsLive())c++;
                    }
                }
                if(cells[x][y].getIsLive())c--;
                if(c==3)ce[x][y].setIsLive(true);
                else if(c==2)ce[x][y].setIsLive(cells[x][y].getIsLive());
                else ce[x][y].setIsLive(false);
            }
        }
        for(int x=0;x<row;x++) {
            for (int y = 0; y < col; y++) {
                cells[x][y]=ce[x][y];
            }
        }
    }
}

