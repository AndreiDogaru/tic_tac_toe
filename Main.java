package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    // Create a GridPane Layout
    final GridPane grid = new GridPane();

    // Create Buttons
    final Button b1 = new Button();
    final Button b2 = new Button();
    final Button b3 = new Button();
    final Button b4 = new Button();
    final Button b5 = new Button();
    final Button b6 = new Button();
    final Button b7 = new Button();
    final Button b8 = new Button();
    final Button b9 = new Button();

    final Button resetButton = new Button();
    final Button newGameButton = new Button();

    // Create labels
    final Label scoreLabel = new Label();
    final Label myName = new Label();

    // Integer to see which player's turn is it
    int turnCount = 1;

    // Integer to see how many moves were in the turn
    int numMoves = 0;

    // Strings and integers for players name and score
    String player1 = "";
    String player2 = "";
    int p1score = 0;
    int p2score = 0;

    // String to show the players name and score on the scoreLabel
    String labelContent = "";

    @Override
    public void start(final Stage primaryStage) throws Exception{

        // Set GridPane, Buttons and Label ( alignment, size, etc.)
        setGame();

        // Start game
        startGame();

        // Create a Scene
        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        scene.getStylesheets().add
                (Main.class.getResource("tictactoe.css").toExternalForm());
        primaryStage.setTitle("TicTacToe");
        primaryStage.show();

    }

    public void setGame(){

        // GridPane
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));

        // Action buttons
        b1.setPrefSize(70,70);
        b2.setPrefSize(70,70);
        b3.setPrefSize(70,70);
        b4.setPrefSize(70,70);
        b5.setPrefSize(70,70);
        b6.setPrefSize(70,70);
        b7.setPrefSize(70,70);
        b8.setPrefSize(70,70);
        b9.setPrefSize(70,70);

        // Horizontal box for first row of buttons
        HBox hb1 = new HBox(1);
        hb1.setAlignment(Pos.TOP_LEFT);
        hb1.getChildren().add(b1);
        hb1.getChildren().add(b2);
        hb1.getChildren().add(b3);

        // Horizontal box for second row of buttons
        HBox hb2 = new HBox(1);
        hb2.setAlignment(Pos.CENTER_LEFT);
        hb2.getChildren().add(b4);
        hb2.getChildren().add(b5);
        hb2.getChildren().add(b6);

        // Horizontal box for third row of buttons
        HBox hb3 = new HBox(1);
        hb3.setAlignment(Pos.BOTTOM_LEFT);
        hb3.getChildren().add(b7);
        hb3.getChildren().add(b8);
        hb3.getChildren().add(b9);

        // Reset table and new game buttons
        resetButton.setPrefSize(150,50);
        newGameButton.setPrefSize(150,50);
        resetButton.setText("Reset table");
        resetButton.setFont(new Font("Comic Sans MS",15));
        newGameButton.setText("New game");
        newGameButton.setFont(new Font("Comic Sans MS",15));

        // Horizontal box for reset and new game buttons
        HBox hb4 = new HBox(70);
        hb4.setAlignment(Pos.BASELINE_CENTER);
        hb4.getChildren().add(resetButton);
        hb4.getChildren().add(newGameButton);

        // Vertical box for the 9 buttons we use to play the game
        VBox vb = new VBox(1);
        vb.setAlignment(Pos.TOP_CENTER);
        vb.getChildren().add(hb1);
        vb.getChildren().add(hb2);
        vb.getChildren().add(hb3);

        // Vertical box for every button in the gridPane
        VBox vb1 = new VBox(150);
        vb1.setAlignment(Pos.TOP_LEFT);
        vb1.getChildren().add(vb);
        vb1.getChildren().add(hb4);
        grid.add(vb1, 5, 0);

        // Label
        scoreLabel.setPrefSize(380,160);
        scoreLabel.setFont(new Font("Comic Sans MS", 22));
        scoreLabel.setText("Press the \"New game\" button\n to start a new game.\n\n\n \tHave fun !");
        myName.setPrefSize(300,50);
        myName.setFont(new Font("Comic Sans MS", 15));
        myName.setText("Created by Andrei Dogaru");

        // Horizontal box for the first label
        HBox hbLabel1 = new HBox();
        hbLabel1.setAlignment(Pos.CENTER_RIGHT);
        hbLabel1.getChildren().add(scoreLabel);

        // Horizontal box for the second label
        HBox hbLabel2 = new HBox();
        hbLabel2.setAlignment(Pos.BASELINE_RIGHT);
        hbLabel2.getChildren().add(myName);

        // Vertical box for every label in the gridPane
        VBox vb2 = new VBox(250);
        vb2.setAlignment(Pos.TOP_RIGHT);
        vb2.getChildren().add(hbLabel1);
        vb2.getChildren().add(hbLabel2);
        grid.add(vb2,15,0);

    }

    public void startGame(){

        newGameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // set score to 0-0
                p1score = 0;
                p2score = 0;

                // set every button's text field to ""
                resetTable();

                // enter the name of the players
                enterPlayersName();

                // make the scoreLabel show the players name and score
                updateScoringLabel();

                // make player X start every game
                turnCount = 1;

                // start the actual game
                pressButton();
            }
        });
    }

    public void enterPlayersName(){

        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Welcome");
        dialog.setHeaderText("Please enter your name below: ");
        dialog.setContentText("Player X: ");

        dialog.showAndWait();
        player1 = dialog.getResult();

        dialog.getEditor().setText("");
        dialog.setTitle("Welcome");
        dialog.setHeaderText("Please enter your name below: ");
        dialog.setContentText("Player O: ");

        dialog.showAndWait();
        player2 = dialog.getResult();

    }

    public void updateScoringLabel(){

        labelContent = player1+" "+p1score+"-"+p2score+" "+player2;
        scoreLabel.setText(labelContent);
    }

    public void pressButton(){

        b1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(b1.getText().equals("") && turnCount == 1){
                    b1.setText("X");
                    turnCount = 2;
                    numMoves ++;
                }else if(b1.getText().equals("") && turnCount == 2){
                    b1.setText("O");
                    turnCount = 1;
                    numMoves ++;
                }
                checkWinner();

            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(b2.getText().equals("") && turnCount == 1){
                    b2.setText("X");
                    turnCount = 2;
                    numMoves ++;
                }else if(b2.getText().equals("") && turnCount == 2){
                    b2.setText("O");
                    turnCount = 1;
                    numMoves ++;
                }
                checkWinner();

            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(b3.getText().equals("") && turnCount == 1){
                    b3.setText("X");
                    turnCount = 2;
                    numMoves ++;
                }else if(b3.getText().equals("") && turnCount == 2){
                    b3.setText("O");
                    turnCount = 1;
                    numMoves ++;
                }
                checkWinner();

            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(b4.getText().equals("") && turnCount == 1){
                    b4.setText("X");
                    turnCount = 2;
                    numMoves ++;
                }else if(b4.getText().equals("") && turnCount == 2){
                    b4.setText("O");
                    turnCount = 1;
                    numMoves ++;
                }
                checkWinner();

            }
        });
        b5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(b5.getText().equals("") && turnCount == 1){
                    b5.setText("X");
                    turnCount = 2;
                    numMoves ++;
                }else if(b5.getText().equals("") && turnCount == 2){
                    b5.setText("O");
                    turnCount = 1;
                    numMoves ++;
                }
                checkWinner();

            }
        });
        b6.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(b6.getText().equals("") && turnCount == 1){
                    b6.setText("X");
                    turnCount = 2;
                    numMoves ++;
                }else if(b6.getText().equals("") && turnCount == 2){
                    b6.setText("O");
                    turnCount = 1;
                    numMoves ++;
                }
                checkWinner();

            }
        });
        b7.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(b7.getText().equals("") && turnCount == 1){
                    b7.setText("X");
                    turnCount = 2;
                    numMoves ++;
                }else if(b7.getText().equals("") && turnCount == 2){
                    b7.setText("O");
                    turnCount = 1;
                    numMoves ++;
                }
                checkWinner();

            }
        });
        b8.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(b8.getText().equals("") && turnCount == 1){
                    b8.setText("X");
                    turnCount = 2;
                    numMoves ++;
                }else if(b8.getText().equals("") && turnCount == 2){
                    b8.setText("O");
                    turnCount = 1;
                    numMoves ++;
                }
                checkWinner();

            }
        });
        b9.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(b9.getText().equals("") && turnCount == 1){
                    b9.setText("X");
                    turnCount = 2;
                    numMoves ++;
                }else if(b9.getText().equals("") && turnCount == 2){
                    b9.setText("O");
                    turnCount = 1;
                    numMoves ++;
                }
                checkWinner();

            }
        });
        resetButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                // We give the turn to the same player that started
                if(turnCount == 1 && numMoves % 2 != 0)
                    turnCount = 2;
                else if(turnCount == 2 && numMoves % 2 != 0)
                    turnCount = 1;

                resetTable();
            }
        });
    }

    public void checkWinner(){
        // Boolean value to check if the game ends up as a tie
        boolean checkEven = true;
        if(b1.getText().equals(b2.getText()) && b1.getText().equals(b3.getText()) && !b1.getText().equals("")){
            // if it enters one of the if statements, this means that the game is not even
            checkEven = false;
            // check which player won
            if(turnCount == 1)
                p2score ++;
            else
                p1score ++;
            // update the scoring label
            updateScoringLabel();
            // if the second player to make a move wins, then we had a little error:
            //    -> next turn after this one, the same player would have started,
            //        which is wrong cause we need to alternate the starting player each turn
            if(numMoves % 2 == 0 && turnCount == 1)
                turnCount = 2;
            else if(numMoves % 2 == 0 && turnCount == 2)
                turnCount = 1;
            // finally we reset the button values to default
            resetTable();
        }
        if(b4.getText().equals(b5.getText()) && b4.getText().equals(b6.getText()) && !b4.getText().equals("")){
            checkEven = false;
            if(turnCount == 1)
                p2score ++;
            else
                p1score ++;
            updateScoringLabel();
            if(numMoves % 2 == 0 && turnCount == 1)
                turnCount = 2;
            else if(numMoves % 2 == 0 && turnCount == 2)
                turnCount = 1;
            resetTable();
        }
        if(b7.getText().equals(b8.getText()) && b7.getText().equals(b9.getText()) && !b7.getText().equals("")){
            checkEven = false;
            if(turnCount == 1)
                p2score ++;
            else
                p1score ++;
            updateScoringLabel();
            if(numMoves % 2 == 0 && turnCount == 1)
                turnCount = 2;
            else if(numMoves % 2 == 0 && turnCount == 2)
                turnCount = 1;
            resetTable();
        }
        if(b1.getText().equals(b4.getText()) && b1.getText().equals(b7.getText()) && !b1.getText().equals("")){
            checkEven = false;
            if(turnCount == 1)
                p2score ++;
            else
                p1score ++;
            updateScoringLabel();
            if(numMoves % 2 == 0 && turnCount == 1)
                turnCount = 2;
            else if(numMoves % 2 == 0 && turnCount == 2)
                turnCount = 1;
            resetTable();
        }
        if(b2.getText().equals(b5.getText()) && b2.getText().equals(b8.getText()) && !b2.getText().equals("")){
            checkEven = false;
            if(turnCount == 1)
                p2score ++;
            else
                p1score ++;
            updateScoringLabel();
            if(numMoves % 2 == 0 && turnCount == 1)
                turnCount = 2;
            else if(numMoves % 2 == 0 && turnCount == 2)
                turnCount = 1;
            resetTable();
        }
        if(b3.getText().equals(b6.getText()) && b3.getText().equals(b9.getText()) && !b3.getText().equals("")){
            checkEven = false;
            if(turnCount == 1)
                p2score ++;
            else
                p1score ++;
            updateScoringLabel();
            if(numMoves % 2 == 0 && turnCount == 1)
                turnCount = 2;
            else if(numMoves % 2 == 0 && turnCount == 2)
                turnCount = 1;
            resetTable();
        }
        if(b1.getText().equals(b5.getText()) && b1.getText().equals(b9.getText()) && !b1.getText().equals("")){
            checkEven = false;
            if(turnCount == 1)
                p2score ++;
            else
                p1score ++;
            updateScoringLabel();
            if(numMoves % 2 == 0 && turnCount == 1)
                turnCount = 2;
            else if(numMoves % 2 == 0 && turnCount == 2)
                turnCount = 1;
            resetTable();
        }
        if(b3.getText().equals(b5.getText()) && b3.getText().equals(b7.getText()) && !b3.getText().equals("")){
            checkEven = false;
            if(turnCount == 1)
                p2score ++;
            else
                p1score ++;
            updateScoringLabel();
            if(numMoves % 2 == 0 && turnCount == 1)
                turnCount = 2;
            else if(numMoves % 2 == 0 && turnCount == 2)
                turnCount = 1;
            resetTable();
        }
        if(checkEven == true  && !b1.getText().equals("")  && !b2.getText().equals("")  && !b3.getText().equals("")
                && !b4.getText().equals("")  && !b5.getText().equals("")  && !b6.getText().equals("")
                && !b7.getText().equals("")  && !b8.getText().equals("")  && !b9.getText().equals("")){
            // if the game is even we prompt a message and reset the table again
            evenGame();
            resetTable();
        }
    }

    public void resetTable(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
        b9.setText("");
        numMoves = 0;
    }

    public void evenGame(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("It's all even !");
        alert.setContentText("Press Ok to reset table and continue.");

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
