package com.chobocho.player;

import com.chobocho.tetris.ITetris;
import com.chobocho.tetris.Tetris;
import com.chobocho.tetris.Tetrominos;

import java.awt.*;

public class PlayerOneDraw implements IPlayerDraw {
    ITetris tetris;

    public void setTetris(ITetris tetris) {
        this.tetris = tetris;
    }

    public void onDraw(Graphics g, int startX, int startY, int blockWidth, int blockHeight) {//startX는 게임판의 시작위치
        // Draw board<- tetrisBoardGui로 부터 호출되고 <- 나머지 데이터는 tetris로 부터 받아서~~
        //우리가 서버로 보내야할 내용은 tetris부분이다(달라지는 데이터를 보내야 한다.<- 이녀석이 게임데이터를 총괄함)
        int i = 0, j = 0;

        int[][] board = tetris.getBoard();
        //이 데이터 보낸다!!//만약 4인용이면 tetris 게임도 4개니까 playeronedraw객체도 4개가 되야할까?<- 인터페이스를 하나 두고 1~4인용 draw를 만들면 될 듯. 아니면
        //새로운 스레드가 필요할 수도 있다. 화면 그리기용 한 개!

        for (i = 0; i < tetris.getWidth(); i++) {
            for (j = 0; j < tetris.getHeight(); j++) {
                drawRectangle(g, startX + i * blockWidth,
                        startY + j * blockHeight, board[j][i], blockWidth, blockHeight);//블록이 쌓이면 board배열이 쌓인 블록에 맞춰
                //최신화 되는 것 같다~~ 그걸 여기서 하지는 안하. <-이건 ㄹㅇ 화면 그리기이다.
            }
        }//내려오는 블럭 제외, 게임 화면을 전부 그린다.

        if (tetris.isPlayState()) {
            System.out.println("Tetris (d) PlayerOne DrawBlock!");

            if (tetris.isEnableShadow()) {
                Tetrominos shadowBlock = tetris.getShadowBlock();
                drawShadowBlock(g, shadowBlock, startX, startY, blockWidth, blockHeight);//그림자를 그린다
            }

            Tetrominos currentBlock = tetris.getCurrentBlock();
            drawBlock(g, currentBlock, startX, startY, blockWidth, blockHeight);//블럭을 그린다.<- 무슨 블럭??..

            Tetrominos nextblock = tetris.getNextBlock();
            int nextBlockX = startX + blockWidth * (tetris.getWidth()-1);//<- 다음에 떨어질 블럭 미리보기??
            int nextBlockY = startY + blockHeight * 4;

            drawNextBlock(g, nextblock, nextBlockX, nextBlockY, blockWidth/2, blockHeight/2);//여기인듯??
        }//게임진행 창 너머를 뜻함

        int scorePointX =  startX + blockWidth * (tetris.getWidth()+1);
        int scorePointY =  startY + blockHeight * 8;
        g.setColor(Color.CYAN);
        g.setFont(new Font("Purisa", Font.PLAIN, 14));
        g.drawString(String.valueOf(tetris.getScore()), scorePointX, scorePointY);
    }


    private void drawBlock(Graphics g, Tetrominos block, int startX, int startY, int blockWidth, int blockHeight) {
        int i = 0, j = 0;
        int[][] ablock = block.getBlock();//테트리스 블럭의 모양을 저장한 배열
        int sw = block.getWidth();
        int sh = block.getHeight();
        int sx = block.getX();
        int sy = block.getY();
        int sType = block.getType();

        for (i = 0; i < sw; i++) {
            for (j = 0; j < sh; j++) {
                if (ablock[j][i] != Tetris.EMPTY && sy+j>=0 ) {////////이 부분을 수정했다. 위에서부터...
                    drawRectangle(g, startX + (sx + i) * blockWidth,
                            startY + (sy + j) * blockHeight, sType, blockWidth, blockHeight);
                }
            }
        }
    }

    private void drawNextBlock(Graphics g, Tetrominos block, int startX, int startY, int blockWidth, int blockHeight) {
        int i = 0, j = 0;
        int[][] ablock = block.getBlock();//테트리스 블럭의 모양을 저장한 배열
        int sw = block.getWidth();
        int sh = block.getHeight();
        int sx = block.getX();
        int sy = 0;
        int sType = block.getType();

        for (i = 0; i < sw; i++) {
            for (j = 0; j < sh; j++) {
                if (ablock[j][i] != Tetris.EMPTY) {////////이 부분을 수정했다. 위에서부터...
                    drawRectangle(g, startX + (sx + i) * blockWidth,
                            startY + (sy + j) * blockHeight, sType, blockWidth, blockHeight);
                }
            }
        }
    }//다음 블럭을 표시하기 위해 내가 그린 녀석

    private void drawShadowBlock(Graphics g, Tetrominos block, int startX, int startY, int blockWidth, int blockHeight) {
        int i = 0, j = 0;
        int[][] ablock = block.getBlock();
        int sw = block.getWidth();
        int sh = block.getHeight();
        int sx = block.getX();
        int sy = block.getY();
        int sType = block.getType();

        for (i = 0; i < sw; i++) {
            for (j = 0; j < sh; j++) {
                if (ablock[j][i] != Tetris.EMPTY) {
                    drawShadowRectangle(g, startX + (sx + i) * blockWidth,
                            startY + (sy + j) * blockHeight, sType, blockWidth, blockHeight);
                }
            }
        }
    }

    private void drawRectangle(Graphics g, int x, int y, int type, int blockWidth, int blockHeight)
    {
        Color colors[] = { new Color(90, 90, 90), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)//배경과 각 블록 색 8가지
        };

        Color color = colors[type];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, blockWidth - 2, blockHeight - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + blockHeight - 1, x, y);
        g.drawLine(x, y, x + blockWidth - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + blockHeight - 1,
                x + blockWidth - 1, y + blockHeight - 1);
        g.drawLine(x + blockWidth - 1, y + blockHeight - 1,
                x + blockWidth - 1, y + 1);
    }

    private void drawShadowRectangle(Graphics g, int x, int y, int type, int blockWidth, int blockHeight)
    {
        Color colors[] = { new Color(90, 90, 90), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[type];

        g.setColor(color);
        //g.fillRect(x + 1, y + 1, blockWidth - 2, blockHeight - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + blockHeight - 1, x, y);
        g.drawLine(x, y, x + blockWidth - 1, y);

        //g.setColor(color.darker());
        g.drawLine(x + 1, y + blockHeight - 1,
                x + blockWidth - 1, y + blockHeight - 1);
        g.drawLine(x + blockWidth - 1, y + blockHeight - 1,
                x + blockWidth - 1, y + 1);
    }

}
