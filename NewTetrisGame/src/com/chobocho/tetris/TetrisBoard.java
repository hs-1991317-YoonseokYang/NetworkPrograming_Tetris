package com.chobocho.tetris;


/**
 * 
 */
public class TetrisBoard {
    private int width;
    private int height;
    private ITetris tetris;
    private int[][] board;

    /**
     * Default constructor
     */
    public TetrisBoard(int width, int height, Tetris tetris) {
        this.width = width;
        this.height = height;
        this.tetris = tetris;

        board = new int[this.height][this.width];
        init();
    }

    public void init() {
       for (int i = 0; i < this.getHeight(); i++) {
           for (int j = 0; j < this.getWidth(); j++) {
               board[i][j] = 0;
           }
       }
    }

    public int[][] getBoard() {
        return this.board;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isAcceptable(Tetrominos tetrominos) {
        int[][] block = tetrominos.getBlock();
        int w = tetrominos.getWidth();
        int h = tetrominos.getHeight();
        int x = tetrominos.getX();
        int y = tetrominos.getY();

        TetrisLog.d(tetrominos.toString()+ "isAcceptable():다음스탭에 놓일 위치에 대한 검사 W : " + w + " H : " + h);
        TetrisLog.d(tetrominos.toString()+ "isAcceptable():다음스탭에 놓일 위치에 대한 검사 X : " + x + " Y : " + y);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (block[i][j] != Tetris.EMPTY) {
                    if (x < 0 || (x+j) > (width-1) || (y+i) > (height-1) || y+h < 0) {//그러면 y의 초기 값은 항상 -h로 줘야겠군
                        return false;
                    }
                    if(y+i>=0) {//여기도 수정된 부분
                        if (board[y + i][x + j] != Tetris.EMPTY) {//Draw 클래스처럼 여기 startX를 두지 않는 것은 startX는 보드의 위치이고, 여기는 보드 클래스이기 때문이지
                            return false;//만약 나오다가 겹친다면 여긴 알 수가 없는 부분인가???//y==0까지 블럭이 차 있는 상황에서 
                        }
                    }
                }
            }
        }


        return true;
    }

    public void addTetrominos(Tetrominos tetrominos) {
        int[][] block = tetrominos.getBlock();
        int w = tetrominos.getWidth();
        int h = tetrominos.getHeight();
        int x = tetrominos.getX();
        int y = tetrominos.getY();
        int type = tetrominos.getType();

        //if(y<=0){
        //    tetris.setState();
       // }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(y+i>=0)//추가한 내용
                    if (block[i][j] != Tetris.EMPTY) {
                        board[i + y][j + x] = type;
                    }
            }
        }
    }

    public int arrange() {
        int x = 0, y = 0, m = 0;
        int count = 0;
        int removedLIne = 0;

        for (y = height-1; y >= 0; y--) {
            count = 0;
            for (x = 0; x < 10; x++) {
                if (board[y][x] != Tetris.EMPTY) {
                    count++;
                }
            }

            if (count == width) {
                removedLIne++;
                for (x = 0; x < width; x++) {
                    for (m = y; m > 0; m--) {
                        board[m][x] = board[m - 1][x];
                    }
                    board[m][0] = Tetris.EMPTY;
                }
                y++;
            }
        }
        return removedLIne;
    }
}