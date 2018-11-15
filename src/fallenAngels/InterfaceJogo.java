package fallenAngels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class InterfaceJogo  extends JFrame implements ActionListener, KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 420;
    private static final String NAME = "Fallen Angels";
    private Jogador jogador1, jogador2;
    private int seta1, seta2;

    public void renderGameScreen(Jogador jogador1, Jogador jogador2, int seta1, int seta2){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.seta1 = seta1;
        this.seta2 = seta2;

        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setName(NAME);

        BufferedImage myImage = null;
        try {
            myImage = ImageIO.read(new URL("https://i.imgur.com/HGUdlW8.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentPane(new Background(myImage));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opcoes");
        menuBar.add(menu);

        JMenuItem item = new JMenuItem("Reiniciar");
        item.getAccessibleContext().setAccessibleDescription("Reinicie o jogo com os mesmos personagens");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                repaint();
            }
        });
        menu.add(item);

        setJMenuBar(menuBar);

        JLabel image = null;
        try {
            image = new JLabel(new ImageIcon(new URL(jogador1.url)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        image.setBounds(135, 215, 99, 128);
        add(image);

        JLabel image2 = null;
        try {
            image2 = new JLabel(new ImageIcon(new URL(jogador2.url)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        image2.setBounds(530, 230, 128, 128);
        add(image2);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_UP) trocarUp();
                else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) trocarDown();
                else if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) tratarEnter();
                else if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) trocarLeft();
            }
        });

        setVisible(true);
        repaint();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.red);
        g.drawString(jogador1.name,135,220);
        g.drawString("Vida: " + jogador1.ptsVida, 135, 237);
        g.drawString(jogador2.name, 530, 220);
        g.drawString("Vida: "+ jogador2.ptsVida,530,237);

        g.setColor(Color.BLACK);
        g.fillRect(135, 80, 100, 40);
        g.setColor(Color.WHITE);
        g.drawString("Atacar", 155, 93);
        g.drawString("Defender", 155, 110);

        switch (seta1){
            case 0:
                g.drawString("~>",135,93);
                break;
            case 1:
                g.drawString("~>",135,110);
                break;
            case 2:
                g.setColor(Color.BLACK);
                g.fillRect(245,80,150,60);
                g.setColor(Color.WHITE);
                g.drawString(jogador1.golpe1.name, 265, 93);
                g.drawString(jogador1.golpe2.name, 265, 110);
                g.drawString(jogador1.golpe3.name, 265, 127);
                switch (seta2){
                    case 1:
                        g.drawString("~>",245,93);
                        break;
                    case 2:
                        g.drawString("~>",245,110);
                        break;
                    case 3:
                        g.drawString("~>",245,127);
                        break;
                }
                break;
            case 3:
                g.setColor(Color.BLACK);
                g.fillRect(245,80,150,60);
                g.setColor(Color.WHITE);
                g.drawString(jogador1.heal1.name, 265, 93);
                g.drawString(jogador1.heal2.name, 265, 110);
                g.drawString(jogador1.heal3.name, 265, 127);
                switch (seta2){
                    case 1:
                        g.drawString("~>",245,93);
                        break;
                    case 2:
                        g.drawString("~>",245,110);
                        break;
                    case 3:
                        g.drawString("~>",245,127);
                        break;
                }
                break;
        }
    }

    private void trocarUp(){
        if(seta2 == 0){
            if(seta1 == 1){
                seta1 = 0;
                repaint();
            }
        }else if(seta2 > 1){
            seta2 -= 1;
            repaint();
        }
    }

    private void trocarDown(){
        if(seta2 == 0){
            if(seta1 == 0){
                seta1 = 1;
                repaint();
            }
        }else if(seta2 < 3){
            seta2 +=1;
            repaint();
        }
    }
    private void trocarLeft(){
        if(seta2 > 0){
            seta2 = 0;
            if(seta1 == 2) seta1 = 0;
            else if(seta1 == 3) seta1 = 1;
            repaint();
        }
    }

    private void tratarEnter(){
        if(seta2 == 0) {
            if(seta1 == 0) seta1 = 2;
            else if(seta1 == 1) seta1 = 3;
            seta2 += 1;
            repaint();
        }else{
            AtorJogador ator = new AtorJogador();
            ator.tratarLance(seta1, seta2);
            //processarJogada(seta1,seta2);
        }
    }

    public void notificarDano(double dano){
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Causou " + dano + " de dano!", "Erro", JOptionPane.ERROR_MESSAGE);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}