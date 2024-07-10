/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 1
 * Members  :
 * 1. 5026231047 - Muhammad Rafly Ayman Masagung
 * 2. 5026231120 - Ida Bagus Adhiraga Yudhistira
 * 3. 5999232023 - Abel, Pierre, Philippe Chartier
 * ------------------------------------------------------
 */

public class Snake{
    private int tailPosition;
    private int headPosition;

    public Snake(int t, int h){
        this.tailPosition = t;
        this.headPosition = h;
    }

    public void setTailPosition(int t){
        this.tailPosition = t;
    }

    public void setHeadPosition(int h){
        this.headPosition = h;
    }

    public int getTailPosition(){
        return tailPosition;
    }

    public int getHeadPosition(){
        return headPosition;
    }
}