## Code Review

Reviewed by: Pei Ling Lam, u7754637

Reviewing code written by: YaoHui HOU u7754892

Component: 1. isRugValid, 2. makeMosaicTrack

**isRugValid Original Code**

    public static boolean isRugValid(String gameString, String rug) {
        char colour = rug.charAt(0);
        int position1 = Character.getNumericValue(rug.charAt(3));
        int position2 = Character.getNumericValue(rug.charAt(4));
        int position3 = Character.getNumericValue(rug.charAt(5));
        int position4 = Character.getNumericValue(rug.charAt(6));
        if(rug.length() != 7){
            return false;
        }else if(colour != 'r' && colour != 'c' && colour != 'p' && colour != 'y'){
            return false;
        }else return position1 <= 6 && position2 <= 6 && position3 <= 6 && position4 <= 6;
        // FIXME: Task 4
    }

**makeMosaicTrack Original Code**

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


### Comments

**isRugValid comments**

1. Best features: well-structured and namings
2. well-documented: There has no comments on what are the algorithms doing, at least for the if-else algorithm.
3. Program decomposition: The structure is quite clear as the variables are declared and initialized first, then the algorithm after the declarations.
4. Java code conventions: The variables are properly named and let people know their usage in the first glance.
   The style is consistent throughout the whole method.
5. There are no error suspected in this method, but it can be improved by using object-oriented programming, instead of using String.
   Also, the method uses only the parameter `rug` but did not use `gameString`. To be safe, `gameString` shall be used to check whether `rug` has fully covered another rug
6. My suggestion to improve to object-orientation, comments on the algorithms, and use all the parameters given:


    public static boolean isRugValid(String gameString, String rug) {
       Game game = new Game();
        game = game.StringToGame(gameString);


        Rug rug1 = new Rug();
        rug1 = rug1.StringToRug(rug);

        if(rug.length() != 7) {
            return false;
        }

        // check whether the colour exists
        if(rug1.getColor() == null) return false;

        // check whether rug has fully covered another rug
        for (int i = 0; i < game.getBoard().getBoardPosition().size(); i++) {
            if(game.getBoard().getBoardPosition().get(i) != null) {
                if (rug1.getRugID() == game.getBoard().getBoardPosition().get(i).getId() && rug1.getColor() == game.getBoard().getBoardPosition().get(i).getColor()) {
                    return false;
                }
            }
        }

        // check whether the rug has gone off the board
        for (int i = 0; i < rug1.getRelativePositions().length; i++) {
            if(rug1.getRelativePositions()[i].getX() > Viewer.BOARD_WIDTH - 1 || rug1.getRelativePositions()[i].getY() > Viewer.BOARD_HEIGHT - 1) {
                return false;
            }
        }

        return true;
    }

**makeMosaicTrack comments**

1. Best features: good use of namings and global variable to store the common value like `BOARD_WIDTH`.
2. Well-documented: There has no comments for the algorithms, it is good that add some comments at if-else statements.
3. Program decomposition: The structure is clear that there are obviously two for-loops and four if-else cases.
4. Java code conventions: The variables are properly named, and the style is consistent.
5. There are no obvious errors in this method, but the codes can be improved by removing the redundancy codes that creates circle, add nodes to group, and set color.
6. My suggestion to improve the codes by removing redundancy and add comments:


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