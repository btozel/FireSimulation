import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

public class GUI extends JFrame implements MouseMotionListener, MouseListener {

    private int[][] grid;


    private Random random = new Random();

    private int xNew, yNew;

    private int[] temporaryCells = new int[9];
    private int[] newCells = new int[9];


    private int leftClickX = -10;
    private int leftClickY = -10;

    private int rightClickX = -10;
    private int rightClickY = -10;



    public GUI(int xSize, int ySize){
        super("Fire Simulation");

        makeTheGrid(xSize, ySize);



        setDefaultLookAndFeelDecorated(true);

        xNew = xSize / 5;
        yNew = ySize / 5;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(xSize, ySize);
        setResizable(false);
        setLocationRelativeTo(null);

        addMouseListener(this);
        addMouseMotionListener(this);

        printGrid(grid);

        Board myBoard = new Board(xSize, ySize, grid);

        setContentPane(myBoard);
        setVisible(true);


    }

    public void updateCells(int xSize, int ySize){
        Board myBoard = new Board(xSize, ySize, grid);
        setContentPane(myBoard);
        setVisible(true);

        for(int i = 0; i < xNew; i++){
            for(int j = 0 ; j < yNew; j++){
                int tempNumPlus = 0;

                // Double for loop to check around
                // (x, y)       -->     (-1,-1)   (0,-1)   (1,-1)
                //                      (-1, 0)   (0, 0)   (1, 0)
                //                      (-1, 1)   (0, 1)   (1, 1)
                for(int iX = -1; iX <= 1; iX++){
                    for(int jX = -1; jX <= 1; jX++){

                        int xValue = i + iX;
                        int yValue = j + jX;

                        if(xValue == -1){
                            xValue = 0;
                        } else if( xValue == xNew){
                            xValue = xNew - 1;
                        }

                        if(yValue == -1){
                            yValue = 0;
                        } else if( yValue == yNew){
                            yValue = yNew - 1;
                        }


                        temporaryCells[tempNumPlus] = grid[xValue][yValue];
                        tempNumPlus++;
                    }
                }

                Cells myCells = new Cells(temporaryCells);
                newCells = myCells.getSurroundingCells();


                grid[i][j] = newCells[4];


//                for(int iY = -1; iY <= 1; iY++){
//                    for(int jY = -1; jY <= 1; jY++){
//
//                        int xValue = i + iY;
//                        int yValue = j + jY;
//
//                        if(xValue == -1){
//                            xValue = 0;
//                        } else if( xValue == xNew){
//                            xValue = xNew - 1;
//                        }
//
//                        if(yValue == -1){
//                            yValue = 0;
//                        } else if( yValue == yNew){
//                            yValue = yNew - 1;
//                        }
//                        grid[xValue][yValue] = newCells[(iY+1)*3 +(jY+1)];
//                    }
//                }
            }
        }
    }



    public void simulationTimer(int time,int xSize, int ySize){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                updateCells(xSize, ySize);

            }
        };
        Timer timer = new Timer();
        timer.schedule(task,40,time);
    }


    public void makeTheGrid(int xSize, int ySize){

        xNew = xSize / 5;
        yNew = ySize / 5;

        // 5 pixel to 1 Grid Cell
        grid = new int[xNew][yNew];

        for(int i = 0; i < xNew; i++){
            for(int j = 0 ; j < yNew; j++){
                int temp = random.nextInt(100);
                if(temp <= 40){
                    grid[i][j] = 4;
                }else if(40 < temp && temp <= 80){
                    grid[i][j] = 5;
                } else{
                    grid[i][j] = 3;
                }
            }
        }
    }

    public void printGrid(int[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0 ; j < grid[i].length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            if(e.getX() >= 0 && e.getY()>= 0 && e.getX() < xNew * 5 && e.getY() < yNew * 5){
                int x = e.getX() / 5;
                int y = (e.getY() - 32) / 5;
                grid[x][y] = 1;
                System.out.println("Left! X = " + e.getX() + " Y = " + e.getY());
            }
        }if(e.getButton() == MouseEvent.BUTTON3){
            if(e.getX() >= 0 && e.getY()>= 0 && e.getX() < xNew * 5 && e.getY() < yNew * 5){
                int x = e.getX() / 5;
                int y = (e.getY() - 32) / 5;
                grid[x][y] = 6;
                System.out.println("Right! X = " + e.getX() + " Y = " + e.getY());
            }
        }if(e.getButton() == MouseEvent.BUTTON2){
            if(e.getX() >= 0 && e.getY()>= 0 && e.getX() < xNew * 5 && e.getY() < yNew * 5){
                int x = e.getX() / 5;
                int y = (e.getY() - 32) / 5;
                grid[x][y] = 0;
                System.out.println("Middle! X = " + e.getX() + " Y = " + e.getY());
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            if(e.getX() >= 0 && e.getY()>= 0 && e.getX() < xNew * 5 && e.getY() < yNew * 5){
                int x = e.getX() / 5;
                int y = (e.getY() - 32) / 5;
                grid[x][y] = 1;
                System.out.println("Dragging Left! X = " + e.getX() + " Y = " + e.getY());
            }
        }if(e.getButton() == MouseEvent.BUTTON3){
            if(e.getX() >= 0 && e.getY()>= 0 && e.getX() < xNew * 5 && e.getY() < yNew * 5){
                int x = e.getX() / 5;
                int y = (e.getY() - 32) / 5;
                grid[x][y] = 6;
                System.out.println("Dragging Right! X = " + e.getX() + " Y = " + e.getY());
            }
        }if(e.getButton() == MouseEvent.BUTTON2){
            if(e.getX() >= 0 && e.getY()>= 0 && e.getX() < xNew * 5 && e.getY() < yNew * 5){
                int x = e.getX() / 5;
                int y = (e.getY() - 32) / 5;
                grid[x][y] = 0;
                System.out.println("Dragging Middle! X = " + e.getX() + " Y = " + e.getY());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }



    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
