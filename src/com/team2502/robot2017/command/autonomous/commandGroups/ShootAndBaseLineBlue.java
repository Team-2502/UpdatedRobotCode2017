package com.team2502.robot2017.command.autonomous.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.team2502.robot2017.command.autonomous.*;

public class ShootAndBaseLineBlue extends CommandGroup
{
    public ShootAndBaseLineBlue()
    {
    	addSequential(new ShootAutoBase("blue"));
	    addSequential(new EncoderDrive(80, 2));
    }
}
