package com.example.demosnakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {

    ArrayList<Pair<Integer,Integer>> PositionCoodinates;

    ArrayList<Integer> SnakeLadderPosition;


    public Board(){

        PositionCoodinates = new ArrayList<>();

        PopulatePostionCoordinates();
        populateSnakeLadder();


    }

    private void PopulatePostionCoordinates(){
        PositionCoodinates.add(new Pair<>(0,0));// x,y coordinates dummy
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                //generate x coordinates
                int Xcord =0;
                if(i%2==0){
                  Xcord = (j*SnakeLadder.tileSize)+SnakeLadder.tileSize/2;
                }
                else{
                    Xcord = (SnakeLadder.tileSize*SnakeLadder.width) - (j*SnakeLadder.tileSize)-SnakeLadder.tileSize/2;
                }
                // generate y coordinates


                int Ycord = (SnakeLadder.tileSize*SnakeLadder.height)-(i*SnakeLadder.tileSize)-SnakeLadder.tileSize/2;

                PositionCoodinates.add(new Pair<>(Xcord,Ycord));

            }

        }
    }

    private void populateSnakeLadder(){

        SnakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            SnakeLadderPosition.add(i);
        }


        // map ladders and snakes

        SnakeLadderPosition.set(4,25);//ladder
        SnakeLadderPosition.set(13,46);
        SnakeLadderPosition.set(27,5); //snake
        SnakeLadderPosition.set(33,49);
        SnakeLadderPosition.set(40,3);
        SnakeLadderPosition.set(42,63);
        SnakeLadderPosition.set(43,18);
        SnakeLadderPosition.set(50,69);
        SnakeLadderPosition.set(54,31);
        SnakeLadderPosition.set(62,81);
        SnakeLadderPosition.set(66,45);
        SnakeLadderPosition.set(74,92);
        SnakeLadderPosition.set(76,58);
        SnakeLadderPosition.set(89,53);
        SnakeLadderPosition.set(99,41);


    }
  public int getNewPostion(int currentposition){


        if(currentposition>0 && currentposition<=100){

            return SnakeLadderPosition.get(currentposition);
        }
        return -1;
  }
    int getXCoordinate(int position){

        if(position>=1 && position<=100){
            return PositionCoodinates.get(position).getKey();
        }
        return -1;
    }

    int  getYCoordinate(int position){
        if(position>=1 && position<=100){
            return PositionCoodinates.get(position).getValue();
        }
        return -1;
    }

//    public static void main(String[] args) {
//
//        Board board = new Board();
//
//        for (int i = 0; i < board.PositionCoodinates.size(); i++) {
//            System.out.println(i+"$ X :"+board.PositionCoodinates.get(i).getKey()+
//                    " Y "+ board.PositionCoodinates.get(i).getValue());
//
//        }
//    }

}
