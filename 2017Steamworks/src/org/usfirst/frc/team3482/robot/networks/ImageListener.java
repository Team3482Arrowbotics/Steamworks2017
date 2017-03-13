package org.usfirst.frc.team3482.robot.networks;

import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class ImageListener implements ITableListener{
	
	public ImageListener(){
		
	}
	@Override
	public void valueChanged(ITable source, String key, Object value, boolean isNew) {
		System.out.println(key + " changed to " + value + ", is new: " + isNew);
		
	}

}
