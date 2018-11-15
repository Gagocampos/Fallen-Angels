package fallenAngels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class InterfaceInicial extends Arena implements ActionListener{

    public void screenRender(boolean conectado){
        final int WIDTH = 600;
        final int HEIGHT = WIDTH/12*9;
        final String NAME = "Fallen Angels";

        JFrame frame = new JFrame(NAME);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        BufferedImage myImage = null;
        try {
            myImage = ImageIO.read(new URL("https://i.imgur.com/ZIQjPnw.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setContentPane(new Background(myImage));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Conexao");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Conecte-se ao NetGames");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Conectar", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Estabelecer conexão");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(conectado == false) {
                    frame.dispose();
                    obterServidor();
                }
                else{
                    notificarJaConectado();
                }
            }
        });
        menu.add(menuItem);

        Container c = frame.getContentPane();
        c.setLayout(null);

        Color brown = new Color(45,47,31);

        JButton jogar = new JButton("Jogar");
        jogar.setBounds(262,275,75,25);
        jogar.setBackground(brown);
        jogar.setForeground(new Color(166,177,186));
        jogar.setFocusPainted(false);
        jogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(conectado == true){
                    frame.dispose();
                    iniciarPartida();
                }else{
                    notificarNaoConectado();
                }
            }
        });
        c.add(jogar);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

    }

    public void obterServidor(){
        JFrame server = new JFrame("Informe o servidor");
        server.setSize(300, 150);
        server.setLocationRelativeTo(null);
        server.setLayout(new FlowLayout());
        server.setVisible(true);

        JLabel label = new JLabel();
        label.setText("Informe o servidor:");
        server.getContentPane().add(label);

        JTextField texto = new JTextField(25);
        texto.setVisible(true);
        server.getContentPane().add(texto);

        JButton confirma = new JButton("Ok");
        confirma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                server.dispose();
                obterId(texto.getText());
            }
        });
        server.getContentPane().add(confirma);
    }

    public void obterId(String servidor){
        JFrame nome = new JFrame("Informe seu nome");
        nome.setLocation(540,300);
        nome.setSize(300, 150);
        nome.setLayout(new FlowLayout());
        nome.setVisible(true);

        JLabel label = new JLabel();
        label.setText("Informe seu nome:");
        nome.add(label);

        JTextField texto = new JTextField(25);
        nome.add(texto);

        JButton confirma = new JButton("Ok");
        confirma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                obterPersonagem(servidor, texto.getText());
                nome.dispose();
            }
        });
        nome.getContentPane().add(confirma);
    }

    private void obterPersonagem(String servidor, String identity) {
        JFrame frame = new JFrame("Selecione o personagem");
        frame.setLocation(475, 185);
        frame.setSize(400, 310);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());

        AtorJogador ator = new AtorJogador();

        JLabel demon = null;
        try {
            demon = new JLabel(new ImageIcon(new URL("https://i.imgur.com/2FB1LnO.png")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }frame.add(demon);

        JButton demonButton = new JButton("Ok");
        demonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                ator.conectar(servidor, identity, 0);
            }
        });
        frame.add(demonButton);

        JLabel wolf = null;
        try{
            wolf = new JLabel(new ImageIcon(new URL("https://i.imgur.com/asIIA77.png")));
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        frame.add(wolf);

        JButton wolfButton = new JButton("Ok");
        wolfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                ator.conectar(servidor, identity, 1);
            }
        });
        frame.add(wolfButton);

        JLabel guardian = null;
        try{
            guardian = new JLabel(new ImageIcon(new URL("https://i.imgur.com/HmQPDgb.png")));
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        frame.add(guardian);

        JButton guardianButton = new JButton("Ok");
        guardianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                ator.conectar(servidor, identity, 2);
            }
        });
        frame.add(guardianButton);

        JLabel taurus = null;
        try{
            taurus = new JLabel(new ImageIcon(new URL("https://i.imgur.com/a6BczbN.png")));
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        frame.add(taurus);

        JButton taurusButton = new JButton("Ok");
        taurusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                ator.conectar(servidor, identity, 3);
            }
        });
        frame.add(taurusButton);
    }

    public void notificarJaConectado(){
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Você já está conectado!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    public void notificarNaoConectado(){
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Você não está conectado!", "Erro", JOptionPane.ERROR_MESSAGE);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
