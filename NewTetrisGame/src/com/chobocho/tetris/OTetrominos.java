package com.chobocho.tetris;

/**
 * 
 */
public class OTetrominos extends Tetrominos {

    public OTetrominos() {
        this.block = new int[][][]{
                {
                        {1, 1},
                        {1, 1}
                },
        };
        numOfBlockType = 1;
        x = 4;
        y = -2;//수정
        r = 0;
        w = 2;
        h = 2;
        type = 1;
    }

    public void rotate() {
        r = 0;
    }
    public int[][] getBlock() {
        return block[r];
    }
}