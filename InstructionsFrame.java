import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * [InstructionsFrame.java]
 * @author JoleneZheng
 * This class stores the JFrame for the instructions panel
 * This class extends the JFrame class
 * January 22nd, 2018
 */
public class InstructionsFrame extends JFrame {
	
	InstructionsFrame instructionsFrame = this;
	
	//constructor
	public InstructionsFrame() {
		
		super("Instructions");

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
		
		InstructionsPanel instructionsPanel = new InstructionsPanel(this); //create a new InstructionsPanel object
	    
	    //add panel to frame
	    add(instructionsPanel);   
	    pack();
	    
	    //display the frame
	    setVisible(true);
	    
	}

}
