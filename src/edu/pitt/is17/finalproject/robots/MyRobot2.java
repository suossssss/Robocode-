package edu.pitt.is17.finalproject.robots;


import robocode.*;
import java.io.*;
import java.util.*;

public class MyRobot2 extends Robot {
	double bulletEnergy; // amount of power put in a bullet
	int stepDistance; // distance to make in each step
	int rightRotationAngle; // right angle for rotating the tank (e.g. 30, 45, 60, 90) 
	int leftRotationAngle; // left angle for rotating the tank
	
	public MyRobot2(){
		 loadProperties();
	}//end constructor
			
	public void onScannedRobot(ScannedRobotEvent e) {
        fire(bulletEnergy);
    }
	
	public void run() {
		
		while (true) {
            ahead(stepDistance);
            turnGunRight(rightRotationAngle);
            back(stepDistance);
            turnGunLeft(leftRotationAngle);
            
        }
    }
	
	public void loadProperties() {
		Properties file=new Properties();
		String filePath="/Users/suoliyun/Documents/workspace/lis86_FinalProject/config/MyRobot2.properties";
		InputStream inputStream=null;
		
		try{
			inputStream=new FileInputStream(filePath);
			file.load(inputStream);//load a file
			//get according properties in the file and convert to related type to assign to the variables
			bulletEnergy=Double.parseDouble(file.getProperty("bulletenergy"));
			stepDistance=Integer.parseInt(file.getProperty("stepdistance"));
			rightRotationAngle=Integer.parseInt(file.getProperty("rightrotationangle"));
			leftRotationAngle=Integer.parseInt(file.getProperty("leftrotationangle"));
			
		
		}//end try
		catch (IOException e) {//except the error
	        e.printStackTrace();
	        }//end catch
		
		finally{//close inputStream at the end
			try {
				inputStream.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}//end catch
		}//end finally
		
	}//end method
	
}//end class