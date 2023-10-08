package comp1110.ass2.gui;

import comp1110.ass2.Assam;
import comp1110.ass2.Board;
import comp1110.ass2.Players;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import comp1110.ass2.Game;

import java.util.List;

/**
 * @author u7754637 Pei Ling Lam
 */
public class Viewer extends Application {

    // The width and height of the window
    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

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

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField boardTextField;

    // total width and height of the board
    private static final double boardWidth = BOARD_WIDTH*Tile_Size;
    private static final double boardHeight = BOARD_HEIGHT*Tile_Size;

    // The start of the board in the x-direction (ie: x = 0)
    private static final double START_X = VIEWER_WIDTH / 2 - boardWidth / 2;

    // The start of the board in the y-direction (ie: y = 0)
    private static final double START_Y = VIEWER_HEIGHT / 2 - boardHeight / 2 - 30.0;


    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     *
     * @author u7754637 Pei Ling Lam
     */
    void displayState(String state) {
        // FIXME Task 5: implement the simple state viewer

        Game gameState = new Game();
        gameState = gameState.StringToGame(state);

        makeMosaicTrack();
        makeBoard();
        makeRug(gameState.getBoard());
        makeAssam(gameState.getAssam());
        makePlayers(gameState.getPlayersList());
        makePlayerLabel(gameState.getPlayersList());

    }

    /**
     * creating the mosaic track around the board to handle if Assam moved out of the board
     * @author u7754637 Pei Ling Lam
     */
    private void makeMosaicTrack() {
        for (int x=0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {

                Circle mosaic = new Circle();

                if (x % 2 == 0 && y == 0) { // initialize the top track
                    mosaic.setCenterX(START_X + BOARD_BORDER + BOARD_TILE_SHADOW_GAP + (x * Tile_Size) + Tile_Size / 1.5);
                    mosaic.setCenterY(START_Y - BOARD_BORDER * 2 - BOARD_TILE_SHADOW_GAP * 2 + (y * Tile_Size) + Tile_Size / 2);
                }else if (x % 2 == 0 && y == 6) { // initialize the bottom track
                    mosaic.setCenterX(START_X + BOARD_BORDER + BOARD_TILE_SHADOW_GAP + (x * Tile_Size) - Tile_Size / 4);
                    mosaic.setCenterY(START_Y + BOARD_BORDER * 2 + BOARD_TILE_SHADOW_GAP * 2 + (y * Tile_Size) + Tile_Size / 2);
                } else if (y % 2 != 0 && x == 0) { // initialize the left track
                    mosaic.setCenterX(START_X + BOARD_BORDER + BOARD_TILE_SHADOW_GAP + (x * Tile_Size) - Tile_Size / 4);
                    mosaic.setCenterY(START_Y - BOARD_BORDER * 2 - BOARD_TILE_SHADOW_GAP * 2 + (y * Tile_Size) + Tile_Size / 2);
                } else if (y % 2 != 0 && x == 6) { // initialize the right track
                    mosaic.setCenterX(START_X + BOARD_BORDER + BOARD_TILE_SHADOW_GAP + (x * Tile_Size) + Tile_Size / 1.5);
                    mosaic.setCenterY(START_Y + BOARD_BORDER * 2 + BOARD_TILE_SHADOW_GAP * 2 + (y * Tile_Size) + Tile_Size / 2);
                }

                mosaic.setRadius(Tile_Size - BOARD_BORDER);

                mosaic.setFill(Color.web("FFBF00"));
                root.getChildren().add(mosaic);
            }
        }
    }

