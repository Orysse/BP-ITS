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

import java.util.ArrayList;
import java.util.Scanner;

public class SnL{
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private ArrayList<Question> questions;
    private int boardSize;
    private int gameStatus;
    private int nowPlaying;

    public SnL(int s){
        this.boardSize = s;
        this.players = new ArrayList<Player>();
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.questions = new ArrayList<Question>();
        this.gameStatus = 0;
    }
    public void setBoardSize(int s){
        this.boardSize = s;
    }
    public void setGameStatus(int s){
        this.gameStatus = s;
    }
    public int getGameStatus(){
        return this.gameStatus;
    }
    public void play(){
        Player playerInTurn;
        Scanner read=new Scanner(System.in);
        System.out.println("Please enter Player 1: ");
        String player1 = read.nextLine();
        System.out.println("Please enter Player 2: ");
        String player2 = read.nextLine();

        //object instantiation
        Player p1 = new Player(player1);
        Player p2 = new Player(player2);
        initiateGame();
        addPlayer(p1);
        addPlayer(p2);

        do {
            playerInTurn =getWhoseTurn();
            System.out.println("Now Playing "+ playerInTurn.getName());
            playOneRoll(playerInTurn);
            System.out.println("New Position:  "+ playerInTurn.getPosition());
            System.out.println("==============================================");

        }
        while (getGameStatus()!=2);

        System.out.println("the  winner is:"+ playerInTurn.getName());
        System.out.println("Final positions are:");

        System.out.println(players.get(0).getName() + ": " + players.get(0).getPosition());
        System.out.println(players.get(1).getName() + ": " + players.get(1).getPosition());

    }

    //Extracted from the code above to make it reusable
    //=======================================================================================
    public void playOneRoll(Player playerInTurn){
        System.out.println(playerInTurn.getName() + " please press enter to roll the dice");
        Scanner sc = new Scanner(System.in);
        String enter = sc.nextLine();
        int x = 0;
        if (enter.isEmpty()) {
            x = playerInTurn.rollDice();
        }
        System.out.println("Dice Number : "+ x);
        movePlayerAround(playerInTurn, x);
        if (x == 6){
            System.out.println(playerInTurn.getName() + " get to roll once more");
            playOneRoll(playerInTurn);
        }
    }
    //=======================================================================================

    public void addPlayer(Player s){
        this.players.add(s);
    }
    public ArrayList<Player> getPlayers(Player s){
        return this.players;
    }
    public void addSnake(Snake s){
        this.snakes.add(s);
    }

    public void addSnakes(int [][] s){
        for (int r = 0; r < s.length; r++){
            Snake snake = new Snake(s[r][0], s[r][1]);
            this.snakes.add(snake);
        }
    }


    public void addLadder(Ladder l){
        this.ladders.add(l);
    }

    public void addLadders(int [][] l){
        for (int r = 0; r < l.length; r++){
            Ladder ladder = new Ladder(l[r][1], l[r][0]);
            this.ladders.add(ladder);
        }

    }

    //=======================================================================================
    public void addQuestions(int [] q){
        for (int r = 0; r < q.length; r++){
            Question question = new Question(q[r]);
            this.questions.add(question);
        }

    }
    //=======================================================================================

    public int getBoardSize(){
        return this.boardSize;
    }
    public ArrayList<Snake> getSnakes(){
        return this.snakes;
    }
    public ArrayList<Ladder> getLadders(){
        return this.ladders;
    }
    public void initiateGame(){
        int [][] l = {
                {2,23},
                {8,34},
                {20,77},
                {32,68},
                {41,79},
                {74,88},
                {82,100},
                {85,95}

        };
        addLadders(l);

        int[][] s = {
                {5, 47},
                {9, 29},
                {15, 38},
                {25, 97},
                {33, 53},
                {37, 62},
                {54, 86},
                {70, 92}
        };

        addSnakes(s);

        //=======================================================================================
        int[] q = {
                14,
                30,
                42,
                37,
                73,
        };
        addQuestions(q);
        //=======================================================================================
    }

    public void movePlayerAround(Player p, int x){
        p.moveAround(x, this.boardSize);
        for(Ladder l:this.ladders){
            if(p.getPosition()== l.getBottomPosition()) {
                System.out.println(p.getName() + " you got Ladder from: " + l.getBottomPosition() + " To: " + l.getTopPosition());
                p.setPosition(l.getTopPosition());
            }
        }
        for(Snake s:this.snakes){
            if(p.getPosition()== s.getHeadPosition()){
                p.setPosition(s.getTailPosition());
                System.out.println(p.getName()+" you get snake head from "+ s.getHeadPosition() + " slide down to " + s.getTailPosition());
            }
        }
        //=======================================================================================
        for(Question q:this.questions){
            if(p.getPosition()== q.getPosition()){
                System.out.print(p.getName());
                if (q.AskQuestion()){
                    playOneRoll(p);
                }
                else System.out.println("Wrong Answer");
            }
        }
        //=======================================================================================
        if(p.getPosition()==this.boardSize){
            this.gameStatus=2;
        }


    }
    public Player getWhoseTurn(){

        if(this.gameStatus==0){
            this.gameStatus=1;
            System.out.println("Who will be the first to play?\nYou can enter the name of the player or \"random\" to randomize the player who start.");
            double r = -1;
            do {
                Scanner sc = new Scanner(System.in);
                String first = sc.next();
                if (first.equals("random")) {
                    r = Math.random();
                }

                if (first.equals(players.get(0).getName())) r = 0;
                if (first.equals(players.get(1).getName())) r = 1;

                if (r == -1){
                    System.out.println("Please enter one of the name you gave above or \"random\".");
                }
            } while (r == -1);
            if(r<=0.5){
                this.nowPlaying = 0;
                return this.players.get(0);
            }
            else {
                this.nowPlaying = 1;
                return this.players.get(1);
            }
        }
        else{
            if(this.nowPlaying == 0){
                this.nowPlaying = 1;
                return this.players.get(1);
            }

            else {
                this.nowPlaying = 0;
                return this.players.get(0);

            }
        }
    }
}