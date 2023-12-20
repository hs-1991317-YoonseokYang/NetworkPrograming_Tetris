package com.chobocho.player;



import com.chobocho.tetris.Tetris;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class SenderThread extends Thread {
   /***************************************************************/
    // 멤버 필드
    /* Socket socket;
    PrintWriter writer;
    String name;
    Tetris tetris;
    //MultiModel multiModel;
    // MultiView multiView;
    /***************************************************************/
    // SenderThread 생성자
    /*public SenderThread(Socket socket, String name, PrintWriter writer, Tetris tetris) {
        this.socket = socket;
        this.name = name;
        this.writer = writer;
        this.tetris= tetris;
    }
    /***************************************************************/
    // run 메소드
  /*  public void run() {
        try {
            writer.println(name);
            writer.flush();
            while(!multiModel.getIsOver()) {
                if (multiModel.getCheckSpace() == 0)
                    Thread.sleep(1000);
                else
                    Thread.sleep(10);
                //multiModel.checkBlockLast(multiModel.getNowBlock(), multiModel.getTurnNum());
                if(tetris.isPlayState()){
                    HashMap<String, Object> data = new HashMap<String, Object>();
                    data.put("name", name);//플레이어 네임인듯 ㅇㅇ
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
                    writer.println(data);
                    writer.flush();
                    //multiView.repaint();
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(name.equals("ClientA")) {
                writer.println("clientBwin");
                writer.flush();
            }
            else  {
                writer.println("clientAwin");
                writer.flush();
            }
            try {
                socket.close();
            }
            catch(Exception ignored) {

            }
        }
   } */
}

