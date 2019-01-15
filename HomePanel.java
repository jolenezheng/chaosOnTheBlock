
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * [HomePanel.java]
 * @author JoleneZheng
 * This class stores the contents for the home panel
 * This class extends the JPanel class and implements MouseListener
 * January 22nd, 2018
 */
public class HomePanel extends JPanel implements MouseListener{

	private BufferedImage homeBackground; //contains the home background image
	
	//constructor
	public HomePanel(HomeFrame homeFrame) {
		
		addMouseListener(this);
		
		//load in image
		try {
			
			homeBackground = ImageIO.read(new File("/Users/JoleneZheng/Codes/Grade 12 Computer Science/Final Project/HomePage.png"));
			
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
		g.drawImage(homeBackground, 0, 0, this); //draw on the board
		
		repaint(); //repaint panel
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		//retrieves the x and y coordinates of the user's click
		int xPos = e.getX();
		int yPos = e.getY();
				
		//if "play" is selected
		if (xPos > 198 && xPos < 422 && yPos > 150 && yPos < 416) {
			new EasyFrame();
		}
		 
		//if "instructions" is selected
		if (xPos > 198 && xPos < 410 && yPos > 450 && yPos < 534) {
			new InstructionsFrame();
		}
		
		//if "hard" is selected
		if (xPos > 20 && xPos < 150 && yPos > 540 && yPos < 680) {
			new EasyFrame();
		} else if (xPos > 250 && xPos < 430 && yPos > 520 && yPos < 680) { //if "medium" is selected
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


}
