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
                cellState = 0;
                break;
            }

            // Fire
            case 1: {
                for(int i = 0; i < this.surroundingCells.length ; i++){
                    if(surroundingCells[i] == 6){
                        cellState = 3;
                    }
                    else{
                        if(rand.nextInt(3) == 1){
                            cellState = 2;
                        }else{
                            cellState = 1;
                        }
                    }
                }
                break;
            }


            // Ash
            case 2:{
                if(rand.nextInt(3) == 1){
                    cellState = 3;
                }
                break;
            }


            // Ground
            case 3:{
                // 1/4 chance --> new Pine Tree
                if(rand.nextInt(4) == 0){
                    cellState = 5;
                }
                break;
            }

            // Tree
            case 4:{
                for(int i = 0; i < this.surroundingCells.length; i++){
                    if(this.surroundingCells[i] == 1){
                        if(rand.nextInt(6) == 0){
                            cellState = 1;
                        }else{
                            cellState = 4;
                        }
                    }
                }
                break;
            }

            // Pine Tree            // DO UPGRADEEE  -- > PINE TREE CAN BE TREE
            case 5:{
                for(int i = 0; i < this.surroundingCells.length; i++){
                    if(this.surroundingCells[i] == 1){
                        if(rand.nextInt(3) == 0){
                            cellState = 1;
                        }else{
                            cellState = 5;
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
