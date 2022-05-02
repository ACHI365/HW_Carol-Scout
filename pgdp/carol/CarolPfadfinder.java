package pgdp.carol;

import java.util.Arrays;

import static pgdp.MiniJava.*;

public class CarolPfadfinder {

    static boolean lastTurnsAreUseless(char[] instr, int filled) {
        if (filled <= 1)
            return false;
        char prev1 = instr[filled - 1];
        char prev2 = instr[filled - 2];
        if (prev1 == 'r' && prev2 == 'l' || prev1 == 'l' && prev2 == 'r' || prev1 == 'r' && prev2 == 'r')
            return true;
        if (filled == 2)
            return false;
        char prev3 = instr[filled - 3];
        return prev1 == 'l' && prev2 == 'l' && prev3 == 'l';
    }

    static boolean wasThereBefore(char[] instr, int filled){
       if (filled == 0) return false;

        int x = 0;
        int y = 0;

        int direction = 0;
        int i = filled - 1;
        while (i > -1){
            if (instr[i] == 'r') {
                if (direction == 0) {
                    direction = 3;
                } else if (direction == 1) {
                    direction = 0;
                } else if (direction == 2) {
                    direction = 1;
                } else {
                    direction = 2;
                }
            } else if (instr[i] == 'l') {
                if (direction == 0) {
                    direction = 1;
                } else if (direction == 1) {
                    direction = 2;
                } else if (direction == 2) {
                    direction = 3;
                } else {
                    direction = 0;
                }
            } else if (instr[i] == 's') {
                if (direction == 0) {
                    x = x + 1;
                } else if (direction == 1) {
                    y = y + 1;
                } else if (direction == 2) {
                    x = x - 1;
                } else {
                    y = y - 1;
                }
                if (x == 0 && y == 0) return true;
            } else if (instr[i] == 'p' || instr[i] == 'n') return false;
        i --;
        }



        return false;
    }

    static int getMinimalStepsAndTurns(int x, int y, int direction, int findX, int findY) {
        int newX = x;
        int newY = y;

        if (direction % 2 == 0)
            newX += 1 - direction;
        else
            newY += 2 - direction;

        int xDistance = Math.abs(x - findX);
        int yDistance = Math.abs(y - findY);
        int newXDistance = Math.abs(newX - findX);
        int newYDistance = Math.abs(newY - findY);
        int minStepsNeeded = xDistance + yDistance;
        if (minStepsNeeded == 0)
            return 0;
        int newMinStepsNeeded = newXDistance + newYDistance;
        int minTurns;
        if (newMinStepsNeeded > minStepsNeeded)
            minTurns = newXDistance * newYDistance > 0 && xDistance * yDistance == 0 ? 1 : 2;
        else
            minTurns = xDistance * yDistance > 0 ? 1 : 0;
        return minStepsNeeded + minTurns;
    }

    public static boolean findInstructionsAuxiliary(int[][] playground, int x, int y, int dir, int blocks, int findX,
                                                    int findY, char[] instr, int filled) {
        if (x == findX && y == findY) {
            printPlayground(playground, x, y, dir, blocks);
            // set remaining instructions to 'e' (stop)
            for (int i = filled; i < instr.length; i++) {
                instr[i] = 'e';
            }
            // found the way
            return true;
        }
        int remaining = instr.length - filled;
        // way could not be found
        if (remaining == 0)
            return false;

        int minStepsAndTurns = getMinimalStepsAndTurns(x, y, dir, findX, findY);
        // too far away already
        if (minStepsAndTurns > remaining)
            return false;

        int newX = x;
        int newY = y;

        if (dir % 2 == 0)
            newX += 1 - dir;
        else
            newY += 2 - dir;

        boolean isOutOfWater = playground[x][y] >= 0;
        boolean isNextInBounds = newX >= 0 && newX < playground.length && newY >= 0 && newY < playground[0].length;
        boolean canStep = isNextInBounds && Math.abs(playground[x][y] - playground[newX][newY]) < 2;
        boolean canTakeBlock = isNextInBounds && isOutOfWater && playground[newX][newY] >= 0 && blocks < 10;
        boolean canPlaceBlock = isNextInBounds && isOutOfWater && playground[newX][newY] < 9 && blocks > 0;

        // check previous instructions and eliminate paths
        // the order of the operation can make a difference (some are more "productive")

        if (canStep) {
            // try walk
            instr[filled] = 's';
            if (!wasThereBefore(instr, filled + 1)
                    && findInstructionsAuxiliary(playground, newX, newY, dir, blocks, findX, findY, instr, filled + 1))
                return true;
        }

        instr[filled] = 'l';
        if (!lastTurnsAreUseless(instr, filled + 1)
                && findInstructionsAuxiliary(playground, x, y, (dir + 1) % 4, blocks, findX, findY, instr, filled + 1))
            return true;

        instr[filled] = 'r';
        if (!lastTurnsAreUseless(instr, filled + 1)
                && findInstructionsAuxiliary(playground, x, y, (dir + 3) % 4, blocks, findX, findY, instr, filled + 1))
            return true;

        if (canTakeBlock && (filled == 0 || instr[filled - 1] != 'p')) {
            // try take block
            instr[filled] = 'n';
            playground[newX][newY]--;
            if (findInstructionsAuxiliary(playground, x, y, dir, blocks + 1, findX, findY, instr, filled + 1))
                return true;
            playground[newX][newY]++;
        }
        if (canPlaceBlock && (filled == 0 || instr[filled - 1] != 'n')) {
            // try place block
            instr[filled] = 'p';
            playground[newX][newY]++;
            if (findInstructionsAuxiliary(playground, x, y, dir, blocks - 1, findX, findY, instr, filled + 1))
                return true;
            playground[newX][newY]--;
        }
        return false;
    }


    public static boolean findInstructions(int[][] playground, int x, int y, int direction, int blocks, int findX,
                                           int findY, char[] instructions) {
        return findInstructionsAuxiliary(playground, x, y, direction, blocks, findX, findY, instructions, 0);
    }

    public static char[] findOptimalSolution(int[][] playground, int x, int y, int direction, int blocks, int findX, int findY, int searchLimit) {

        for (int i = 0; i <= searchLimit; i++) {
            char[] solution = new char[i];
            if (findInstructions(playground, x, y, direction, blocks, findX, findY, solution)) {
                return solution;
            }
        } return null;
    }

    public static void main(String[] args) {
        /*
         * You can change this main-Method as you want. This is not being tested.
         */
        // Note that in this array initialization the rows are in reverse order and both
        // x- and y-axis are swapped.
        int[][] playground = { //
                { 0, -1, -1, -1, -1 }, //
                { -1, -1, -1, -1, -1 }, //
                { -1, -1, 7, 8, 9 }, //
                { -1, -1, 8, 3, 5 }, //
                { -1, -1, 9, 5, 3 } //
        };
        int startX = 2;
        int startY = 1;
        int startDir = 0;
        int startBlocks = 1;

        printPlayground(playground, startX, startY, startDir, startBlocks);

        int findX = 4;
        int findY = 4;
        // this is expected to have an optimal solution with exactly 40 instructions
        char[] instructions = findOptimalSolution(playground, startX, startY, startDir, startBlocks, findX, findY, 40);
        boolean success = instructions != null;

        if (success) {
            write("SUCCESS");
            printPlayground(playground);
            write(Arrays.toString(instructions));
        } else {
            write("FAILED");
        }
    }

}

