import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * [InstructionsPanel.java]
 * @author JoleneZheng
 * This class stores the contents for the instructions panel
 * This class extends the JPanel class and implements MouseListener
 * January 22nd, 2018
 */
public class InstructionsPanel extends JPanel implements MouseListener {

	private BufferedImage instructionsBackground; //stores the instructions background image
	
	//constructor
	public InstructionsPanel(InstructionsFrame instructionsFrame) {

		addMouseListener(this);
		
		//load in instructions image
		try {
			
			instructionsBackground = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/InstructionsPage.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * paintComponenet
	 * contains GUI content to draw on image
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(instructionsBackground, 0, 0, this); //draw on the board
		
		repaint(); //repaint panel
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		//retrieves the click's x and y coordinates
		int xPos = e.getX();
		int yPos = e.getY();
		
		//if "play" is selected, start from the easy frame from default
		if (xPos > 438 && xPos < 556 && yPos > 570 && yPos < 632) {
			
			new	EasyFrame();
			
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}
	
	
} //end of class
