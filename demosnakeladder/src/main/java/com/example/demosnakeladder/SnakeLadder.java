package com.example.demosnakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

     public static final int tileSize = 40, width=10, height=10;

     public  static final int buttonLine = height*tileSize+40, infoLine = buttonLine-20;

     private  static Dice dice = new Dice();

     private Player playerOne, playerTwo;


     private  boolean gamestarted = false, playerOneTurn =false, playerTwoTurn = false;
             //resetTurn=false;

    private Pane CreateContent(){

        Pane root = new Pane();

        root.setPrefSize(width*tileSize, height*tileSize+80);

        // create a tile /block


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }

        }

        Image image = new Image("C:\\Users\\Chinnu\\IdeaProjects\\demosnakeladder\\src\\main\\snakeladder.jpg");

        ImageView board = new ImageView();
        board.setImage(image);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);


        // create buttons
        Button playerOnebutton = new Button("player One");
        Button playerTwobutton = new Button("player Two");
        Button startButton     = new Button("Start");


       // Button resetbutton     = new Button("reset");

        playerOnebutton.setTranslateY(buttonLine);
        playerOnebutton.setTranslateX(25);
        playerOnebutton.setDisable(true);
        playerTwobutton.setTranslateY(buttonLine);
        playerTwobutton.setTranslateX(295);
        playerTwobutton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(180);



//        resetbutton.setTranslateX(buttonLine);
//        resetbutton.setTranslateY(220);
//        resetbutton.setDisable(true);





        // info/label display
        Label playerOnelabel = new Label("");
        Label playerTwolabel = new Label("");
        Label dicelabel = new Label("Start Game!");

        playerOnelabel.setTranslateY(infoLine);
        playerOnelabel.setTranslateX(25);
        playerTwolabel.setTranslateY(infoLine);
        playerTwolabel.setTranslateX(295);
        dicelabel.setTranslateY(infoLine);
        dicelabel.setTranslateX(170);


        playerOne  = new Player(tileSize, Color.WHITE,"Ravi");
        playerTwo  = new Player(tileSize-5,Color.BLACK,"Venky");


        // playerAction
        playerOnebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(gamestarted){
                    if(playerOneTurn){

                        int diceValue = dice.getDiceValue();
                        dicelabel.setText("DiceValue: "+ diceValue);
                        playerOne.movePlayer(diceValue);

                        if(playerOne.winnerOne()){

                            dicelabel.setText(playerOne.getName()+" is Winner");



                            playerOneTurn = false;
                            playerOnebutton.setDisable(true);
                            playerOnelabel.setText("");


                            playerTwoTurn = false;
                            playerTwobutton.setDisable(true);
                            playerTwolabel.setText("");



                            startButton.setDisable(false);
                            gamestarted = false;


                        }
                        else{

                            playerOneTurn = false;
                            playerOnebutton.setDisable(true);
                            playerOnelabel.setText("");

                            playerTwoTurn = true;
                            playerTwobutton.setDisable(false);
                            playerTwolabel.setText("Your Turn:"+playerTwo.getName());

                        }
                 }
                }
            }
        });


        // playertwo action

        playerTwobutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(gamestarted){
                    if(playerTwoTurn){

                        int diceValue = dice.getDiceValue();
                        dicelabel.setText("Dice Value"+diceValue);
                        playerTwo.movePlayer(diceValue);

                        // winning condition

                        if(playerTwo.winnerOne()){

                            dicelabel.setText(playerTwo.getName()+" is Winner");

                            startButton.setDisable(false);
                            gamestarted = true;

                            playerOneTurn = false;
                            playerOnebutton.setDisable(true);
                            playerOnelabel.setText("");


                            playerTwoTurn = false;
                            playerTwobutton.setDisable(true);
                            playerTwolabel.setText("");




                        }else{


                            playerTwoTurn = false;
                            playerTwobutton.setDisable(true);
                            playerTwolabel.setText("");


                            playerOneTurn = true;
                            playerOnebutton.setDisable(false);
                            playerOnelabel.setText("Your Turn:"+playerOne.getName());

                        }


                    }
                }
            }
        });





    // start action
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                gamestarted = true;
                dicelabel.setText("GameStarted");
                startButton.setDisable(true);

//                resetTurn = true;
//                resetbutton.setDisable(false);

                playerOneTurn =true;
                playerOnebutton.setDisable(false);
                playerOnelabel.setText("Your Turn: "+ playerOne.getName());
                playerOne.startingposition();

                playerTwoTurn = false;
                playerTwolabel.setText("");
                playerTwobutton.setDisable(true);
                playerTwo.startingposition();
            }
        });


        // resetAction
//        resetbutton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//
//              if(resetTurn)
//              {
//                    playerOne.startingposition();
//                    playerTwo.startingposition();
//
//                    startButton.setDisable(false);
//                    dicelabel.setText("Start Game!");
//
//
//                    resetTurn = false;
//                    resetbutton.setDisable(true);
//
//                }
//            }
//        });





        root.getChildren().addAll(board,playerOnebutton,playerTwobutton,startButton,playerOnelabel,playerTwolabel,
                dicelabel,playerOne.getCoin(),playerTwo.getCoin()

        );

     return root;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(CreateContent());
        stage.setTitle("Sanake And Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}