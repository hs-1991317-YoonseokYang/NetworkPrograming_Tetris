package choboTetris;

import com.chobocho.player.*;
import com.chobocho.tetris.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TetrisBoardGui extends JPanel implements ITetrisObserver {
    final int BOARD_WIDTH = 13;
    final int BOARD_HEIGHT = 20;

    IPlayer playerOne;
    IPlayerDraw playerOneDraw;
    IPlayerAction playerOneAction;

    JLabel statusbar;//이걸 이용하면 텍스트 바꿀 수 있다.

    private Image screenBuffer = null;
    private Graphics graphicsBuffer = null;


    public TetrisBoardGui(TetrisMain parent) {
        playerOneDraw = new PlayerOneDraw();
        playerOneAction = new PlayerOneAction();
        playerOne = new Player(this, playerOneDraw, playerOneAction);

        statusbar = parent.getStatusBar();
        setFocusable(true);
        addKeyListener(new TetrisKeyAdapter());
    }

    public void update() {
        System.out.println("Tetris (d) View.update()");
        repaint();
    }
    private int blockWidth() { return (int) getSize().getWidth() / BOARD_WIDTH; }
    private int blockHeight() { return (int) getSize().getHeight() / BOARD_HEIGHT; }


    public void start()
    {
        playerOne.init();
        statusbar.setText("Press S to start game!");
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Dimension size = getSize();//이 사이즈는 jFrame에 이녀석이 어떻게 부착되어있느냐에 따라 달라짐

        int width = (int)size.getWidth();
        int height = (int)size.getHeight();

        if (screenBuffer == null) {
            screenBuffer = createImage(width, height);
        }

        graphicsBuffer = screenBuffer.getGraphics();
        graphicsBuffer.setColor(Color.DARK_GRAY);
        graphicsBuffer.fillRect(0, 0, width, height);

        int boardY = (int) size.getHeight() - BOARD_HEIGHT * blockHeight();//이건 무슨 높이인가?..
        int boardX = (int) (size.getWidth() - BOARD_WIDTH * blockWidth())/2;

        playerOne.onDraw(graphicsBuffer, boardX, boardY, blockWidth(), blockHeight());//이 데이터만 보내면 되지 않나요??

        g.drawImage(screenBuffer, 0, 0, null);

        screenBuffer = null;
    }

    public void setStatusbar(String text){
        statusbar.setText(text);
    }

    class TetrisKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            playerOne.onPressKey(keycode);
        }
    }
}