    /**
     * creating background board
     * @author u7754637 Pei Ling Lam
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
        root.getChildren().add(boardBack);

        for (int x=0; x < BOARD_WIDTH; x++) {
            for (int y=0; y < BOARD_HEIGHT; y++) {
                Rectangle tileShadow = new Rectangle(
                        START_X+(x*Tile_Size)+(BOARD_TILE_SHADOW_GAP / 2),
                        START_Y+(y*Tile_Size)+(BOARD_TILE_SHADOW_GAP / 2),
                        Tile_Size - BOARD_TILE_SHADOW_GAP,
                        Tile_Size - BOARD_TILE_SHADOW_GAP);
                tileShadow.setFill(Color.TRANSPARENT);
                tileShadow.setStrokeWidth(2);
                tileShadow.setStroke(Color.GREY);
                tileShadow.setOpacity(0.5);
                root.getChildren().add(tileShadow);
            }
        }
    }

    /**
     * method that show the rugs on the board
     * @param board
     * @author u7754637 Pei Ling Lam
     */
    private void makeRug(Board board) {
        for(int i = 0; i < board.getBoardPosition().size(); i++) {
            if(board.getBoardPosition().get(i).getAbsolutePosition() != null) {
                double x = board.getBoardPosition().get(i).getAbsolutePosition().getX();
                double y = board.getBoardPosition().get(i).getAbsolutePosition().getY();
                Rectangle rugTile = new Rectangle(
                        START_X + (x * Tile_Size) + (BOARD_TILE_SHADOW_GAP / 2),
                        START_Y + (y * Tile_Size) + (BOARD_TILE_SHADOW_GAP / 2),
                        Tile_Size - BOARD_TILE_SHADOW_GAP,
                        Tile_Size - BOARD_TILE_SHADOW_GAP);

                rugTile.setFill(setPlayerColour(board.getBoardPosition().get(i).getColor()));
                rugTile.setStrokeWidth(2);
                rugTile.setStroke(Color.GREY);
                rugTile.setOpacity(0.5);
                root.getChildren().add(rugTile);
            }
        }

    }

    /**
     * method that draws assam and his facing direction
     * @param assam string of assam representation
     *
     * @author u7754637 Pei Ling Lam
     */
    public void makeAssam(Assam assam) {
        double x = assam.getAbsolutePosition().getX();
        double y = assam.getAbsolutePosition().getY();

        double centreX = START_X + (x * Tile_Size) + Tile_Size / 2;
        double centreY = START_Y + (y * Tile_Size) + Tile_Size / 2;
        double radius = Tile_Size / 3;
        double triangleSide = 5.0;

        Circle assamCircle = new Circle(centreX, centreY, radius);
        assamCircle.setFill(Color.web("C41E3A"));
        root.getChildren().add(assamCircle);

        Polygon direction = new Polygon();
        switch (assam.getCurrentDirection()) {
            case EAST:
                direction.getPoints().addAll(
                        centreX + triangleSide + radius, centreY,
                        centreX + radius, centreY + triangleSide / 2,
                        centreX + radius, centreY - triangleSide / 2
                );
                break;

            case WEST:
                direction.getPoints().addAll(
                        centreX - triangleSide - radius, centreY,
                        centreX - radius, centreY + triangleSide / 2,
                        centreX - radius, centreY - triangleSide / 2
                );
                break;

            case SOUTH:
                direction.getPoints().addAll(
                        centreX, centreY + triangleSide + radius,
                        centreX + triangleSide / 2, centreY + radius,
                        centreX - triangleSide / 2, centreY + radius
                );
                break;

            case NORTH:
                direction.getPoints().addAll(
                        centreX, centreY - triangleSide - radius,
                        centreX + triangleSide / 2, centreY - radius,
                        centreX - triangleSide / 2, centreY - radius
                );
                break;
        }

        direction.setFill(Color.web("C41E3A"));
        root.getChildren().add(direction);

    }

