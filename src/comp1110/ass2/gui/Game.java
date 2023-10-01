package comp1110.ass2.gui;

import comp1110.ass2.Marrakech;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.List;

public class Game extends Application {

    // The width and height of the window
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    /*
     Distance to leave from a button to the right - used for setting up
     all the buttons at the bottom of the window.
     */
    private static final double BUTTON_BUFFER = 200.0;

    // The width of the board (left to right)
    public final static int BOARD_WIDTH = 7;

    // The height of the board (top to bottom)
    public final static int BOARD_HEIGHT = 7;

    // Height and width of each tile
    private static final double Tile_Size = 50;

    // Pixel gap between the grey rectangles that indicates where the tiles are on the board
    private static final double BOARD_TILE_SHADOW_GAP = 0.5;

    // how much the blue background extends past the tiles
    private static final int BOARD_BORDER = 10;

    // total width and height of the board
    private static final double boardWidth = BOARD_WIDTH*Tile_Size;
    private static final double boardHeight = BOARD_HEIGHT*Tile_Size;

    // The start of the board in the x-direction (ie: x = 0)
    private static final double START_X = (double) WINDOW_WIDTH / 2 - boardWidth / 2;

    // The start of the board in the y-direction (ie: y = 0)
    private static final double START_Y = (double) WINDOW_HEIGHT / 2 - boardHeight / 2 - 30.0;

    /*
    all the groups for scene
     */
    private static final Group root = new Group();
    private static final Group board = new Group();
    private static final Group mosaicTrack = new Group();
    private static final Group rugs = new Group();
    private static final Group die = new Group();
    private static final Group controls = new Group();

    /**
     * @author u7754892 Yaohui Hou
     * @author u7754637 Pei Ling Lam (supporting author)
     */
    class GameInterface{
        public GameInterface(Stage stage){
            Pane root = new Pane();
            makeMosaicTrack();
            makeBoard();
            makeDie();
            makeControls();
            stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
            root.getChildren().add(mosaicTrack);
            root.getChildren().add(board);
            root.getChildren().add(die);
            root.getChildren().add(controls);
            stage.show();
        }
    }


