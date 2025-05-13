package finalproj;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.util.Random;
import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileHandler;

public class GamePanel extends JPanel implements Runnable {
    final int orig_tile_size = 16;
    final int scale = 3;

    public final int tile_size = orig_tile_size * scale;
    public final int max_screen_row = 19;
    public final int max_screen_col = 21;
    final int screen_height = tile_size * max_screen_row;
    final int screen_width = tile_size * max_screen_col;

    TileHandler tile_handler = new TileHandler(this);
    KeyHandler key_handler = new KeyHandler();
    public CollisionChecker collision_checker = new CollisionChecker(this);
    public AssetSetter asset_setter = new AssetSetter(this);
    Thread gameThread;
    Player player1 = new Player(this, key_handler);

    public SuperObject obj[] = new SuperObject[20];

    int player_speed = 4; // Default player speed
    int original_speed = player_speed; // Store original speed

    int FPS = 60;

    long start_time;
    public long seconds_display;

    // Disaster variables
    private long lastDisasterTime = 0;
    private final long disasterIntervalMin = 10000; // Minimum disaster interval 10 seconds
    private final long disasterIntervalMax = 20000; // Maximum disaster interval 20 seconds
    private long disasterInterval = disasterIntervalMin; // Current interval
    private NaturalDisaster currentDisaster = null; // Current disaster
    private long disasterStartTime = 0; // When disaster started
    private boolean disasterActive = false; // If disaster active

    // For rain animation
    private final int maxRaindrops = 300;
    private int[] rainX = new int[maxRaindrops];
    private int[] rainY = new int[maxRaindrops];
    private int[] rainSpeedArr = new int[maxRaindrops];  // Individual raindrop speeds
    private int rainSpeed = 20;
    private Random random = new Random();


