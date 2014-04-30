package org.openqa.selenium.remote;


import java.awt.AWTException;
import java.awt.Robot;

import com.google.common.collect.ImmutableMap;

import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.ExecuteMethod;


public class SRobot{

	protected final ExecuteMethod executor;
	private static Robot robot=null;
	private boolean remote = false;
	
	public SRobot(ExecuteMethod executor) {	
		this.executor = executor;
	}
	
	public SRobot(RemoteWebDriver driver) {		
		this.executor = driver.getExecuteMethod();
	}
	
	public void pressKey(int keyToPress) {
		if(remote){
			executor.execute(DriverCommand.ROBOT_KEYPRESS,
	                     ImmutableMap.of("value", keyToPress));
		}else{
			SRobot.getRobot().keyPress(keyToPress);
		}
	}
	
	public void releaseKey(int keyToRelease) {
		if(remote){
			executor.execute(DriverCommand.ROBOT_KEYRELEASE,
	                     ImmutableMap.of("value", keyToRelease));
		}else{
			SRobot.getRobot().keyRelease(keyToRelease);
		}
		
	}

	public static synchronized Robot getRobot() {
		if (robot == null) {
			try {
				robot = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return robot;
	}
	
	public void setRemote(boolean remote){
		this.remote = remote;
	}
	
	public boolean getRemote(){
		return this.remote;
	}

}
