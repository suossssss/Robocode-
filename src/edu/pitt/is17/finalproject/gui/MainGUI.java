package edu.pitt.is17.finalproject.gui;
/**
 * @author liyun suo
 * 
 * This class is used to build MainGui frame and button to manage the properties of robots 
 * and run the battle.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;
import robocode.control.events.BattleMessageEvent;

public class MainGUI {
	private JFrame mainFrame; // Frame
	// Label for robot1
	private JLabel MyRobot1; 
	private JLabel bulletEnergy1;
	private JLabel stepDistance1;
	private JLabel angleR1;
	private JLabel angleL1;
	// Label for robot2
	private JLabel MyRobot2; 
	private JLabel bulletEnergy2;
	private JLabel stepDistance2;
	private JLabel angleR2;
	private JLabel angleL2;
	
	private JLabel vs;
	
	private JButton Save1; // Button for robot1
	private JButton Save2; // Button for robot2
	private JButton RunBattle; // Button for start
	
	//textfield for robot1
	private JTextField BE1;
	private JTextField SD1;
	private JTextField AR1;
	private JTextField AL1;
	//textfield for robot2
	private JTextField BE2;
	private JTextField SD2;
	private JTextField AR2;
	private JTextField AL2;	
	//slider for robot1
	private JSlider R1;
	private JSlider R2;
	private JSlider R3;
	private JSlider R4;
	//slider for robot2
	private JSlider R5;
	private JSlider R6;
	private JSlider R7;
	private JSlider R8;
    // interger variables which are loaded from properties
	private int BE1Text;
	private int SD1Text;
	private int AR1Text;
	private int AL1Text;
	private int BE2Text;
	private int SD2Text;
	private int AR2Text;
	private int AL2Text;
	
	private JPanel panel1;//panel for robot1
	private JPanel panel2;//panel for robot2
	
	//file path of loading properties
	String filePath1="/Users/suoliyun/Documents/workspace/lis86_FinalProject/config/MyRobot1.properties";//path
	String filePath2="/Users/suoliyun/Documents/workspace/lis86_FinalProject/config/MyRobot2.properties";
	//install path of robocode
	public static final String ROBOCODE_INSTALL_DIR = "/Users/suoliyun/Documents/robocode";
	public static final String ROBOT_1_NAME = "edu.pitt.is17.finalproject.robots.MyRobot1"; 
	public static final String ROBOT_2_NAME = "edu.pitt.is17.finalproject.robots.MyRobot2";
	
	
	public MainGUI(){
		
 		prepareGUI();
		
	}//constructor


	public static void main(String[] args){
		MainGUI frame= new MainGUI();//create a object to build frame
	
	}
	
	private void prepareGUI(){
		
		//Build a frame
		mainFrame=new JFrame("Robot Editor GUI");
		mainFrame.setSize(600,600);
		mainFrame.setLayout(null);
		
		//build panel1  for robot1 then add labels to panel1
		MyRobot1=new JLabel("MyRobot1",JLabel.CENTER);
		MyRobot1.setBounds(10, 10, 150, 30); 
		
		panel1=new JPanel();
	    panel1.setLayout(null);
	    panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
	    panel1.setBounds(5,50,250,250);
	    mainFrame.add(panel1);
		
		bulletEnergy1=new JLabel("Bullet Energy",JLabel.LEFT);
		bulletEnergy1.setBounds(10, 10, 150, 30); 
		panel1.add(bulletEnergy1);
		
		stepDistance1=new JLabel("Step Distance",JLabel.LEFT);
		stepDistance1.setBounds(10, 70, 150, 30); 
		panel1.add(stepDistance1);
		
		angleR1=new JLabel("Rat.AngleR",JLabel.LEFT);
		angleR1.setBounds(10, 130, 150, 30); 
		panel1.add(angleR1);
		
		
		angleL1=new JLabel("Rat.AngleL",JLabel.LEFT);
		angleL1.setBounds(10, 200, 150, 30);
		panel1.add(angleL1);
		 
		//build panel2for robot2 then add label to panel2
		MyRobot2=new JLabel("MyRobot2",JLabel.CENTER);
		MyRobot2.setBounds(350, 10, 150, 30);
		 
		panel2=new JPanel();
	    panel2.setLayout(null);
	    panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
	    panel2.setBounds(350,50,250,250);
	    mainFrame.add(panel2);
		
		bulletEnergy2=new JLabel("Bullet Energy",JLabel.LEFT);
		bulletEnergy2.setBounds(10, 10, 150, 30); 
		panel2.add(bulletEnergy2);
		
		stepDistance2=new JLabel("Step Distance",JLabel.LEFT);
		stepDistance2.setBounds(10,70, 150, 30); 
		panel2.add(stepDistance2);
		
		angleR2=new JLabel("Rat.AngleR",JLabel.LEFT);
		angleR2.setBounds(10,130, 150, 30); 
		panel2.add(angleR2);
		
		angleL2=new JLabel("Rat.AngleL",JLabel.LEFT);
		angleL2.setBounds(10,200, 150, 30); 
		panel2.add(angleL2);
		
		vs=new JLabel("V/S",JLabel.CENTER);
		vs.setBounds(220, 150, 150, 30);

		//build textfields for robot1
		BE1 =new JTextField();
		BE1.setBounds(200, 10, 40, 30);	
		panel1.add(BE1);
		
		SD1 =new JTextField();
		SD1.setBounds(200, 70, 40, 30);
		panel1.add(SD1);

		AR1 =new JTextField();
		AR1.setBounds(200, 130, 40, 30);
		panel1.add(AR1);

		AL1 =new JTextField();
		AL1.setBounds(200, 200, 40, 30);
		panel1.add(AL1);

		//build textfields for robot2
		BE2 =new JTextField();
		BE2.setBounds(200, 10, 40, 30);
		panel2.add(BE2);

		SD2 =new JTextField();
		SD2.setBounds(200, 70, 40, 30);
		panel2.add(SD2);

		AR2 =new JTextField();
		AR2.setBounds(200, 130, 40, 30);
		panel2.add(AR2);

		AL2 =new JTextField();
		AL2.setBounds(200, 200, 40, 30);
		panel2.add(AL2);
	    
		//bulid slider for robot1
		R1 =new JSlider(1,10);      
		//add changelistener to trace the change of slider then assign the updated number to textfield
		R1.addChangeListener(new ChangeListener(){
   		public void stateChanged(ChangeEvent event){
  			R1=(JSlider)event.getSource();
   			BE1.setText(""+(R1.getValue()));
   		}
   		});
        R1.setBounds(100,10,100,30);
        panel1.add(R1);
        
        R2 =new JSlider(10,1000);
      //add changelistener to trace the change of slider then assign the updated number to textfield
        R2.addChangeListener(new ChangeListener(){
       		public void stateChanged(ChangeEvent event){
      			R2=(JSlider)event.getSource();
       			SD1.setText(""+(R2.getValue()));
       			
       		}
       		});
        R2.setBounds(100,70,100,30);
        panel1.add(R2);
        
        R3 =new JSlider(0,180);
      //add changelistener to trace the change of slider then assign the updated number to textfield
        R3.addChangeListener(new ChangeListener(){
       		public void stateChanged(ChangeEvent event){
      			R3=(JSlider)event.getSource();
       			AR1.setText(""+(R3.getValue()));
       		}
       		});
        R3.setBounds(100,130,100,30);
        panel1.add(R3);
        
        R4 =new JSlider(0,180);
      //add changelistener to trace the change of slider then assign the updated number to textfield
        R4.addChangeListener(new ChangeListener(){
       		public void stateChanged(ChangeEvent event){
      			R4=(JSlider)event.getSource();
       			AL1.setText(""+(R4.getValue()));
       		}
       		});
        R4.setBounds(100,200,100,30);
        panel1.add(R4);
        
        //bulid slider for robot1
        R5 =new JSlider(1,10);
      //add changelistener to trace the change of slider then assign the updated number to textfield
        R5.addChangeListener(new ChangeListener(){
       		public void stateChanged(ChangeEvent event){
      			R5=(JSlider)event.getSource();
       			BE2.setText(""+(R5.getValue()));
       		}
       		});
        R5.setBounds(100,10,100,30);
        panel2.add(R5);
        
        R6 =new JSlider(10,1000);
      //add changelistener to trace the change of slider then assign the updated number to textfield
        R6.addChangeListener(new ChangeListener(){
       		public void stateChanged(ChangeEvent event){
      			R6=(JSlider)event.getSource();
       			SD2.setText(""+(R6.getValue()));
       		}
       		});
        R6.setBounds(100,70,100,30);
        panel2.add(R6);
        
        R7 =new JSlider(0,180);
      //add changelistener to trace the change of slider then assign the updated number to textfield
        R7.addChangeListener(new ChangeListener(){
       		public void stateChanged(ChangeEvent event){
      			R7=(JSlider)event.getSource();
       			AR2.setText(""+(R7.getValue()));
       		}
       		});
        R7.setBounds(100,130,100,30);
      //add changelistener to trace the change of slider then assign the updated number to textfield
        panel2.add(R7);
        
        R8 =new JSlider(0,180);
      //add changelistener to trace the change of slider then assign the updated number to textfield
        R8.addChangeListener(new ChangeListener(){
       		public void stateChanged(ChangeEvent event){
      			R8=(JSlider)event.getSource();
       			AL2.setText(""+(R8.getValue()));
       		}
       		});
        R8.setBounds(100,200,100,30);
        panel2.add(R8);
       
        //load the file and get the properties then assign to related variables
        loadProperties();
		//show the numbers loaded from the file in the textfield
		BE1.setText(BE1Text+"");		
		SD1.setText(SD1Text+"");
		AR1.setText(AR1Text+"");
		AL1.setText(AL1Text+"");
		BE2.setText(BE2Text+"");
		SD2.setText(SD2Text+"");
		AR2.setText(AR2Text+"");
		AL2.setText(AL2Text+"");
		
		//Build save button for robot1
		Save1 = new JButton("Save 1"); // Instantiate button
		Save1.setBounds(10, 300, 250, 30);
		//add action listener to save 1 button and write the updated number to the file
		Save1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveAction(filePath1,BE1,SD1,AR1,AL1);
			}
				
		});
		
		//Build save button for robot1
		Save2 = new JButton("Save 2"); // Instantiate button
		Save2.setBounds(350, 300, 250, 30);
		//add action listener to save 2 button and write the updated number to the file
		Save2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveAction(filePath2,BE2,SD2,AR2,AL2);
			}	
		});
		
		//build runbattle button to start game
		RunBattle = new JButton("Run Battle"); // Instantiate button
		RunBattle.setBounds(220, 400, 150, 30);
		//add action listener to load the method to start game
		RunBattle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				runBattle();
			}
		});
		
	    //finally add the buttons and labels on the frame
		mainFrame.add(MyRobot1); 
        mainFrame.add(MyRobot2); 
		mainFrame.add(vs); 
		mainFrame.add(Save1);
		mainFrame.add(Save2);
		mainFrame.add(RunBattle);

	
		mainFrame.setVisible(true); // Display frame (make visible)
		
	}//end method
	
	public void loadProperties(){
        Properties file=new Properties();//load the properties then assign to the text field		
		InputStream inputStream1=null;
		InputStream inputStream2=null;
		try{
			inputStream1=new FileInputStream(filePath1);
			file.load(inputStream1);//load a file
			//get according properties in the file and convert to related type to assign to the variables
			BE1Text=Integer.parseInt(file.getProperty("bulletenergy"));
			SD1Text=Integer.parseInt(file.getProperty("stepdistance"));
			AR1Text=Integer.parseInt(file.getProperty("rightrotationangle"));
			AL1Text=Integer.parseInt(file.getProperty("leftrotationangle"));
			inputStream2=new FileInputStream(filePath2);
			file.load(inputStream2);//load a file
			//get according properties in the file and convert to related type to assign to the variables
			BE2Text=Integer.parseInt(file.getProperty("bulletenergy"));
			SD2Text=Integer.parseInt(file.getProperty("stepdistance"));
			AR2Text=Integer.parseInt(file.getProperty("rightrotationangle"));
			AL2Text=Integer.parseInt(file.getProperty("leftrotationangle"));
			
		}//end try
		catch (IOException e) {//except the error
	        e.printStackTrace();
	        }//end catch
		
		finally{//close inputStream at the end
			try {
				inputStream1.close();
				inputStream2.close();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}//end catch
		}//end finally
	}
	
	//the event after click save button
	public void saveAction(String path,JTextField BE,JTextField SD,JTextField AR,JTextField AL){
		Properties file=new Properties();
		OutputStream outputStream =null;
		InputStream inputStream=null;
		
		 try {
			
			inputStream = new FileInputStream(path);//load file
			file.load(inputStream);
			if(Integer.parseInt(BE.getText())<1||Integer.parseInt(BE.getText())>10)//check if bullet energy is out of range 
   				JOptionPane.showMessageDialog(null,"Bullet Energy should be a number between 1 and 10 !");//if so,output error message,and don't save the updated data
			else file.setProperty("bulletenergy", BE.getText());//if the number is right ,write the updated data into file
			
			if(Integer.parseInt(SD.getText())<10||Integer.parseInt(SD.getText())>1000)
   				JOptionPane.showMessageDialog(null,"Step Distance should be a number between 10 and 1000 !");
			else file.setProperty("stepdistance", SD.getText());
			
			if(Integer.parseInt(AR.getText())<0||Integer.parseInt(AR.getText())>180)
   				JOptionPane.showMessageDialog(null,"Rat.AngleR should be a number between 0 and 180 !");
			else file.setProperty("rightrotationangle", AR.getText());
			
			if(Integer.parseInt(AL.getText())<0||Integer.parseInt(AL.getText())>180)
   				JOptionPane.showMessageDialog(null,"Rat.AngleL should be a number between 0 and 180 !");			
			else file.setProperty("leftrotationangle", AL.getText());

			outputStream = new FileOutputStream(path);//output all the changes to the file
			file.store(outputStream,path);//save the changes
			
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
		 

		finally{//close inputStream at the end
				try {
					inputStream.close();
					outputStream.close();
				} catch (IOException error) {
					
					error.printStackTrace();
	}
}
}//end method	
	
		
	
	//runbattle method
	public static void runBattle(){
		 // Disable log messages from Robocode
		 RobocodeEngine.setLogMessagesEnabled(false);
		 // Create the RobocodeEngine
		 RobocodeEngine engine = new RobocodeEngine(new java.io.File(ROBOCODE_INSTALL_DIR));
		 // Add our own battle listener to the RobocodeEngine
		 engine.addBattleListener(new BattleAdaptor(){
		 // Called when the battle is completed successfully with battle results
		 public void onBattleCompleted(BattleCompletedEvent e) {
		 System.out.println("-- Battle has completed --");

		 // Print out the sorted results with the robot names
		 System.out.println("Battle results:");
		 for (robocode.BattleResults result : e.getSortedResults()) {
		 System.out.println(" " + result.getTeamLeaderName() + ": " + result.getScore());
		 }
		 }
		 // Called when the game sends out an information message during the battle
		 public void onBattleMessage(BattleMessageEvent e) {
		 System.out.println("Msg> " + e.getMessage());
		 }
		 // Called when the game sends out an error message during the battle
		 public void onBattleError(BattleErrorEvent e) {
		 System.out.println("Err> " + e.getError());
		 }

		 });
		 // Show the Robocode battle view
		 engine.setVisible(true);
		 // Setup the battle specification
		 int numberOfRounds = 5;
		 BattlefieldSpecification battlefield = new BattlefieldSpecification(800, 600); // 800x600
		 RobotSpecification[] selectedRobots = engine.getLocalRepository(ROBOT_1_NAME+"*,"+ROBOT_2_NAME+"*");

		 System.out.println("Robots selected");

		 BattleSpecification battleSpec = new BattleSpecification(numberOfRounds, battlefield, selectedRobots);

		 System.out.println("Inactivity time:"+battleSpec.getInactivityTime());
		 // Run our specified battle and let it run till it is over
		 engine.runBattle(battleSpec, true); // waits till the battle finishes
		 // Cleanup our RobocodeEngine
		 engine.close();
		 // Make sure that the Java VM is shut down properly
		 System.exit(0);
		 }
	
	
}//end class
	
		

		
	
	 