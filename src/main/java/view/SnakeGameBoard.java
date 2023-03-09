package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.BasicStroke;
import java.awt.Dimension;

public class SnakeGameBoard extends JPanel {
    private int rowNum=10;
    private int colNum=10;
    private int originX=10;
    private int originY=10;
    private int lineWidth=2;
    private int sideLength;

    SnakeGameBoard() {
        int horizontalTotalLineWidth = (colNum+1)*lineWidth;
        int verticalTotalLineWidth = (rowNum+1)*lineWidth;
        
        sideLength = (this.getWidth()-originX*2-horizontalTotalLineWidth)/colNum; 
    }

    private void drawBoardLine(Graphics2D g) {

        for(int i=0; i<=rowNum; i++) {
            g.drawLine(originX, originY+i*sideLength, originX+colNum*sideLength, originY+i*sideLength);
        }

        for(int j=0; j<=colNum; j++) {
            g.drawLine(originX+j*sideLength, originY, originX+j*sideLength, originY+rowNum*sideLength);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(lineWidth));
        drawBoardLine(g2d);
    }
}
