import java.util.Random;
import java.util.Scanner;

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

public class Question{
    private int Position;
    private Random r = new Random();
    private Scanner sc = new Scanner(System.in);

    public Question(int p){
        this.Position = p;
    }

    public void setPosition(int p){
        this.Position = p;
    }

    public int getPosition() {
        return this.Position ;
    }

    public boolean AskQuestion() {
        int a = r.nextInt(10);
        int b = r.nextInt(10);

        System.out.println(" you got a question, please answer the following question: " + a + "*" + b);
        int n = sc.nextInt();

        return a*b == n;
    }
}