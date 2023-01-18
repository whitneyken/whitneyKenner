import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PacmanApp {

    static final int BlockSize = 50;
    static final int CornerSize = 10;
    static final int DotRadius = 7;
    public static void main(String[] args) throws IOException {

        var mazeFile = "mediumMazeSol.txt";
        var maze = readMaze(mazeFile);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(maze);
            }
        });
    }

    private static void createAndShowGUI(char[][] maze) {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Pacman");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(maze[0].length*BlockSize,maze.length*BlockSize);
        f.add(new GamePanel(maze));
        f.pack();
        f.setVisible(true);
    }


    private static class GamePanel extends JPanel{
        private char[][] maze;

        int pacmanRow, pacmanCol;

        final int framesPerSquare = 20;
        int steppedFrames = 0;
        int targetRow, targetCol;

        Timer timer;
        public GamePanel(char[][] maze) {
            this.maze = Arrays.stream(maze).map(char[]::clone).toArray(char[][]::new);
            for(int row = 0; row < maze.length; row++){
                for(int col = 0; col < maze[0].length; col++)
                {
                    if(maze[row][col] == 'S'){
                        pacmanRow = row;
                        pacmanCol = col;
                    }
                }
            }
            System.out.println(mazeToString(this.maze));
            setBorder(BorderFactory.createLineBorder(Color.black));

            updateDirection();

            timer = new Timer(16, (ActionEvent e) ->{
                animate();
            });
            timer.start();
        }

        private void updateDirection() {
            if(maze[pacmanRow -1][pacmanCol] == '.' || maze[pacmanRow -1][pacmanCol] == 'G'){
                targetRow = pacmanRow - 1;
                targetCol = pacmanCol;
            } else if(maze[pacmanRow  + 1][pacmanCol] == '.' || maze[pacmanRow  + 1][pacmanCol] == 'G'){
                targetRow = pacmanRow + 1;
                targetCol = pacmanCol;
            } else if(maze[pacmanRow][pacmanCol - 1] == '.' || maze[pacmanRow][pacmanCol - 1] == 'G'){
                targetRow = pacmanRow;
                targetCol = pacmanCol - 1;
            } else if(maze[pacmanRow][pacmanCol + 1] == '.' || maze[pacmanRow][pacmanCol + 1] == 'G'){
                targetRow = pacmanRow;
                targetCol = pacmanCol + 1;
            } else {
                targetCol = pacmanCol;
                targetRow = pacmanRow;
            }
        }

        private void animate() {
            ++steppedFrames;
            if(steppedFrames == framesPerSquare/2){
                maze[targetRow][targetCol] = 'o'; //been there, done that
            }
            if(steppedFrames == framesPerSquare){
                pacmanCol = targetCol;
                pacmanRow = targetRow;
                steppedFrames = 0;
                updateDirection();
            }
            repaint();
        }

        public Dimension getPreferredSize() {
            return new Dimension(maze[0].length*BlockSize,maze.length*BlockSize);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g;
            // Draw Text
            g2.setColor(Color.BLACK);
            g2.fillRect(0,0,getWidth(),getHeight());



            for(var row = 0; row < maze.length; row++){
                for(var col = 0; col < maze[0].length; col++){
                    //draw the wall cells
                    if(maze[row][col] == 'X'){
                        g2.setColor(Color.BLUE);
                        g2.setStroke(new BasicStroke(4));
                        boolean wallDown = (row != maze.length -1) && maze[row + 1][col] == 'X';
                        boolean wallUp = row != 0 && maze[row -1][col] == 'X';
                        boolean wallLeft = col != 0 && maze[row][col -1] == 'X';
                        boolean wallRight = col != (maze[0].length -1) && maze[row][col +1] == 'X';

                        boolean wallUpLeft = col != 0 && row != 0 && maze[row - 1][col -1] == 'X';
                        boolean wallDownLeft = col != 0 && row != (maze.length -1) && maze[row + 1][col -1] == 'X';

                        boolean wallUpRight = col != maze[0].length -1 && row != 0 && maze[row -1][col + 1] == 'X';
                        boolean wallDownRight = col != maze[0].length -1 && row != maze.length -1 && maze[row + 1][col + 1] == 'X';
                        if(!wallLeft){
                            g2.drawLine(col * BlockSize, row*BlockSize + CornerSize,
                                    col*BlockSize, (row + 1)*BlockSize - CornerSize);



                            if(!wallUp){ //draw the top left corner
                                g2.drawArc(col*BlockSize, row*BlockSize,CornerSize, CornerSize, 90, 90);
                            } else if(!wallUpLeft){ //check diagonally up + left
                                g2.drawLine(col*BlockSize, row*BlockSize,
                                        col*BlockSize, row*BlockSize + CornerSize);
                            } else { //there is a wall up to the left, so we need to draw our "outgoing" corner edge
                                g2.drawArc(col*BlockSize - CornerSize, row*BlockSize, CornerSize, CornerSize,0, 90);
                            }

                            if(!wallDown){ //draw bottom right corner
                                g2.drawArc(col*BlockSize, (row + 1)*BlockSize - CornerSize,CornerSize, CornerSize, 180, 90);
                            } else if(!wallDownLeft) {
                                g2.drawLine(col*BlockSize, (row + 1)*BlockSize - CornerSize,
                                        col*BlockSize, (row + 1)*BlockSize);
                            } else { //there is a wall down to the left, so we need to draw the "outgoing" corner edge
                                g2.drawArc(col*BlockSize - CornerSize, (row + 1)*BlockSize - CornerSize, CornerSize, CornerSize,0, -90);
                            }

                        }

                        if(!wallRight){
                            g2.drawLine((col +1)*BlockSize, row*BlockSize + CornerSize,
                                    (col + 1)*BlockSize, (row + 1)*BlockSize - CornerSize);

                            if(!wallUp){ //draw the top left corner
                                g2.drawArc((col + 1)*BlockSize - CornerSize, row*BlockSize,CornerSize, CornerSize, 90, -90);
                            } else if(!wallUpRight){ //check diagonally up + left
                                g2.drawLine((col + 1)*BlockSize, row*BlockSize,
                                        (col + 1)*BlockSize, row*BlockSize + CornerSize);
                            } else { //draw the "outgoing corner"
                                g2.drawArc((col + 1)*BlockSize, row*BlockSize, CornerSize, CornerSize, 90, 90);
                            }

                            if(!wallDown){ //draw bottom right corner
                                g2.drawArc((col + 1)*BlockSize - CornerSize, (row + 1)*BlockSize - CornerSize,CornerSize, CornerSize, 0, -90);
                            } else if(!wallDownRight) {
                                g2.drawLine((col + 1)*BlockSize, (row + 1)*BlockSize - CornerSize,
                                        (col + 1)*BlockSize, (row + 1)*BlockSize);
                            } else {
                                g2.drawArc((col + 1)*BlockSize, (row + 1)*BlockSize - CornerSize, CornerSize, CornerSize, 180, 90);
                            }

                        }

                        if(!wallUp){
                            int startX = wallLeft && !wallUpLeft ? col*BlockSize : col*BlockSize + CornerSize;
                            int stopX = wallRight && !wallUpRight ? (col + 1)*BlockSize : (col + 1)*BlockSize - CornerSize;
                            g2.drawLine(startX, row*BlockSize, stopX, row*BlockSize);
                        }
                        if(!wallDown){
                            int startX = wallLeft && !wallDownLeft ? col*BlockSize : col*BlockSize + CornerSize;
                            int stopX = wallRight && !wallDownRight ? (col + 1)*BlockSize : (col + 1)*BlockSize - CornerSize;
                            g2.drawLine(startX, (row + 1)*BlockSize, stopX, (row + 1)*BlockSize);
                        }
                    }
                    else if(maze[row][col] == ' ' || maze[row][col] == '.'){
                        g2.setColor(Color.WHITE);
                        int centerX = (int)((col + 0.5)*BlockSize);
                        int centerY = (int)((row + 0.5)*BlockSize);
                        g2.fillOval(centerX - DotRadius, centerY - DotRadius, 2*DotRadius, 2*DotRadius );
                    } else if(maze[row][col] == 'G'){
                        g2.setColor(Color.RED);
                        int centerX = (int)((col + 0.5)*BlockSize);
                        int centerY = (int)((row + 0.5)*BlockSize);
                        g2.fillOval(centerX - 20, centerY, 20, 20);
                        g2.fillOval(centerX - 10, centerY, 20, 20);
                        g2.setColor(Color.YELLOW);
                        g2.setStroke(new BasicStroke(4));
                        g2.drawLine(centerX, centerY, centerX + 7, centerY - 7);

                    }
                }
            }

            //
            g2.setColor(Color.YELLOW);
            double phase = (double)steppedFrames/framesPerSquare;
            int centerX = (int)((pacmanCol + 0.5 + (targetCol - pacmanCol)*phase)*BlockSize) ;
            int centerY = (int)((pacmanRow + 0.5 + (targetRow - pacmanRow)*phase)*BlockSize);
            final int pacmanRadius = 20;
            g2.fillOval(centerX - pacmanRadius, centerY - pacmanRadius, 2*pacmanRadius, 2*pacmanRadius );
            g2.setColor(Color.BLACK);
            int angleOffset = targetCol >= pacmanCol ? 0 : 180; //facing right/left?
            g2.fillArc(centerX - pacmanRadius, centerY - pacmanRadius, 2*pacmanRadius + 3, 2*pacmanRadius + 3,
                    angleOffset + (int)(30*phase), (int)(-60*phase));


        }
    }

    public static char[][] readMaze(String inputFilename) throws IOException {
        Supplier<Stream<String>> lines = () -> {
            try {
                return Files.lines(Paths.get(inputFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        int dims[] = Arrays.stream(lines.get().findFirst().get().split(" ")).mapToInt(Integer::parseInt).toArray();
        return lines.get().skip(1).map(String::toCharArray).toArray(size -> new char[dims[0]][dims[1]]);
    }

    public static String mazeToString(char[][] maze){
        var height = maze.length;
        var width = maze[0].length;
        return width + " " + height + "\n" + Arrays.stream(maze).map(String::new).collect(Collectors.joining("\n"));
    }
}

