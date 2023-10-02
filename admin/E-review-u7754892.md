## Code Review

Reviewed by: Yaohui HOU, u7754892

Reviewing code written by: Zexin TANG, u7770276

Component: 1.isPlacementValid 2.getPaymentAmount
 

**isPlacementValid Original Code**

    public static boolean isPlacementValid(String gameState, String rug) {

        Game game = new Game();
        game = game.StringToGame(gameState);

        if (rug.length() != 7) return false;

        Rug rug1 = new Rug();
        rug1 = rug1.StringToRug(rug);

        IntPair rugTile1 = rug1.getRelativePositions()[0];
        IntPair rugTile2 = rug1.getRelativePositions()[1];

        List<IntPair> assamAdjacent = game.getAssam().adjacentEdge();
        boolean flag = false;

        // check whether it is placed with adjacent to Assam
        // check for first segment of the rug
        for (int i = 0; i < assamAdjacent.size(); i++) {
            if (rugTile1.getX() == assamAdjacent.get(i).getX() && rugTile1.getY() == assamAdjacent.get(i).getY()) {
                flag = true;
            }
        }

        // if first segment does not place at the adjacent of assam, check the second segment
        if (!flag) {
            int i = 0;
            for (i = 0; i < assamAdjacent.size(); i++) {
                if (rugTile2.getX() == assamAdjacent.get(i).getX() && rugTile2.getY() == assamAdjacent.get(i).getY()) {
                    break;
                }
            }

            if (i == assamAdjacent.size()) {
                return false;
            }
        }

        // check if the rug fully cover another rug
        RugTile boardTile1 = game.getBoard().getRugTileOnPosition(rugTile1);
        RugTile boardTile2 = game.getBoard().getRugTileOnPosition(rugTile2);

        if (boardTile1 != null && boardTile2 != null) {
            if (boardTile1.getColor() == boardTile2.getColor() && boardTile1.getId() == boardTile2.getId())
                return false;
        }

        // check if the rug place under assam
        for (int i = 0; i < rug1.getRelativePositions().length; i++) {
            if (rug1.getRelativePositions()[i].getX() == game.getAssam().getAbsolutePosition().getX() && rug1.getRelativePositions()[i].getY() == game.getAssam().getAbsolutePosition().getY())
                return false;
        }

        return true;
    }


**getPaymentAmount Original Code**

     public static int getPaymentAmount(String gameString) {
        // Create a new Game object and parse the gameString
        Game game = new Game().StringToGame(gameString);

        // Now, you can use the game object to calculate the payment amount
        Assam assam = game.getAssam();
        List<RugTile> board = game.getBoard().getBoardPosition();
        int boardSize = 7; 

        Color rugColor = board.get(assam.getAbsolutePosition().getX() * boardSize + assam.getAbsolutePosition().getY()).getColor();

        int payment = 0;

        // Create a boolean array to keep track of visited squares
        boolean[][] visited = new boolean[boardSize][boardSize];

        // Define four possible directions (up, down, left, right)
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        // Perform a depth-first search to find connected squares of the same color
        payment = dfs(board, rugColor, assam.getAbsolutePosition().getX(), assam.getAbsolutePosition().getY(), visited, boardSize);

        return payment;
    }


### Comments

**isPlacementValid**

1. Naming: Variables and methods are clearly named and 
   it is easy to understand their purpose.
2. Integrity: The code takes full account of invalid placement 
   and returns false when a problem is found to ensure 
   the validity and integrity of the input.
3. Structure: The code divides the different checking steps into 
   several parts, which makes the code structure clearer.
   
Overall, this code is effective for the task, but could be improved.
For example, the initialization of `Game game = new Game();` and `Rug rug1 = new Rug();` 
is unnecessary because it is reassigned immediately afterwards.


**getPaymentAmount**

1. Naming: The code is highly readable. Variables and methods are 
   clearly named and their purpose can be easily understood. 
   Comments also help in understanding various parts of the code.
2. Modularity: The code has divided different tasks into several parts. 
   The DFS algorithm is encapsulated in a separate method, making the code clearer.

Overall, the quality of this code is high. Some can be improved.
For example, it is unnecessary to assign payment to 0. In addition, the variables 
dx and dy are not called and can be deleted.


