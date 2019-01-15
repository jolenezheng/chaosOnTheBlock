import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * [HomeFrame.java]
 * @author JoleneZheng
 * This class stores the JFrame for the home panel
 * This class extends the JFrame class
 * January 22nd, 2018
 */
public class HomeFrame extends JFrame {
	
	HomeFrame homeFrame = this;
	
	//constructor
	public HomeFrame() {
		
		super("Chaos on the Block");

		setPreferredSize(new Dimension(608,690));
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    run(); //calls run method
		
	}
	
	/**
	 * run
	 * This method accesses the panel containing the easy level
	 */
	void run() {
		
		HomePanel homePanel = new HomePanel(this); //creates a new HomePanel object
	    
	    //add panel to frame
	    add(homePanel);   
	    pack();
	    
	    //display the frame
	    setVisible(true);
	    
	}
	
	
	


}