    // For earthquake shaking
    private int shakeOffsetX = 0;
    private int shakeOffsetY = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key_handler);
        this.setFocusable(true);

        // Initialize raindrop positions and speeds
        for (int i = 0; i < maxRaindrops; i++) {
            rainX[i] = random.nextInt(screen_width);
            rainY[i] = -random.nextInt(screen_height); // Start above screen randomly
            rainSpeedArr[i] = rainSpeed - 5 + random.nextInt(11); // Speed between 15-25
            if (rainSpeedArr[i] < 5) rainSpeedArr[i] = 5; // Minimum speed
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setUpGame() {
        asset_setter.setObject();
        lastDisasterTime = System.currentTimeMillis();
        // Random interval for first disaster
        disasterInterval = disasterIntervalMin + (long) (Math.random() * (disasterIntervalMax - disasterIntervalMin));
    }

    // MAIN GAME
    @Override
    public void run() {
        double draw_interval = 1000000000 / FPS;
        double next_draw_time = System.nanoTime() + draw_interval;
        start_time = System.currentTimeMillis();
        while (gameThread != null) {
        	long elapsed_time = System.currentTimeMillis() - start_time;
        	long elapsed_seconds = elapsed_time / 1000;
        	seconds_display = elapsed_seconds % 999999999;
            long currentTime = System.currentTimeMillis();

            // Trigger disaster if time for next disaster reached and no active disaster
            if (!disasterActive && currentTime - lastDisasterTime >= disasterInterval) {
                triggerDisaster();
                lastDisasterTime = currentTime;
                disasterActive = true;
                disasterStartTime = currentTime;
            }

            if (disasterActive) {
                long disasterDuration = getDisasterDuration(currentDisaster);
                if (currentTime - disasterStartTime >= disasterDuration) {
                    endDisaster();
                } else if (currentDisaster == NaturalDisaster.EARTHQUAKE) {
                    // Earthquake shake offsets randomly updated every frame during quake
                    shakeOffsetX = random.nextInt(7) - 3; // -3 to +3 pixels
                    shakeOffsetY = random.nextInt(7) - 3;
                } else {
                    shakeOffsetX = 0;
                    shakeOffsetY = 0;
                }
            } else {
                shakeOffsetX = 0;
                shakeOffsetY = 0;
            }

            update();
            repaint();

            try {
                double remaining_time = next_draw_time - System.nanoTime();
                remaining_time = remaining_time / 1000000;
                if (remaining_time < 0) remaining_time = 0;
                Thread.sleep((long) remaining_time);
                next_draw_time += draw_interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (disasterActive) {
            switch (currentDisaster) {
                case THUNDERSTORM:
                    player_speed = original_speed * 4; // speed quadrupled
                    updateRain();
                    break;
                case BLACKOUT:
                    player_speed = original_speed; // normal speed
                    break;
                case EARTHQUAKE:
                    player_speed = original_speed;
                    break;
                default:
                    player_speed = original_speed;
                    break;
            }
        } else {
            player_speed = original_speed;
        }
        player1.update();
    }

    private void updateRain() {
        for (int i = 0; i < maxRaindrops; i++) {
            rainY[i] += rainSpeedArr[i]; // Use individual speed for each raindrop

            // If the raindrop goes below the screen, reset its position
            if (rainY[i] > screen_height) {
                rainX[i] = random.nextInt(screen_width); // Random X position
                rainY[i] = -random.nextInt(screen_height); // Respawn above the screen randomly
                rainSpeedArr[i] = rainSpeed - 5 + random.nextInt(11); // Reassign speed on reset
                if (rainSpeedArr[i] < 5) rainSpeedArr[i] = 5; // Ensure minimum speed
            }
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Apply earthquake shake offsets to all drawings
        g2.translate(shakeOffsetX, shakeOffsetY);

        if (currentDisaster == NaturalDisaster.BLACKOUT && disasterActive) {
            // Blackout: fill screen black except player (drawn later)
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, screen_width, screen_height);
        } else {
            // Draw tiles and objects normally
            tile_handler.draw(g2);
            for (SuperObject obj : obj) {
                if (obj != null) obj.draw(g2, this);
            }
        }

        // Draw visual overlays for disasters
        if (disasterActive && currentDisaster != null) {
            switch (currentDisaster) {
                case THUNDERSTORM:
                    drawThunderstormEffect(g2);
                    break;
                case EARTHQUAKE:
                    drawEarthquakeEffect(g2);
                    break;
                case BLACKOUT:
                    // no overlay, screen black except player
                    break;
            }
        }

        // Draw player on top
        player1.draw(g2);

        // Reset translation
        g2.translate(-shakeOffsetX, -shakeOffsetY);

        g2.dispose();
    }

    private void drawThunderstormEffect(Graphics2D g2) {
        // Bluish transparent overlay for blur effect
        g2.setColor(new Color(0, 50, 100, 120));
        g2.fillRect(0, 0, screen_width, screen_height);

        // Raindrops as light blue lines
        g2.setColor(new Color(173, 216, 230, 180));
        g2.setStroke(new BasicStroke(2));
        for (int i = 0; i < maxRaindrops; i++) {
            int x = rainX[i];
            int y = rainY[i];

            g2.drawLine(x, y, x, y + 10);
        }
        g2.setStroke(new BasicStroke(1));
    }

    private void drawEarthquakeEffect(Graphics2D g2) {
        // Gray transparent overlay for blur
        g2.setColor(new Color(100, 100, 100, 100));
        g2.fillRect(0, 0, screen_width, screen_height);

        // Draw gray rocks randomly scattered
        g2.setColor(new Color(50, 50, 50, 180));
        for (int i = 0; i < 20; i++) {
            int size = 10 + random.nextInt(10);
            int x = random.nextInt(screen_width - size);
            int y = random.nextInt(screen_height - size);
            g2.fillOval(x, y, size, size);
        }
    }

    public void triggerDisaster() {
        NaturalDisaster[] disasters = NaturalDisaster.values();
        currentDisaster = disasters[random.nextInt(disasters.length)];

        disasterActive = true;
        disasterStartTime = System.currentTimeMillis();

        System.out.println("Disaster started: " + currentDisaster);

        if (currentDisaster == NaturalDisaster.THUNDERSTORM) {
            // Reset raindrops for fresh start
            for (int i = 0; i < maxRaindrops; i++) {
                rainX[i] = random.nextInt(screen_width);
                rainY[i] = -random.nextInt(screen_height);
                rainSpeedArr[i] = rainSpeed - 5 + random.nextInt(11);
                if (rainSpeedArr[i] < 5) rainSpeedArr[i] = 5;
            }
        }
    }

    public long getDisasterDuration(NaturalDisaster disaster) {
        switch (disaster) {
            case THUNDERSTORM:
                return 5000; // 5 seconds
            case BLACKOUT:
                return 10000; // 10 seconds
            case EARTHQUAKE:
                return 3000; // 3 seconds
            default:
                return 0;
        }
    }

    public void endDisaster() {
        System.out.println("Disaster ended: " + currentDisaster);
        currentDisaster = null;
        disasterActive = false;
        player_speed = original_speed;

        // Randomize next disaster interval
        disasterInterval = disasterIntervalMin + (long) (Math.random() * (disasterIntervalMax - disasterIntervalMin));
        lastDisasterTime = System.currentTimeMillis();

        shakeOffsetX = 0;
        shakeOffsetY = 0;
    }

    // Check if player is disoriented (Earthquake)
    public boolean isPlayerDisoriented() {
        return disasterActive && currentDisaster == NaturalDisaster.EARTHQUAKE;
    }

    // Get current player's speed
    public int getPlayerSpeed() {
        return player_speed;
    }

    // Enum for disasters
    public enum NaturalDisaster {
        THUNDERSTORM,
        BLACKOUT,
        EARTHQUAKE
    }
}

