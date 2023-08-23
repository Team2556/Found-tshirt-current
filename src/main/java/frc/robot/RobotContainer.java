// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.drivetrain.TeleopDrive;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

    private static RobotContainer instance;
    private static final Drivetrain drivetrain = Drivetrain.getInstance();

    // Controllers
    XboxController xbox1 = new XboxController(0);

    public RobotContainer() {
        configureControls();
    }

    private void configureControls() {

        drivetrain.setDefaultCommand(
                new TeleopDrive(xbox1::getLeftY, xbox1::getLeftX, xbox1::getRightX, xbox1::getAButtonPressed));
//        new JoystickButton(xbox1, XboxController.Button.kB.value)
//                .whenReleased(() -> dropped = !dropped);
//
//        intake.setDefaultCommand(
//                new Active(xbox1::getRightTriggerAxis,0.0));
//
//        shooter.setDefaultCommand(
//                new Shoot(xbox1::getLeftTriggerAxis));
    }

    public static RobotContainer getInstance() {
        if(instance == null)
            instance = new RobotContainer();
        return instance;
    }
}