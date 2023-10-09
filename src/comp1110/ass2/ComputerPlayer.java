package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {
    private String name;
    private Random random;

    public ComputerPlayer(String name) {
        this.name = name;
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    // Implement a method to generate a random move
    public String generateRandomMove() {
        // Replace this with your logic to generate a random move
        List<String> possibleMoves = new ArrayList<>();
        possibleMoves.add("A1B2"); // Replace with actual move format
        possibleMoves.add("C3D4"); // Replace with actual move format

        int randomIndex = random.nextInt(possibleMoves.size());
        return possibleMoves.get(randomIndex);
    }
}

