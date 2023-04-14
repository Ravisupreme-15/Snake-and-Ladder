package com.example.demosnakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {

   //declaring the coin

 private Circle coin;
 //declare the posion of the coin
 private int currentpostion;

//name of the plyer
 private String name;

private static Board gameBoard = new Board();


 public Player(int tileSize, Color coinColor,String PlyerName){

     coin = new Circle(tileSize/2);
     coin.setFill(coinColor);
     name = PlyerName;
     currentpostion =0;

     movePlayer(1);

 }

 public void movePlayer(int diceValue){

    if(currentpostion+diceValue<=100){
        currentpostion+=diceValue;
        TranslateTransition firstmove = translateAnimation(diceValue), secondmove = null;
       // translateAnimation(diceValue);


       int newPosition = gameBoard.getNewPostion(currentpostion);

         if(newPosition != currentpostion && newPosition!=-1){
             currentpostion = newPosition;
             secondmove =translateAnimation(6);
         }
         if(secondmove ==null){
             firstmove.play();
         }
         else{
             SequentialTransition sequentialTransition = new SequentialTransition(firstmove,new PauseTransition(Duration.millis(500)),
                     secondmove);
             sequentialTransition.play();
         }

//        int X = gameBoard.getXCoordinate(currenpostion);
//        int Y = gameBoard.getYCoordinate(currenpostion);
//
//        coin.setTranslateX(X);
//        coin.setTranslateY(Y);
    }
 }


 private  TranslateTransition  translateAnimation(int diceValue){

     TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200*diceValue),coin);

     translateTransition.setToX(gameBoard.getXCoordinate(currentpostion));
     translateTransition.setToY(gameBoard.getYCoordinate(currentpostion));
     translateTransition.setAutoReverse(false);
     return translateTransition;

 }


 // bring players to starting position
    public   void startingposition(){
     currentpostion=0;
     movePlayer(1);

    }


  boolean winnerOne(){

     if(currentpostion==100){
         return true;
     }
     return false;
  }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrenpostion() {
        return currentpostion;
    }

    public String getName() {
        return name;
    }
}
