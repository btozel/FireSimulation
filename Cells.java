import java.util.Random;

public class Cells {

    private int[] surroundingCells = new int[9];
    private int cellState;

    public Cells(int[] surroundingCells){
        this.surroundingCells = surroundingCells;

        // 0 1 2
        // 3 4 5
        // 6 7 8
        this.cellState = surroundingCells[4];

        Random rand = new Random();


        // Wall         --> 0
        // Fire         --> 1
        // Ash          --> 2
        // Ground       --> 3
        // Tree         --> 4
        // Pine Tree    --> 5
        // Water        --> 6
        // Bomb         --> 7
        // Dynamite     --> 8


        switch (cellState){

            // Wall
            case 0:{
                // Wall stays always as a wall
                cellState = 0;
                break;
            }

            // Fire
            case 1: {
                for(int i = 0; i < surroundingCells.length ; i++){
                    // If there is a water (6) around the fire
                    if(surroundingCells[i] == 6){
                        // 1/2 chance stay as a fire
                        if(rand.nextInt(2) == 0){
                            cellState = 1;
                        }
                        // 1/2 chance turns into ground
                        else{
                            cellState = 3;
                        }
                    }
                    // If there is no water around the fire
                    else{
                        // 1/4 chance turns into ash (2)
                        if(rand.nextInt(10) == 1){
                            cellState = 2;
                        }
                        // 3/4 chance stay as a fire
                        else{
                            cellState = 1;
                        }
                    }
                }
                break;
            }


            // Ash
            case 2:{
                // 1/3 chance --> turns into Ground(3)
                if(rand.nextInt(3) == 1){
                    cellState = 3;
                }
                break;
            }


            // Ground
            case 3:{
                // 1/10 chance --> new Pine Tree(5)
                if(rand.nextInt(50) == 0){
                    cellState = 5;
                }
                break;
            }

            // Tree
            case 4:{
                for(int i = 0; i < surroundingCells.length; i++){
                    // If there is a fire around the tree
                    if(this.surroundingCells[i] == 1){
                        if(rand.nextInt(10) == 0){
                            cellState = 4;
                        }else{
                            cellState = 1;
                        }
                    }
                }
                break;
            }

            // Pine Tree            // DO UPGRADEEE  -- > PINE TREE CAN BE TREE
            case 5:{
                for(int i = 0; i < this.surroundingCells.length; i++){
                    if(this.surroundingCells[i] == 1){
                        if(rand.nextInt(10) == 0){
                            cellState = 5;
                        }else{
                            cellState = 1;
                        }
                    }
                    else{
                        cellState = 5;
                    }
                }
                break;
            }


            // Water
            case 6:{
                for(int i = 0; i < this.surroundingCells.length; i++){
                    if(this.surroundingCells[i] == 1){
                        int temp = rand.nextInt(4);
                        if(temp == 0){
                            cellState = 6;
                        } else if(temp == 1){
                            cellState = 1;
                        } else {
                            cellState = 2;
                        }
                    }else{
                        cellState = 5;
                    }
                }
                break;
            }
        }
        surroundingCells[4] = cellState;
    }


    public int[] getSurroundingCells(){
        return surroundingCells;
    }


}
