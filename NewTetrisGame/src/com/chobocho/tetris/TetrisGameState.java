package com.chobocho.tetris;
import java.util.*;

/**
 * 
 */
public abstract class TetrisGameState {
    protected ITetris tetris;//왜 참조하나?<- 옵저버 구조 생각, Tetris클래스는 데이터(를 저장하고) 변경을 통보하는 클래스이다. 데이터 표현은 observer가한다.

    public TetrisGameState() {
    }

    public void init() {

    }

    public void rotate() {
        // TODO implement here
    }

    public void moveLeft() {
        // TODO implement here
    }

    public void moveRight() {
        // TODO implement here
    }

    public void moveDown() {
        // TODO implement here
    }

    public void fixCurrentBlock() {

    }

    public void moveBottom() {
        // TODO implement here
    }

    public void updateBlock() {
    }

    public boolean gameOver() {
        return false;
    }

    public void updateBoard() {

    }

    public Tetrominos getCurrentTetrominos() {
        return null;
    }

    public Tetrominos getNextTetrominos() {
        return null;
    }

    public Tetrominos getShodowTetrominos() {
        return null;
    }

    public void update() {
        TetrisLog.d("TetrisGameState.update()");
        if (tetris != null) {
            tetris.getObserver().update();//옵저버에게 업데이트<- 데이터 변경됨을 옵저버에게 통보함
        }
    }

    public boolean isIdleState() { return false; }
    public boolean isGameOverState() { return false; }
    public boolean isPlayState() { return false; }
    public boolean isPauseState() { return false; }
}