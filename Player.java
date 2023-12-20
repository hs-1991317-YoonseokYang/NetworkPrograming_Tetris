package com.chobocho.player;

import choboTetris.TetrisBoardGui;
import com.chobocho.tetris.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Player implements IPlayer, ActionListener {
    final int BOARD_WIDTH = 10;
    final int BOARD_HEIGHT = 20;
    HashMap<String, Object> data = new HashMap<String, Object>();//테스트용 삭제 필요
    ITetris tetris;
    TetrisBoardGui board;//tetris에 대한 옵저버 클래스
    IPlayerDraw playerDraw;
    IPlayerAction playerAction;
    Timer tetrisTimer;

    private int gameSpeed = 0;

    public Player(TetrisBoardGui board, IPlayerDraw playerDraw, IPlayerAction playerAction) {
        this.board = board;

        this.tetris = new Tetris(BOARD_WIDTH, BOARD_HEIGHT);
        this.tetris.register(this.board);
        this.tetris.init();

        this.playerDraw = playerDraw;
        this.playerDraw.setTetris(this.tetris);

        this.playerAction = playerAction;
        this.playerAction.setPlayer(this);

        this.tetrisTimer = new Timer(0, this);

        //----------------------------아래는 테스트


        data.put("nowBlock", tetris.getCurrentBlock());
        // data.put("nextBlock", multiModel.getNextBlock());
        // data.put("turnNum", multiModel.getTurnNum());
        // data.put("nowRow", multiModel.getNowRow());
        // data.put("nowCol", multiModel.getNowCol());
        //data.put("gameScore", multiModel.getGameScore());
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < tetris.getHeight(); i++)
            for (int j = 0; j < tetris.getWidth(); j++)
                arr.add(tetris.getBoard()[i][j]);
        data.put("map", arr);

        System.out.println(data);

    }

    public void onDraw(Graphics g, int startX, int startY, int blockWidth, int blockHeight) {
        playerDraw.onDraw(g, startX, startY, blockWidth, blockHeight);
    }

    public void onPressKey(int k) {
        playerAction.onKeyEvent(k);
    }

    public void init() {
        tetris.init();
    }

    public void moveLeft() {
        tetris.moveLeft();
    }

    public void moveRight() {
        tetris.moveRight();
    }

    public void moveDown() {
        tetris.moveDown();
    }

    public void rotate() {
        tetris.rotate();
    }

    public void moveBottom() {
        tetris.moveBottom();
        tetris.moveDown();
    }

    public void play() {
        tetris.play();
        tetrisTimer.start();
    }

    public void pause() {
        tetris.pause();
        tetrisTimer.stop();
    }

    public void resume() {
        tetris.resume();
        tetrisTimer.start();
    }

    public boolean isIdleState() {
        return tetris.isIdleState();
    }

    public boolean isGameOverState() {
        return tetris.isGameOverState();
    }

    public boolean isPlayState() {
        return tetris.isPlayState();
    }

    public boolean isPauseState() {
        return tetris.isPauseState();
    }

    public boolean isEnableShadow() { return tetris.isEnableShadow(); }
    public void enableShadow() { tetris.enableShadow(); }
    public void disableShadow() { tetris.disableShadow(); }

    public void actionPerformed(ActionEvent e) {//이벤트 머시기 스레드
        System.out.println("Tetris (d) There is event");
        if (tetris == null ) {
            return;
        }
        if (tetris.isPlayState()) {
            tetris.moveDown();//여기서 게임 종료 여부를 찾는다<- 게임종료는 GamePlayState에서 확인<- GameBoard의 isAcceptable()이용
            gameSpeed = 700 - (tetris.getScore() / 100000);
            tetrisTimer.setDelay(gameSpeed);
        } else if (tetris.isGameOverState()) {
            tetrisTimer.stop();
        }
        //테스트
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < tetris.getHeight(); i++)
            for (int j = 0; j < tetris.getWidth(); j++)
                arr.add(tetris.getBoard()[i][j]);
        data.put("map", arr);//이 데이터만 있어도 어느 정도의 화면 공유는 가능하다. 쉽게 갈꺼면 이렇게 가는 것도 괜찮을 것임
        System.out.println(data);
    }

}
