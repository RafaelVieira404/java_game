package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int screenPanelOriginalSize = 16;
    final int scale = 3;
    public final int tileSize = screenPanelOriginalSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRol = 10;
    public final int screenWidth = maxScreenRol * tileSize;
    public final int screenHeight = maxScreenCol * tileSize;

    KeyBoardHandler keyH = new KeyBoardHandler();

    //set game fps
    int fps = 60;

    TileManager tileManager = new TileManager(this);
    Player player = new Player(this,keyH);
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenHeight,screenWidth));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThreat(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {

            double drawInterval = (double) 1000000000/fps;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            long timer =0;
            int drawCount= 0;

            while (gameThread != null){
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += currentTime - lastTime;
                lastTime = currentTime;

                if (delta >= 1){
                    update();
                    repaint();
                    delta -= 1;
                    drawCount++;
                }


                if (timer >= 1000000000){
                    System.out.println("FPS: " + drawCount);
                    drawCount =0;
                    timer = 0;

                }
            }
        }
    }

    public void update() {
    player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
