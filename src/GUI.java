

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    int row,col;
    private GameMap gameMap;
    private  JButton[][] gameCells;

    private boolean isRunning;

    public GUI(GameMap gameMap){
        this.row=gameMap.getRow();
        this.col=gameMap.getCol();
        this.gameMap=gameMap;
        initGameGUI();
    }

    public void initGameGUI(){
        JPanel backPanel,centerPanel;
        backPanel= new JPanel(new BorderLayout());
        centerPanel=new JPanel(new GridLayout(row,col));
        this.setContentPane(backPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        backPanel.add(centerPanel,"Center");

        gameCells=new JButton[row][col];

        for(int x=0;x<row;x++){
            for(int y=0;y<col;y++){
                gameCells[x][y]=new JButton("");
                gameCells[x][y].setBackground(Color.white);
                centerPanel.add(gameCells[x][y]);
            }
        }

        //设置窗口
        int sizelx,sizely;
        sizelx=Math.max(row*30,800);
        sizely=Math.max(col*30,800);
        this.setSize(sizely,sizelx);
        this.setResizable(true);
        this.setLocationRelativeTo(null);//让窗口居中显示
        this.setVisible(true);
    }

    public void startGame(){
        //随机开局
        gameMap.randonInitCell();
        showWorld();
        isRunning = false;
        //开始
        Thread thread;
        isRunning = true;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    Change();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        thread.start();

    }

    public void Change(){
        gameMap.updateOfCell();
        showWorld();
    }

    public void showWorld(){
        for(int x=0;x<row;x++){
            for(int y=0;y<col;y++){
                if(gameMap.getCellIsLive(x,y)){
                    gameCells[x][y].setBackground(Color.black);
                }
                else{
                    gameCells[x][y].setBackground(Color.white);
                }
            }
        }
    }

}