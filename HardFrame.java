import java.awt.Dimension;

import javax.swing.JFrame;


public class HardFrame extends JFrame{
	
	HardFrame hardFrame = this;
	//Level level;
	
	
	public HardFrame() { //(Level level)
		
		super("Hard");
		
		//this.level = level;
		
		setPreferredSize(new Dimension(608,690));
	    setResizable(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    run();
		
		
	}
	
	

	
	void run() {
		
		HardPanel hardPanel = new HardPanel(this); //(this, level)
				
		add(hardPanel);
		pack();
		
		setVisible(true);

	    
	}

}
