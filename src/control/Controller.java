package control;

import entity.Board;
import exception.BorderAheadException;
import exception.WallAheadException;
import gui.GUI;
import javafx.application.Application;

public class Controller {
    public static void main(String[] args) {
        Application.launch(GUI.class, args);
    }

    public static void play(Direction dir) {
        Board board = Board.getInstance();
        boolean canWalk = false;
        try {
            canWalk = board.virtualWalk(dir);
            if (canWalk) board.walk(dir);
        } catch (BorderAheadException e) {
            System.out.println(e);
        } catch (WallAheadException e) {
            System.out.println(e);
        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }
}