    /**
     * method that show the view of rugs and dirhams of each player
     * @param playersList list of the players
     *
     * @author u7754637 Pei Ling Lam
     */
    private void makePlayers(List<Players> playersList) {

        for (int i = 0; i < playersList.size(); i++) {
            if(i == 0 || i == 1) {
                double x = START_X - BOARD_TILE_SHADOW_GAP;
                double y = START_Y + (Tile_Size * 7 / 2) - Tile_Size;
                switch (i) {
                    case 0:
                        x -= Tile_Size * 3;
                        break;

                    case 1:
                        x += Tile_Size * 8;
                        break;
                }

                // create dirham
                for (int j = 0; j < playersList.get(i).getNumDirham(); j++) {
                    Circle dirham = new Circle(
                            x + j + 2,
                            y - Tile_Size / 2,
                            Tile_Size / 5);
                    dirham.setFill(Color.web("D4AF37"));
                    dirham.setStrokeWidth(0.5);
                    dirham.setStroke(Color.BLACK);
                    root.getChildren().add(dirham);
                }

                // create rug
                for (int j = 0; j < playersList.get(i).getNumRug(); j++) {
                    Rectangle rug = new Rectangle(
                             x + j,
                            y,
                            Tile_Size - BOARD_TILE_SHADOW_GAP,
                            (Tile_Size * 2) - BOARD_TILE_SHADOW_GAP);
                    rug.setFill(setPlayerColour(playersList.get(i).getColor()));
                    rug.setStrokeWidth(0.5);
                    rug.setStroke(Color.BLACK);
                    root.getChildren().add(rug);
                }
            } else {
                double x = START_X + (Tile_Size * 7 / 2) - Tile_Size;
                double y = START_Y - BOARD_TILE_SHADOW_GAP;
                switch (i) {
                    case 2:
                        y -= Tile_Size * 2.5;
                        break;

                    case 3:
                        y += Tile_Size * 8;
                        break;
                }

                // create dirham
                for (int j = 0; j < playersList.get(i).getNumDirham(); j++) {
                    Circle dirham = new Circle(
                            x + j + Tile_Size * 2.5,
                            y + Tile_Size / 2,
                            Tile_Size / 5);
                    dirham.setFill(Color.web("D4AF37"));
                    dirham.setStrokeWidth(0.5);
                    dirham.setStroke(Color.BLACK);
                    root.getChildren().add(dirham);
                }

                // create rug
                for (int j = 0; j < playersList.get(i).getNumRug(); j++) {
                    Rectangle rug = new Rectangle(
                            x,
                            y + j,
                            Tile_Size * 2 - BOARD_TILE_SHADOW_GAP,
                            Tile_Size - BOARD_TILE_SHADOW_GAP);
                    rug.setFill(setPlayerColour(playersList.get(i).getColor()));
                    rug.setStrokeWidth(0.5);
                    rug.setStroke(Color.BLACK);
                    root.getChildren().add(rug);
                }
            }
        }
    }

    /**
     * method that labels the number of rugs and dirhams of each player
     * @param playersList list of the players
     *
     * @author u7754637 Pei Ling Lam
     */
    private void makePlayerLabel(List<Players> playersList) {
        double labelX = VIEWER_WIDTH - 250;
        double labelY = 10;
        double labelStrokeWidth = 10;
        double radius = 5;
        Rectangle labelBox = new Rectangle(labelX, labelY, 230, 80);
        labelBox.setFill(Color.BLACK);
        root.getChildren().add(labelBox);

        for (int i = 0; i < playersList.size(); i++) {
            Circle playerColour = new Circle(labelX + labelStrokeWidth, labelY + labelStrokeWidth * 1.5 + (i * 15), radius);
            playerColour.setFill(setPlayerColour(playersList.get(i).getColor()));
            root.getChildren().add(playerColour);

            // cross out the players if they are out of the game
            if(!playersList.get(i).isInGame()) {
                Line line = new Line(labelX + labelStrokeWidth - radius * 2, labelY + labelStrokeWidth * 1.5 + (i * 15), labelX + 190, labelY + labelStrokeWidth * 1.5 + (i * 15));
                line.setStroke(Color.RED);
                line.setStrokeWidth(2);
                line.setFill(Color.RED);
                root.getChildren().add(line);
            }

            Text playerDetails = new Text(labelX + labelStrokeWidth + radius, labelY + labelStrokeWidth * 2 + (i * 15), "Player " + (i + 1) + "  | Rugs: " + playersList.get(i).getNumRug() + "  | Dirhams: " + playersList.get(i).getNumDirham());
            playerDetails.setFont(Font.font(12));
            playerDetails.setFill(Color.WHITE);
            root.getChildren().add(playerDetails);
        }
    }

    /**
     * method that set the colour of the node from players color
     * @param color color of the players from enum created in ass2 package
     * @return color type from JavaFx.scene.paint
     *
     * @author u7754637 Pei Ling Lam
     */
    private Color setPlayerColour(comp1110.ass2.Color color) {
        switch (color) {
            case RED:
                return Color.RED;

            case PURPLE:
                return Color.PURPLE;

            case CYAN:
                return Color.CYAN;

            case YELLOW:
                return Color.YELLOW;
        }
        return Color.TRANSPARENT;
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label boardLabel = new Label("Game State:");
        boardTextField = new TextField();
        boardTextField.setPrefWidth(800);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayState(boardTextField.getText());
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(boardLabel,
                boardTextField, button);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Marrakech Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
