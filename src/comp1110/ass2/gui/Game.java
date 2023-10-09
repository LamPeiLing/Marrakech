package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game extends Application {

    // The width and height of the window
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    /*
     Distance to leave from a button to the right - used for setting up
     all the buttons at the bottom of the window.
     */
    private static final double BUTTON_BUFFER = 100.0;

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
    private static final Group boardGroup = new Group();
    private static final Group mosaicTrack = new Group();
    private static final Group rugs = new Group();
    private static final Group assamGroup = new Group();
    private static final Group playersGroup = new Group();
    private static final Group die = new Group();
    private static final Group controls = new Group();
    private Stage prevStage;

    /*
    global variables
     */
    private int playerNum; // number of players

    private int dieNum; // number of dot on the die
    private Assam assam = new Assam(); // to control assam regarding the position and direction

    private Board board = new Board();

    private comp1110.ass2.Game game = new comp1110.ass2.Game();

    private int currPlayer;

    private Button rotateLeftAssam;
    private Button rotateRightAssam;

    /*
    global variables regarding the rules of the game
     */
    private static final int INITIAL_DIRHAMS = 30; // initial value of the number of dirhams for each player
    private static final int INITIAL_RUGS = 15; // initial value of the number of rugs for each player

    /**
     * @author u7754892 Yaohui Hou
     * @author u7754637 Pei Ling Lam (supporting author)
     */
    class GameInterface{
        public GameInterface(Stage stage){
            Pane root = new Pane();
            makeControls();
            makeBoard();
            makeMosaicTrack();
            initializeRugsOnBoard();
            initializeAssam();
            createDie(Marrakech.rollDie());
            initializePlayers();
            stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
            root.getChildren().add(mosaicTrack);
            root.getChildren().add(boardGroup);
            root.getChildren().add(rugs);
            root.getChildren().add(assamGroup);
            root.getChildren().add(playersGroup);
            root.getChildren().add(die);
            root.getChildren().add(controls);
            stage.show();
        }
    }

    /**
     * A DraggableRug Object is a Rectangle that represents rugs that can move everywhere
     *
     * @author u7754637 Pei Ling Lam
     */
    class DraggableRug extends Rectangle {
        final Rug rug;  // the corresponding rug

        String tempGame; // temporary game string that compares with global variable game

        double mouseX,mouseY; // these coordinates are useful for drag-and-drop

        Angle tempAngle; // the angle of the gui piece (different from the backend when rotating)

        IntPair[] positions = new IntPair[2];

        boolean outOfBound = false;


        /*
        Piece's last position
        */
        double lastX, lastY;
        double x, y; // position of rug on the screen

        double width, height; // width and height of the rug

        public DraggableRug(Rug rug, double x, double y) {
            super(x, y, Tile_Size * 2 - BOARD_TILE_SHADOW_GAP, Tile_Size - BOARD_TILE_SHADOW_GAP);
            this.width = Tile_Size * 2 - BOARD_TILE_SHADOW_GAP;
            this.height = Tile_Size - BOARD_TILE_SHADOW_GAP;
            this.rug = rug;
            this.tempAngle = Angle.DEG_0;
            this.x = x;
            this.y = y;

            // set initial position when the rugs are not on the board yet
            positions[0] = new IntPair(0,0);
            positions[1] = new IntPair(0,0);
            rug.setRelativePositions(positions);


            // draw the draggable rugs
            this.setLayoutX(x);
            this.setLayoutY(y);
            setFill(setPlayerColour(rug.getColor()));
            setStrokeWidth(0.5);
            setStroke(Color.BLACK);


            /*
             Event handler for if the user presses the mouse on this
             specific piece.
             */
            this.setOnMousePressed(event -> {

                // Set these values to prepare for drag-and-drop
                this.mouseX = event.getSceneX();
                this.mouseY = event.getSceneY();

                this.requestFocus();

                this.setOnKeyPressed(keyEvent -> {
                    // rotate if the 'r' key is pressed
                    if (keyEvent.getCode().equals(KeyCode.R)) {
                        tempAngle = Angle.getAngleFromValue((tempAngle.value+90) % 360);
                        this.setRotate(tempAngle.value);
                    }
                });
            });

            /*
             Event handler for if the user drags the mouse over this
             specific piece.
             */
            this.setOnMouseDragged(event -> {

                /*
                 Move the piece by the difference in mouse position
                 since the last drag.
                 */
                double diffX = event.getSceneX() - mouseX;
                double diffY = event.getSceneY() - mouseY;
                this.setLayoutX(this.getLayoutX() + diffX);
                this.setLayoutY(this.getLayoutY() + diffY);

                /*
                 Update `mouseX` and `mouseY` and repeat the process.
                 */
                this.mouseX = event.getSceneX();
                this.mouseY = event.getSceneY();
            });


             /*
             Event handler for if the user releases the left mouse button over this
             specific piece.
             */
            this.setOnMouseReleased(event -> {

                calculateRugPosition();

                // check whether the rug is out of bound
                for (IntPair p: positions) {
                    if(p.getX() < 0 || p.getX() > BOARD_WIDTH - 1 || p.getY() < 0 || p.getY() > BOARD_HEIGHT - 1) {
                        outOfBound = true;
                    }
                }

                // to avoid crashing in terminal
                if(outOfBound) {
                    // set rectangle back to horizontal if rotated become vertical
                    if(tempAngle == Angle.DEG_90 || tempAngle == Angle.DEG_270) {
                        this.setRotate(180);
                    }
                    this.snapToLast();
                    outOfBound = false;
                } else {

                    rug.setRelativePositions(positions);

                    // check whether current game is same as the state after making placement
                    tempGame = Marrakech.makePlacement(game.GameToString(), rug.RugToString());
                    if (Objects.equals(tempGame, game.GameToString())) {
                        // set rectangle back to horizontal if rotated become vertical
                        if(tempAngle == Angle.DEG_90 || tempAngle == Angle.DEG_270) {
                            this.setRotate(180);
                        }
                        this.snapToLast();
                    } else {
                        game = game.StringToGame(tempGame); // update game state

                        Players current_player = game.getPlayersList().get(currPlayer);

                        // get the color to find player
                        RugTile rugTile1 = board.getRugTileOnPosition(rug.getRelativePositions()[0]);
                        RugTile rugTile2 = board.getRugTileOnPosition(rug.getRelativePositions()[1]);

                        if (rugTile1 != null && rugTile1.getColor() != current_player.getColor()) {
                            Players playerPayTo = game.findPlayerFromColor(rugTile1.getColor());
                            if (playerPayTo != null && playerPayTo.isInGame()) {
                                int amount = 1;

                                // check whether current player has enough dirhams to pay
                                if (current_player.getNumDirham() < 1) { // if not enough dirhams
                                    current_player.updateNumDirham(current_player.getNumDirham(), true);
                                    playerPayTo.updateNumDirham(current_player.getNumDirham(), false);
                                    current_player.setIsInGame(false);
                                } else { // if enough dirhams
                                    current_player.updateNumDirham(amount, true);
                                    playerPayTo.updateNumDirham(amount, false);
                                }

                                //update players in game
                                game.updatePlayer(current_player);
                                game.updatePlayer(playerPayTo);
                            }
                        }

                        if (rugTile2 != null && rugTile2.getColor() != current_player.getColor()) {
                            Players playerPayTo = game.findPlayerFromColor(rugTile2.getColor());

                            if (playerPayTo != null && playerPayTo.isInGame()) {
                                int amount = 1;

                                // check whether current player has enough dirhams to pay
                                if (current_player.getNumDirham() < 1) { // if not enough dirhams
                                    current_player.updateNumDirham(current_player.getNumDirham(), true);
                                    playerPayTo.updateNumDirham(current_player.getNumDirham(), false);
                                    playerPayTo.getScores().updateRugScore(true);
                                    current_player.setIsInGame(false);
                                    informPlayerOut(getPlayerNum(current_player.getColor().value));
                                } else { // if enough dirhams
                                    current_player.updateNumDirham(amount, true);
                                    playerPayTo.updateNumDirham(amount, false);
                                    playerPayTo.getScores().updateRugScore(true);
                                }

                                //update players in game
                                game.updatePlayer(current_player);
                                game.updatePlayer(playerPayTo);
                            }
                        }

                        board = game.getBoard(); // update board state
                        updateNextPlayer(); // next player
                        updateGameInfo(); // update game state

                        // checking if the current game state is a solution to the problem
                        if (Marrakech.isGameOver(game.GameToString())) {
                            char winner = Marrakech.getWinner(game.GameToString());
                            Alert solved = new Alert(Alert.AlertType.INFORMATION);
                            if (winner == 'n') {
                                solved.setHeaderText("Game has not finished yet!");
                                solved.setContentText("Please continue the game.");
                            } else if (winner == 't') {
                                solved.setHeaderText("There is no winner!");
                                solved.setContentText("More than one players share the same total score and number of Dirhams!");
                            } else {
                                solved.setTitle("Congratulations!");
                                solved.setHeaderText("Player " + getPlayerNum(winner) + " is the winner!");
                                comp1110.ass2.Color color = comp1110.ass2.Color.RED; // simply put a color to initialize
                                solved.setContentText("Scores: " + game.findPlayerFromColor(color.getColorFromValue(winner)).getScores().getTotalScore());
                            }
                            solved.show();
                        }

                    }
                }
            });

            this.snapToLast();
        }


        /**
         * @return the closest position on the board to where this piece
         *         is currently positioned
         */
        public IntPair getSnapPosition() {
            int x = (int) Math.round((this.getLayoutX() + this.x - START_X)  / Tile_Size);
            int y = (int) Math.round((this.getLayoutY() + this.y - START_Y) / Tile_Size);
            return new IntPair(x, y);
        }

        public void calculateRugPosition() {
            switch (tempAngle) {
                case DEG_0:
                case DEG_180:
                    positions[0] = getSnapPosition();
                    positions[1].setX(getSnapPosition().getX() + 1);
                    positions[1].setY(getSnapPosition().getY());
                    break;

                case DEG_90:
                case DEG_270:
                    positions[0].setX(getSnapPosition().getX());
                    positions[0].setY(getSnapPosition().getY() - 1);
                    positions[1] = getSnapPosition();
                    break;
            }
        }


        /**
         * Snaps this piece back to its last position which must be valid
         */
        public void snapToLast() {
            this.setLayoutX(this.lastX);
            this.setLayoutY(this.lastY);

            // reset the temporary angle and position
            tempAngle = Angle.DEG_0;
            positions[0] = new IntPair(0,0);
            positions[1] = new IntPair(0,0);
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
        boardGroup.getChildren().add(boardBack);

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
                boardGroup.getChildren().add(tileShadow);
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
     * method that initialize the board that has no rugs on it
     *
     * @author u7754637 Pei Ling Lam
     */
    private void initializeRugsOnBoard() {
        List<RugTile> rugTiles = new ArrayList<>();
        for (int i = 0; i < BOARD_HEIGHT*BOARD_WIDTH; i++) {
            rugTiles.add(new RugTile());
        }
        board.setBoardPosition(rugTiles);
        game.setBoard(board);
    }

    /**
     * method that show the rugs on the board
     *
     * @author u7754637 Pei Ling Lam
     */
    private void makeRug() {
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
                rugs.getChildren().add(rugTile);
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
        dieNum = Marrakech.rollDie();
        createDie(dieNum);
        moveAssam();
        rotateRightAssam.setDisable(false);
        rotateLeftAssam.setDisable(false);
    }

    /**
     * initialize assam when the new game starts
     * position set to the middle of the board
     * direction set to facing downwards
     *
     * @author u7754637 Pei Ling Lam
     */
    private void initializeAssam() {
        assam.setAbsolutePosition(new IntPair(3,3));
        assam.setCurrentDirection(Direction.SOUTH);
        makeAssam();
    }

    /**
     * update assam direction whenever the button is pressed by rotating 90 degrees clockwise
     *
     * @author u7754637 Pei Ling Lam
     */
    private void rotateLeftAssam() {
        assam = assam.StringToAssam(Marrakech.rotateAssam(assam.AssamToString(), -90));
        assamGroup.getChildren().clear();
        makeAssam();
        rotateRightAssam.setDisable(true);
        rotateLeftAssam.setDisable(true);
    }

    private void rotateRightAssam() {
        assam = assam.StringToAssam(Marrakech.rotateAssam(assam.AssamToString(), 90));
        assamGroup.getChildren().clear();
        makeAssam();
        rotateRightAssam.setDisable(true);
        rotateLeftAssam.setDisable(true);
    }

    /**
     * update assam position when a die is rolled
     *
     * @author u7754637 Pei Ling Lam
     */
    private void moveAssam() {
        assam = assam.StringToAssam(Marrakech.moveAssam(assam.AssamToString(), dieNum));
        assamGroup.getChildren().clear();
        makeAssam();
        Players current_player = game.getPlayersList().get(currPlayer);

        // get the color to find player
        RugTile rugTile = board.getRugTileOnPosition(assam.getAbsolutePosition());
        if(rugTile != null) {
            Players playerPayTo = game.findPlayerFromColor(rugTile.getColor());

            if (playerPayTo != null && playerPayTo.isInGame()) {
                int amount = Marrakech.getPaymentAmount(game.GameToString());

                // check whether current player has enough dirhams to pay
                if (current_player.getNumDirham() < amount) { // if not enough dirhams
                    current_player.updateNumDirham(current_player.getNumDirham(), true);
                    playerPayTo.updateNumDirham(current_player.getNumDirham(), false);
                    current_player.setIsInGame(false);
                    informPlayerOut(getPlayerNum(current_player.getColor().value));
                } else { // if enough dirhams
                    current_player.updateNumDirham(amount, true);
                    playerPayTo.updateNumDirham(amount, false);
                }

                //update players in game
                game.updatePlayer(current_player);
                game.updatePlayer(playerPayTo);
                updateGameInfo();
            }
        }
    }

    /**
     * draw assam according to the position and direction
     *
     * @author u7754637 Pei Ling Lam
     */
    private void makeAssam() {
        double x = assam.getAbsolutePosition().getX();
        double y = assam.getAbsolutePosition().getY();

        double centreX = START_X + (x * Tile_Size) + Tile_Size / 2;
        double centreY = START_Y + (y * Tile_Size) + Tile_Size / 2;
        double radius = Tile_Size / 3;
        double triangleSide = 5.0;

        Circle assamCircle = new Circle(centreX, centreY, radius);
        assamCircle.setFill(Color.web("C41E3A"));
        assamGroup.getChildren().add(assamCircle);

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
        assamGroup.getChildren().add(direction);
        game.setAssam(assam);
    }

    /**
     * method that initialize the players by drawing dirhams and rugs when starting a new game
     *
     * @author u7754637 Pei Ling Lam
     */
    private void initializePlayers() {
        List<Players> playersList = new ArrayList<>();

        for (int i = 0; i < playerNum; i++) {
            Players player = new Players(INITIAL_DIRHAMS, INITIAL_RUGS, true);

            if (i == 0) {
                player.setColor(comp1110.ass2.Color.CYAN);
            } else if (i == 1) {
                player.setColor(comp1110.ass2.Color.YELLOW);
            } else if (i == 2) {
                player.setColor(comp1110.ass2.Color.PURPLE);
            } else {
                player.setColor(comp1110.ass2.Color.RED);
            }

            playersList.add(player);
        }
        game.setPlayersList(playersList);
        currPlayer = 0; // first player set to cyan
        makePlayers();
    }


    /**
     * method that draw rugs and dirhams of the players according to game string
     *
     * @author u7754637 Pei Ling Lam
     */
    private void makePlayers() {
        for (int i = 0; i < playerNum; i++) {
            double x,y;

            //create dirham
            for (int j = 0; j < game.getPlayersList().get(i).getNumDirham(); j++) {
                if(i == 0) {
                    x = START_X - BOARD_TILE_SHADOW_GAP - Tile_Size * 3 + j + 2;
                    y = START_Y + (Tile_Size * 7 / 2) - Tile_Size - Tile_Size/2;
                } else if(i == 1)  {
                    x = START_X - BOARD_TILE_SHADOW_GAP + Tile_Size * 8 + j + 2;
                    y = START_Y + (Tile_Size * 7 / 2) - Tile_Size - Tile_Size/2;
                } else if (i == 2) {
                    x = START_X + (Tile_Size * 7 / 2) - Tile_Size + j + Tile_Size * 2.5;
                    y = START_Y - BOARD_TILE_SHADOW_GAP - Tile_Size * 2.5 + Tile_Size / 2;
                } else {
                    x = START_X + (Tile_Size * 7 / 2) - Tile_Size + j + Tile_Size * 2.5;
                    y = START_Y - BOARD_TILE_SHADOW_GAP + Tile_Size * 8 + Tile_Size / 2;
                }
                drawDirham(x, y);
            }

            // create rugs
            for (int j = 0; j < game.getPlayersList().get(i).getNumRug(); j++) {
                if(i == 0) {
                    x = START_X - BOARD_TILE_SHADOW_GAP - Tile_Size * 3.5 + j;
                    y = START_Y + (Tile_Size * 7 / 2) - Tile_Size;
                } else if(i == 1)  {
                    x = START_X - BOARD_TILE_SHADOW_GAP + Tile_Size * 8 + j;
                    y = START_Y + (Tile_Size * 7 / 2) - Tile_Size;
                } else if (i == 2) {
                    x = START_X + (Tile_Size * 7 / 2) - Tile_Size;
                    y = START_Y - BOARD_TILE_SHADOW_GAP - Tile_Size * 2.5 + j;
                } else {
                    x = START_X + (Tile_Size * 7 / 2) - Tile_Size;
                    y = START_Y - BOARD_TILE_SHADOW_GAP + Tile_Size * 8 + j;
                }

                Rug rug = new Rug(game.getPlayersList().get(i).getColor(), j, new IntPair[2]);

                // rug can only move when it is the player's turn
                // only the last piece of rug can move
                if(i == currPlayer && j == game.getPlayersList().get(i).getNumRug() - 1) {
                    drawDraggableRug(rug, x, y);
                } else {
                    drawRug(rug, x, y);
                }
            }
        }

        makePlayerLabel();
    }

    /**
     * method to draw one piece of rug
     * @param r current rug to get colour
     * @param x x-coordinate of the rug on screen
     * @param y y-coordinate of the rug on screen
     *
     * @author u7754637 Pei Ling Lam
     */
    private void drawRug(Rug r, double x, double y) {
        Rectangle rug = new Rectangle(
                x,
                y ,
                Tile_Size * 2 - BOARD_TILE_SHADOW_GAP,
                Tile_Size - BOARD_TILE_SHADOW_GAP);
        rug.setFill(setPlayerColour(r.getColor()));
        rug.setStrokeWidth(0.5);
        rug.setStroke(Color.BLACK);
        playersGroup.getChildren().add(rug);
    }

    /**
     * method to draw draggable rugs
     * @param rug current rug
     * @param x x-coordinate on screen
     * @param y y-coordinate on screen
     *
     * @author u7754637 Pei Ling Lam
     */
    private void drawDraggableRug(Rug rug, double x, double y) {
        DraggableRug draggableRug = new DraggableRug(rug,  x,  y);
        playersGroup.getChildren().add(draggableRug);
    }

    /**
     * method to draw one piece of dirham
     * @param x x-coordinate of the dirham on screen
     * @param y y-coordinate of the dirham on screen
     *
     * @author u7754637 Pei Ling Lam
     */
    private void drawDirham(double x, double y) {
        Circle dirham = new Circle(
                x,
                y,
                Tile_Size / 5);
        dirham.setFill(Color.web("D4AF37"));
        dirham.setStrokeWidth(0.5);
        dirham.setStroke(Color.BLACK);
        playersGroup.getChildren().add(dirham);
    }

    /**
     * method that labels the number of rugs and dirhams of each player
     *
     * @author u7754637 Pei Ling Lam
     */
    private void makePlayerLabel() {
        double labelX = WINDOW_WIDTH - 250;
        double labelY = 10;
        double labelStrokeWidth = 10;
        double radius = 5;

        Rectangle labelBox = new Rectangle(labelX, labelY, 230, 80);
        labelBox.setFill(Color.BLACK);
        playersGroup.getChildren().add(labelBox);

        for (int i = 0; i < game.getPlayersList().size(); i++) {
            Circle playerColour = new Circle(labelX + labelStrokeWidth, labelY + labelStrokeWidth * 1.5 + (i * 15), radius);
            playerColour.setFill(setPlayerColour(game.getPlayersList().get(i).getColor()));
            playersGroup.getChildren().add(playerColour);

            // cross out the players if they are out of the game
            if(!game.getPlayersList().get(i).isInGame()) {
                Line line = new Line(labelX + labelStrokeWidth - radius * 2, labelY + labelStrokeWidth * 1.5 + (i * 15), labelX + 190, labelY + labelStrokeWidth * 1.5 + (i * 15));
                line.setStroke(Color.RED);
                line.setStrokeWidth(2);
                line.setFill(Color.RED);
                playersGroup.getChildren().add(line);
            }

            Text playerDetails = new Text(labelX + labelStrokeWidth + radius, labelY + labelStrokeWidth * 2 + (i * 15), "Player " + (i + 1) + "  | Rugs: " + game.getPlayersList().get(i).getNumRug() + "  | Dirhams: " + game.getPlayersList().get(i).getNumDirham());
            playerDetails.setFont(Font.font(12));
            playerDetails.setFill(Color.WHITE);
            playersGroup.getChildren().add(playerDetails);
        }
    }

    /**
     * method that set the colour of the node from players color
     * @param color color of the players from enum created in ass2 package
     * @return color type from JavaFx.scene.paint
     *
     * @author u7754637 Pei Ling Lam
     */
    public Color setPlayerColour(comp1110.ass2.Color color) {
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
     * method that update the rugs on the board, players' remaining dirham and rugs, and the label
     *
     * @author u7754637 Pei Ling Lam
     */
    private void updateGameInfo() {
        rugs.getChildren().clear();
        makeRug();
        playersGroup.getChildren().clear();
        makePlayers();
        makePlayerLabel();
    }

    /**
     * method that update the turn of current player
     *
     * @author u7754637 Pei Ling Lam
     */
    private void updateNextPlayer() {
        currPlayer++;
        if(currPlayer == playerNum) {
            currPlayer = 0;
        }
        if(!game.getPlayersList().get(currPlayer).isInGame()) {
            updateNextPlayer();
        }
    }

    private int getPlayerNum(char c) {
        switch (c) {
            case 'c':
                return 0;

            case 'y':
                return 1;

            case 'p':
                return 2;

            case 'r':
                return 3;
        }

        return 0;
    }

    /**
     * method to show that which player is out the game
     * @param i player number
     */
    private void informPlayerOut(int i) {
        Alert out = new Alert(Alert.AlertType.INFORMATION);
        out.setTitle("Attention!");
        out.setHeaderText("Player " + i + " is out!");
        out.show();
    }

    /**
     * clear the previous stage and start a new stage
     *
     * @author u7754637 Pei Ling Lam
     */
    private void newGame() throws Exception {
        prevStage.close();
        Stage currStage = new Stage();
        playersGroup.getChildren().clear();
        assamGroup.getChildren().clear();
        rugs.getChildren().clear();
        boardGroup.getChildren().clear();
        playersGroup.getChildren().clear();
        root.getChildren().clear();
        GameInterface gameInterface = new GameInterface(currStage);
        start(currStage);

    }

    /**
     * method that control of buttons
     *
     * @author u7754637 Pei Ling Lam
     */
    private void makeControls() {
        // Settings for a Button instance
        Button newGame = new Button();
        newGame.setLayoutX(BUTTON_BUFFER);
        newGame.setLayoutY(WINDOW_HEIGHT - 70);
        newGame.setOnAction(event -> {
            try {
                this.newGame();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        newGame.setStyle("-fx-font-size: 16px;");
        newGame.setText("New Game");
        this.controls.getChildren().add(newGame);

        rotateLeftAssam = new Button();
        rotateLeftAssam.setLayoutX(newGame.getLayoutX() + BUTTON_BUFFER + 50);
        rotateLeftAssam.setLayoutY(WINDOW_HEIGHT - 70);
        rotateLeftAssam.setOnAction(event -> this.rotateLeftAssam());
        rotateLeftAssam.setStyle("-fx-font-size: 16px;");
        rotateLeftAssam.setText("Rotate Assam (Left)");
        this.controls.getChildren().add(rotateLeftAssam);

        rotateRightAssam = new Button();
        rotateRightAssam.setLayoutX(rotateLeftAssam.getLayoutX() + BUTTON_BUFFER * 2);
        rotateRightAssam.setLayoutY(WINDOW_HEIGHT - 70);
        rotateRightAssam.setOnAction(event -> this.rotateRightAssam());
        rotateRightAssam.setStyle("-fx-font-size: 16px;");
        rotateRightAssam.setText("Rotate Assam (Right)");
        this.controls.getChildren().add(rotateRightAssam);

        Button rollDie = new Button();
        rollDie.setLayoutX(rotateRightAssam.getLayoutX() + BUTTON_BUFFER * 2);
        rollDie.setLayoutY(WINDOW_HEIGHT - 70);
        rollDie.setOnAction(event -> this.makeDie());
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
            playerNum = Integer.parseInt(selectedPlayerNum.split(" ")[0]); // get the number of players
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

        prevStage = stage;

        stage.setScene(scene);
        stage.show();
    }
}

