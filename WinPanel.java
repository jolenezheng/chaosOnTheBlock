import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * [WinPanel.java]
 * @author JoleneZheng
 * This class stores the contents for the win panel
 * This class extends the JPanel class and implements MouseListener
 * January 22nd, 2018
 */
public class WinPanel extends JPanel implements MouseListener {
	
	private BufferedImage winBackground; //image storing the picture indicating the user has won
	
	//constructor
	public WinPanel(WinFrame winFrame) {

		addMouseListener(this);
		
		//load image into program
		try {
			
			winBackground = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/win.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} //end of constructor
	
	/**
	 * paintComponenet
	 * contains GUI content to draw on image
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(winBackground, 0, 0, this); //draw image on the board
		
		repaint(); //repaint panel
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		//retrieves the s and y coordinates of user's click
		int xPos = e.getX();
		int yPos = e.getY();
				
		if (xPos > 100 && xPos < 245 && yPos > 315 && yPos < 376) { //if "play again" was selected
			new EasyFrame();
		}
		
		if (xPos > 261 && xPos < 349 && yPos > 316 && yPos < 376) { //if "home" was selected
			new HomeFrame();
		}
		
		if (xPos > 365 && xPos < 510 && yPos > 315 && yPos < 376) { //if "next level" was selected
			new MediumFrame();
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
