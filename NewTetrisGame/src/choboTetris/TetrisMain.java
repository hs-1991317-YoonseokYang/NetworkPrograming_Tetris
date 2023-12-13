package choboTetris;

import javax.swing.*;
import java.awt.*;

public class TetrisMain extends JFrame {
    JLabel statusbar;

    public TetrisMain() {
        statusbar = new JLabel("Press S to play game");
        statusbar.setFont(new Font(statusbar.getFont().getFontName(), Font.PLAIN, 18));//폰트사이즈만 변경한다.
        add(statusbar, BorderLayout.SOUTH);
        TetrisBoardGui tetrisBoardGui  = new TetrisBoardGui(this);//패런트라는 것은 저 보드가 이 jframe클래스에 붙는다는 뜻이겠지
        add(tetrisBoardGui);

        tetrisBoardGui.start();

        setSize(390, 630);
        setTitle("ChoboTetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JLabel getStatusBar() {
        return statusbar;
    }

    public static void main(String[] args) {
        TetrisMain tetrisMain = new TetrisMain();
        tetrisMain.setLocationRelativeTo(null);
        tetrisMain.setVisible(true);
    }
}