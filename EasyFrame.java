import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * [EasyFrame.java]
 * @author JoleneZheng
 * This class stores the JFrame for the easy level
 * This class extends the JFrame class
 * January 22nd, 2018
 */
public class EasyFrame extends JFrame {
	
	EasyFrame easyFrame = this;	
	
	//constructor
	public EasyFrame() {
		
		super("Easy");
				
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
		
		EasyPanel easyPanel = new EasyPanel(this); //create a new EasyPanel object
				
		add(easyPanel); //add panel to frame
		pack();
		
		setVisible(true); //set it to be visible

	    
	}
	

}
