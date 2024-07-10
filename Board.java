import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private int[][] grid;
    private int x;
    private int y;



    public Board(int x, int y, int[][] grid){
        this.x = x / 5;
        this.y = y / 5;
        this.grid = grid;

        JPanel board = new JPanel();
        board.setLayout(null);
        board.setPreferredSize(new Dimension(x, y));
    }


    public void paintComponent(Graphics g){
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(grid[i][j] == 0){                    // Wall
                    g.setColor(Color.ORANGE);
                }else if(grid[i][j] == 1){              // Fire
                    g.setColor(Color.red);
                }else if(grid[i][j] == 2){              // Ash
                    g.setColor(Color.LIGHT_GRAY);
                } else if(grid[i][j] == 3){             // Ground
                    g.setColor(Color.white);
                } else if(grid[i][j] == 4){             // Tree
                    g.setColor(Color.green.darker());
                } else if(grid[i][j] == 5){             // Pine Tree
                    g.setColor(Color.green);
                } else if(grid[i][j] == 6){             // Water
                    g.setColor(Color.CYAN);
                }
                g.fill3DRect(i*5, j*5, 5, 5, false);
            }
        }
    }


}
