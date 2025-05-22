package finalproj;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuWindow extends JFrame {

    public static final int CELL_SIZE = 48; // Size of 1 cell
    public static final int SCREEN_WIDTH = 21 * CELL_SIZE;  // 21 columns
    public static final int SCREEN_HEIGHT = 19 * CELL_SIZE; // 19 rows

    public MenuWindow() {
        setTitle("Elbi Express");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        MenuPanel menuPanel = new MenuPanel(this);
        setContentPane(menuPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(getClass().getResourceAsStream("/scenes/" + imagePath));
        } catch (IOException | NullPointerException e) {
            System.err.println("Failed to load image: /scenes/" + imagePath);
            e.printStackTrace();
            return null;
        }
    }

    private class MenuPanel extends JPanel {
        private final MenuWindow parent;
        private final Image backgroundImage;

        private final JButton btnNewGame;
        private final JButton btnAbout;
        private final JButton btnDevelopers;

        public MenuPanel(MenuWindow parent) {
            this.parent = parent;

            setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            setLayout(null);
            setFocusable(true);

            backgroundImage = loadImage("menu.png");

            int btnWidth = 200;
            int btnHeight = 50;
            int centerX = (SCREEN_WIDTH - btnWidth) / 2;
            int startY = 550;
            int spacingY = 70;

            btnNewGame = createButton("S1.png", centerX, startY, btnWidth, btnHeight);
            btnAbout = createButton("A1.png", centerX, startY + spacingY, btnWidth, btnHeight);
            btnDevelopers = createButton("D1.png", centerX, startY + 2 * spacingY, btnWidth, btnHeight);

            btnNewGame.addActionListener(e -> {
                parent.dispose();
                Main mainGame = new Main();
                mainGame.launchGameWindow();
            });

            btnAbout.addActionListener(e -> {
                parent.setContentPane(new ImageInfoPanel(parent, "about.png"));
                parent.revalidate();
                parent.repaint();
            });

            btnDevelopers.addActionListener(e -> {
                parent.setContentPane(new ImageInfoPanel(parent, "devs.png"));
                parent.revalidate();
                parent.repaint();
            });

            add(btnNewGame);
            add(btnAbout);
            add(btnDevelopers);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }

        private JButton createButton(String imagePath, int x, int y, int width, int height) {
            JButton button = new JButton();
            button.setBounds(x, y, width, height);

            Image img = loadImage(imagePath);
            if (img != null) {
                img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(img));
            }

            button.setText(null);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);

            return button;
        }
    }

    private class ImageInfoPanel extends JPanel {
        private final JButton backButton;
        private final JLabel imageLabel;

        public ImageInfoPanel(MenuWindow parent, String imagePath) {
            setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            setLayout(null);
            setBackground(Color.BLACK);

            BufferedImage img = loadImage(imagePath);
            if (img != null) {
                Image scaledImg = img.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_SMOOTH);
                imageLabel = new JLabel(new ImageIcon(scaledImg));
                imageLabel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
                add(imageLabel);
            } else {
                imageLabel = new JLabel("Image not found");
                imageLabel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
                imageLabel.setForeground(Color.RED);
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                add(imageLabel);
            }

            backButton = new JButton();
            backButton.setBounds(SCREEN_WIDTH - 70, 20, 50, 50);
            backButton.setFocusPainted(false);
            backButton.setBorderPainted(false);
            backButton.setContentAreaFilled(false);
            backButton.setOpaque(false);

            BufferedImage backImg = loadImage("x.png");
            if (backImg != null) {
                backButton.setIcon(new ImageIcon(backImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            }

            backButton.addActionListener(e -> {
                parent.setContentPane(new MenuPanel(parent));
                parent.revalidate();
                parent.repaint();
            });

            add(backButton);

            setComponentZOrder(backButton, 0);
            setComponentZOrder(imageLabel, 1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuWindow::new);
    }
}