    /**
     * @author u7754892 Yaohui Hou
     */
    private void makeBoard() {
        // creating rectangle to represent the blue background of the board
        Rectangle boardBack = new Rectangle(
                START_X-BOARD_BORDER+(BOARD_TILE_SHADOW_GAP / 2),
                START_Y-BOARD_BORDER+(BOARD_TILE_SHADOW_GAP / 2),
                boardWidth+(2*BOARD_BORDER)- BOARD_TILE_SHADOW_GAP,
                boardHeight+(2*BOARD_BORDER)- BOARD_TILE_SHADOW_GAP);
        boardBack.setFill(Color.web("e4d5b7"));
        boardBack.setArcHeight(30.0d);
        boardBack.setArcWidth(30.0d);
        // adding the rectangle to the board group
        board.getChildren().add(boardBack);

        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                Rectangle tileShadow = new Rectangle(
                        START_X + (x * Tile_Size) + (BOARD_TILE_SHADOW_GAP / 2),
                        START_Y + (y * Tile_Size) + (BOARD_TILE_SHADOW_GAP / 2),
                        Tile_Size - BOARD_TILE_SHADOW_GAP,
                        Tile_Size - BOARD_TILE_SHADOW_GAP);
                tileShadow.setFill(Color.TRANSPARENT);
                tileShadow.setStrokeWidth(2);
                tileShadow.setStroke(Color.GREY);
                tileShadow.setOpacity(0.5);
                board.getChildren().add(tileShadow);
            }
        }
    }

    /**
     * creating the mosaic track around the board to handle if Assam moved out of the board
     * @author u7754892 Yaohui Hou
     */
    private void makeMosaicTrack() {
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                if (x % 2 == 0 && y == 0) {
                    Circle mosaic = new Circle(START_X + BOARD_BORDER + BOARD_TILE_SHADOW_GAP + (x * Tile_Size) + Tile_Size / 1.5,
                            START_Y - BOARD_BORDER * 2 - BOARD_TILE_SHADOW_GAP * 2 + (y * Tile_Size) + Tile_Size / 2, Tile_Size - BOARD_BORDER);
                    mosaic.setFill(Color.web("FFBF00"));
                    mosaicTrack.getChildren().add(mosaic);
                }else if (x % 2 == 0 && y == 6) {
                    Circle mosaic = new Circle(START_X + BOARD_BORDER + BOARD_TILE_SHADOW_GAP + (x * Tile_Size) - Tile_Size / 4,
                            START_Y + BOARD_BORDER * 2 + BOARD_TILE_SHADOW_GAP * 2 + (y * Tile_Size) + Tile_Size / 2, Tile_Size - BOARD_BORDER);
                    mosaic.setFill(Color.web("FFBF00"));
                    mosaicTrack.getChildren().add(mosaic);
                } else if (y % 2 != 0 && x == 0) {
                    Circle mosaic = new Circle(START_X + BOARD_BORDER + BOARD_TILE_SHADOW_GAP + (x * Tile_Size) - Tile_Size / 4,
                            START_Y - BOARD_BORDER * 2 - BOARD_TILE_SHADOW_GAP * 2 + (y * Tile_Size) + Tile_Size / 2, Tile_Size - BOARD_BORDER);
                    mosaic.setFill(Color.web("FFBF00"));
                    mosaicTrack.getChildren().add(mosaic);
                } else if (y % 2 != 0 && x == 6) {
                    Circle mosaic = new Circle(START_X + BOARD_BORDER + BOARD_TILE_SHADOW_GAP + (x * Tile_Size) + Tile_Size / 1.5,
                            START_Y + BOARD_BORDER * 2 + BOARD_TILE_SHADOW_GAP * 2 + (y * Tile_Size) + Tile_Size / 2, Tile_Size - BOARD_BORDER);
                    mosaic.setFill(Color.web("FFBF00"));
                    mosaicTrack.getChildren().add(mosaic);
                }
            }
        }
    }

    /**
     * This method draws the die everytime a die is rolled
     * @param n number of dot on the face
     *
     * @author u7754637 Pei Ling Lam
     */
    private void createDie(int n) {
        // create square of the die
        Rectangle face = new Rectangle(
                WINDOW_WIDTH - 300,
                START_Y,
                Tile_Size/2,
                Tile_Size/2);
        face.setFill(Color.GOLD);
        die.getChildren().add(face);


        // draw the dot to represent faces of the die
        Circle dot1 = new Circle();
        Circle dot2 = new Circle();
        Circle dot3 = new Circle();
        Circle dot4 = new Circle();

        switch (n) {
            // create one dot
            case 1:
                dot1.setCenterX(WINDOW_WIDTH - 300 + Tile_Size / 4);
                dot1.setCenterY(START_Y + Tile_Size / 4);
                dot1.setRadius(2);

                dot1.setFill(Color.BROWN);

                die.getChildren().add(dot1);
                break;

            // create two dots
            case 2:
                dot1.setCenterX(WINDOW_WIDTH - 300 + Tile_Size / 4);
                dot1.setCenterY(START_Y + Tile_Size / 6);
                dot1.setRadius(2);

                dot2.setCenterX(WINDOW_WIDTH - 300 + Tile_Size / 4);
                dot2.setCenterY(START_Y + Tile_Size / 3);
                dot2.setRadius(2);

                dot1.setFill(Color.BROWN);
                dot2.setFill(Color.BROWN);

                die.getChildren().add(dot1);
                die.getChildren().add(dot2);

                break;

            // create three dots
            case 3:

                dot1.setCenterX(WINDOW_WIDTH - 300 + Tile_Size / 4);
                dot1.setCenterY(START_Y + Tile_Size / 6);
                dot1.setRadius(2);

                dot2.setCenterX(WINDOW_WIDTH - 300 + Tile_Size / 6);
                dot2.setCenterY(START_Y + Tile_Size / 3);
                dot2.setRadius(2);

                dot3.setCenterX(WINDOW_WIDTH - 300 + Tile_Size / 3);
                dot3.setCenterY(START_Y + Tile_Size / 3);
                dot3.setRadius(2);

                dot1.setFill(Color.BROWN);
                dot2.setFill(Color.BROWN);
                dot3.setFill(Color.BROWN);

                die.getChildren().add(dot1);
                die.getChildren().add(dot2);
                die.getChildren().add(dot3);
                break;

            // create four dots
            case 4:
                dot1.setCenterX(WINDOW_WIDTH - 300 + Tile_Size / 6);
                dot1.setCenterY(START_Y + Tile_Size / 6);
                dot1.setRadius(2);

                dot2.setCenterX(WINDOW_WIDTH - 300 + Tile_Size / 6);
                dot2.setCenterY(START_Y + Tile_Size / 3);
                dot2.setRadius(2);

                dot3.setCenterX(WINDOW_WIDTH - 300 + Tile_Size / 3);
                dot3.setCenterY(START_Y + Tile_Size / 6);
                dot3.setRadius(2);

                dot4.setCenterX(WINDOW_WIDTH - 300 + Tile_Size / 3);
                dot4.setCenterY(START_Y + Tile_Size / 3);
                dot4.setRadius(2);

                dot1.setFill(Color.BROWN);
                dot2.setFill(Color.BROWN);
                dot3.setFill(Color.BROWN);
                dot4.setFill(Color.BROWN);

                die.getChildren().add(dot1);
                die.getChildren().add(dot2);
                die.getChildren().add(dot3);
                die.getChildren().add(dot4);
                break;
        }

    }

    /**
     * method to update a die after being rolled
     *
     * @author u7754637 Pei Ling Lam
     */
    private void makeDie() {
        createDie(Marrakech.rollDie());
    }

    /**
     * clear the rugs on the board
     * initialize the rugs and dirhams to players again
     *
     * @author u7754637 Pei Ling Lam
     */
    private void newGame() {

    }

    /**
     * method that control of buttons
     *
     * @author u7754637 Pei Ling Lam
     */
    private void makeControls() {
        // Settings for a Button instance
        Button newGame = new Button();
        newGame.setLayoutX(100);
        newGame.setLayoutY(WINDOW_HEIGHT - 100);
        newGame.setOnAction(event -> this.newGame());
        newGame.setStyle("-fx-font-size: 16px;");
        newGame.setText("New Game");
        this.controls.getChildren().add(newGame);

        Button rollDie = new Button();
        rollDie.setLayoutX(WINDOW_WIDTH - BUTTON_BUFFER);
        rollDie.setLayoutY(WINDOW_HEIGHT - 100);
        rollDie.setOnAction(event -> this.makeDie()); // Lambda expression
        rollDie.setStyle("-fx-font-size: 16px;");
        rollDie.setText("Roll Die");
        this.controls.getChildren().add(rollDie);
    }


    /**
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     *
     * @author u7754892 Yaohui Hou
     */
    @Override
    public void start(Stage stage) throws Exception {
        // FIXME Task 7 and 15
        stage.setTitle("Marrakech");

        VBox startPage = new VBox(10);
        startPage.setAlignment(Pos.CENTER);
        startPage.setPadding(new Insets(20));

        // Create a ChoiceBox to choose the number of players
        ChoiceBox<String> playerNumChoice = new ChoiceBox<>();
        playerNumChoice.getItems().addAll("2 Players", "3 Players", "4 Players");
        playerNumChoice.setValue("4 Players");
        playerNumChoice.setStyle("-fx-font-size: 16px;");

        // Create start button. Click it then start game
        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 16px;");
        startButton.setOnAction(e -> {
            String selectedPlayerNum = playerNumChoice.getValue();
            int playerNum = Integer.parseInt(selectedPlayerNum.split(" ")[0]); // get the number of players
            System.out.println("Starting a " + playerNum + "-player game...");

            GameInterface gameScreen = new GameInterface(stage);
        });

        //Create cancel button. Click it then exit the game.
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-font-size: 16px;");
        cancelButton.setOnAction(e -> {
            stage.close();
        });

        startPage.getChildren().addAll(playerNumChoice, startButton, cancelButton);
        Scene scene = new Scene(startPage, WINDOW_WIDTH, WINDOW_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }
}

