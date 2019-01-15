import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * [MediumFrame.java]
 * @author JoleneZheng
 * This class stores the JFrame for the medium level
 * This class extends the JFrame class
 * January 22nd, 2018
 */
public class MediumFrame extends JFrame {
	
	MediumFrame mediumFrame = this; //medium frame object	
	
	//constructor
	public MediumFrame() {
		
		super("Medium");
				
		setPreferredSize(new Dimension(608,690));
	    setResizable(false); //sets frame to a default size
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    run(); //calls run method
		
	}
	
	/**
	 * run
	 * This method accesses the panel containing the medium level
	 */
	void run() {
		
		MediumPanel mediumPanel = new MediumPanel(this); //create a new panel object
				
		add(mediumPanel); //add panel to frame
		pack();
		
		setVisible(true);

	    
	}

}
