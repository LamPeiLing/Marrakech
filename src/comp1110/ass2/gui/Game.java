package comp1110.ass2.gui;

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

import java.util.List;

public class Game extends Application {

    private static final Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    // The width of the board (left to right)
    public final static int BOARD_WIDTH = 7;

    // The height of the board (top to bottom)
    public final static int BOARD_HEIGHT = 7;
    private static final double Tile_Size = 50;
    private static final double BOARD_TILE_SHADOW_GAP = 0.5;
    private static final int BOARD_BORDER = 10;
    private static final double boardWidth = BOARD_WIDTH*Tile_Size;
    private static final double boardHeight = BOARD_HEIGHT*Tile_Size;

    private static final double START_X = (double) WINDOW_WIDTH / 2 - boardWidth / 2;

    // The start of the board in the y-direction (ie: y = 0)
    private static final double START_Y = (double) WINDOW_HEIGHT / 2 - boardHeight / 2 - 30.0;
    private static final Group board = new Group();
    private static final Group mosaicTrack = new Group();
    private static final Group rugs = new Group();

    static class GameInterface{
        public GameInterface(Stage stage){
            Pane root = new Pane();
            makeMosaicTrack();
            makeBoard();
            stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
            root.getChildren().add(mosaicTrack);
            root.getChildren().add(board);
            stage.show();
        }
    }


    private static void makeBoard() {
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
     */
    private static void makeMosaicTrack() {
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

