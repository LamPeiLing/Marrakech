package comp1110.ass2.gui;

import comp1110.ass2.Assam;
import comp1110.ass2.Board;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import comp1110.ass2.Game;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    // The width of the board (left to right)
    public final static int BOARD_WIDTH = 7;

    // The height of the board (top to bottom)
    public final static int BOARD_HEIGHT = 7;
    // Height and width of each tile
    private static final double Tile_Size = 70;

    // Pixel gap between the grey rectangles that indicates where the tiles are on the board
    private static final double BOARD_TILE_SHADOW_GAP = 0.5;

    // how much the blue background extends past the tiles
    private static final int BOARD_BORDER = 10;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField boardTextField;

    private static final double boardWidth = BOARD_WIDTH*Tile_Size;
    private static final double boardHeight = BOARD_HEIGHT*Tile_Size;

    private static final double START_X = VIEWER_WIDTH / 2 - boardWidth / 2;

    // The start of the board in the y-direction (ie: y = 0)
    private static final double START_Y = VIEWER_HEIGHT / 2 - boardHeight / 2 - 30.0;


    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     */
    void displayState(String state) {
        // FIXME Task 5: implement the simple state viewer

        Game gameState = new Game();
        gameState = gameState.StringToGame(state);

        makeMosaicTrack();
        makeBoard();
        makeRug(gameState.getBoard());
        makeAssam(gameState.getAssam());

    }

    /**
     * creating the mosaic track around the board to handle if Assam moved out of the board
     */
    private void makeMosaicTrack() {
        for (int x=0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                if (x % 2 == 0 && y == 0) {
                    Circle mosaic = new Circle(START_X-BOARD_BORDER+(BOARD_TILE_SHADOW_GAP / 2) + (x * Tile_Size) + Tile_Size - BOARD_TILE_SHADOW_GAP,
                            START_Y-BOARD_BORDER*3 - (BOARD_TILE_SHADOW_GAP*2) + (y * Tile_Size) + Tile_Size/2, Tile_Size - BOARD_BORDER);
                    mosaic.setFill(Color.web("FFBF00"));
                    root.getChildren().add(mosaic);
                }else if (x % 2 == 0 && y == 6) {
                    Circle mosaic = new Circle(START_X+BOARD_BORDER+(BOARD_TILE_SHADOW_GAP/2) + (x * Tile_Size),
                            START_Y+BOARD_BORDER*3 + (BOARD_TILE_SHADOW_GAP*2) + (y * Tile_Size) + Tile_Size/2, Tile_Size - BOARD_BORDER);
                    mosaic.setFill(Color.web("FFBF00"));
                    root.getChildren().add(mosaic);
                } else if (y % 2 != 0 && x == 0) {
                    Circle mosaic = new Circle(START_X+BOARD_BORDER+(BOARD_TILE_SHADOW_GAP/2) + (x * Tile_Size),
                            START_Y-BOARD_BORDER*3 - (BOARD_TILE_SHADOW_GAP*2) + (y * Tile_Size) + Tile_Size/2, Tile_Size - BOARD_BORDER);
                    mosaic.setFill(Color.web("FFBF00"));
                    root.getChildren().add(mosaic);
                } else if (y % 2 != 0 && x == 6) {
                    Circle mosaic = new Circle(START_X-BOARD_BORDER+(BOARD_TILE_SHADOW_GAP/2) + (x * Tile_Size) + Tile_Size - BOARD_TILE_SHADOW_GAP,
                            START_Y+ BOARD_BORDER*3 + (BOARD_TILE_SHADOW_GAP*2) + (y * Tile_Size) + Tile_Size/2, Tile_Size - BOARD_BORDER);
                    mosaic.setFill(Color.web("FFBF00"));
                    root.getChildren().add(mosaic);
                }
            }
        }
    }

    /**
     * creating background board
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
                tileShadow.setStrokeWidth(4);
                tileShadow.setStroke(Color.GREY);
                tileShadow.setOpacity(0.5);
                root.getChildren().add(tileShadow);
            }
        }
    }

    /**
     * method that show the rugs on the board
     * @param board
     */
    private void makeRug(Board board) {
        for(int i = 0; i < board.getBoardPosition().size(); i++) {
            if(board.getBoardPosition().get(i) != null) {
                double x = board.getBoardPosition().get(i).getAbsolutePosition().getX();
                double y = board.getBoardPosition().get(i).getAbsolutePosition().getY();
                Rectangle rugTile = new Rectangle(
                        START_X + (x * Tile_Size) + (BOARD_TILE_SHADOW_GAP / 2),
                        START_Y + (y * Tile_Size) + (BOARD_TILE_SHADOW_GAP / 2),
                        Tile_Size - BOARD_TILE_SHADOW_GAP,
                        Tile_Size - BOARD_TILE_SHADOW_GAP);

                switch (board.getBoardPosition().get(i).getColor()) {
                    case RED:
                        rugTile.setFill(Color.RED);
                        break;

                    case PURPLE:
                        rugTile.setFill(Color.PURPLE);
                        break;

                    case CYAN:
                        rugTile.setFill(Color.CYAN);
                        break;

                    case YELLOW:
                        rugTile.setFill(Color.YELLOW);
                        break;

                    default:
                        rugTile.setFill(Color.TRANSPARENT);
                }
                rugTile.setStrokeWidth(4);
                rugTile.setStroke(Color.GREY);
                rugTile.setOpacity(0.5);
                root.getChildren().add(rugTile);
            }
        }

    }

    private void makeAssam(Assam assam) {
        double x = assam.getAbsolutePosition().getX();
        double y = assam.getAbsolutePosition().getY();

        double centreX = START_X + (x * Tile_Size) + Tile_Size / 2;
        double centreY = START_Y + (y * Tile_Size) + Tile_Size / 2;
        double radius = Tile_Size / 3;
        double triangleSide = 10.0;

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
