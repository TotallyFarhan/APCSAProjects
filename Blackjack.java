import java.util.Scanner;

public class Blackjack extends Thread {
   private int total;
   private int compTotal;
   private int card;
   private String choice;
   private String cards;
   private String computerChoice;
   private Scanner s;
   private boolean isGameOver;
   private String winner;
   private boolean isPlayerTurn;
   private boolean isComputerTurn;
   private boolean playerBust;
   
   public static void main(String[] args) {
      Blackjack b1 = new Blackjack();
      while (!b1.isGameOver && b1.isPlayerTurn && !b1.playerBust) {
         b1.input();
         b1.hitOrStand("player");
         b1.checkBust("player");
      }
      while (b1.isComputerTurn) {
         b1.computerInput();
         b1.hitOrStand("computer");
         try {
            Thread.sleep(1000);
         }
         catch (InterruptedException e) {
            System.out.println(e);
         }
         b1.checkBust("computer");
      }
      if (b1.isGameOver) {
         b1.checkWin();
         System.out.println(b1.winner + " wins!");
      }
   }
   
   public Blackjack() {
      total = 0;
      compTotal = 0;
      choice = "";
      computerChoice = "";
      cards = "";
      s = new Scanner(System.in);
      winner = "";
      isPlayerTurn = true;
      isComputerTurn = false;
      playerBust = false;
      startGame();
   }
   
   public void startGame() {
      drawCard("player");
      drawCard("player");
   }
   
   public void drawCard(String who) {
      card = (int)(Math.random() * 13) + 1;
      if (card == 11) {
         System.out.println("Card: J");
         card = 10;
         cards += " J";
      }
      else if (card == 12) {
         System.out.println("Card: Q");
         card = 10;
         cards += " Q";
      }
      else if (card == 13) {
         System.out.println("Card: K");
         card = 10;
         cards += " K";
      }
      else if (card == 1) {
         System.out.println("Card: A");
         card = 1;
         cards += " A";
      }
      else {
         System.out.println("Card: " + card);
         cards += " ";
         cards += card;
      }
      
      if (who.equals("player")) {
         total += card;
         System.out.println("Total: " + total);
         System.out.println("\n");
      }
      else {
         compTotal += card;
         System.out.println("Computer Total: " + compTotal);
         System.out.println("\n");
      }
   }
   
   public void input() {
      while (!isGameOver && choice.equals("")) {
         System.out.println("Hit or Stand?");
         choice = s.nextLine();
         
         if (!choice.toLowerCase().equals("hit") && !choice.toLowerCase().equals("stand")) {
            choice = "";
            System.out.println("u trippin dawg");
         }
      }
   }
   
   public void computerInput() {
      double hitStand = Math.random();
      double hitChance = 0.0;
      if (compTotal >= 0) {
         hitChance = 0.9;
      }
      if (compTotal > 7) {
         hitChance  = 0.8;
      }
      if (compTotal > 12) {
         hitChance = 0.7;
      }
      if (compTotal > 17) {
         hitChance = 0.3;
      }
      if (compTotal >= 19) {
         hitChance = 0.1;
      }
      if (compTotal == 21) {
         hitChance = 0.0;
      }
            
      if (hitStand < hitChance) {
         computerChoice = "hit";
      }
      else {
         computerChoice = "stand";
      }
   }
   
   public void hitOrStand(String who) {
      if (who.equals("player")) {
         if(choice.toLowerCase().equals("hit")) {
            drawCard("player");
            choice = "";
         }
         else {
            isPlayerTurn = false;
            isComputerTurn = true;
            choice = "";
         }
      }
      else {
         if (computerChoice.toLowerCase().equals("hit")) {
            drawCard("computer");
            computerChoice = "";
         }
         else {
            isComputerTurn = false;
            isGameOver = true;
            computerChoice = "";
         }
      }
   }
   
   public void checkBust(String who) {
      if (who.equals("player")) {
         if (total > 21) {
            playerBust = true;
            System.out.println("\nBust! Computer Wins");
         }
      }
      else {
         if (compTotal > 21) {
            isComputerTurn = false;
            System.out.println("\nBust! You Win");
         }
      }
   }
   
   public void checkWin() {
      if (isGameOver) {
         if (total > compTotal) {
            winner = "Player";
         }
         else if (total < compTotal) {
            winner = "Computer";
         }
         else if (total == compTotal) {
            winner = "Tie";
         }
      }
   }
   
}