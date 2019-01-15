import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * [WinFrame.java]
 * @author JoleneZheng
 * This class stores the JFrame for the win panel
 * This class extends the JFrame class
 * January 22nd, 2018
 */
public class WinFrame extends JFrame {

	WinFrame winFrame = this;

	//constructor
	public WinFrame() {

		super("You Win");

		setPreferredSize(new Dimension(608,435));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		run(); //call run method

	}

	/**
	 * run
	 * This method accesses the panel containing the easy level
	 */
	void run() {

		WinPanel winPanel = new WinPanel(this); //create a new WinPanel object

		//add panel to frame
		add(winPanel);   
		pack();

		//display the frame
		setVisible(true);

	}

}
