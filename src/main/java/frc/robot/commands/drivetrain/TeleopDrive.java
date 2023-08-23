package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

import java.util.function.Supplier;

public class TeleopDrive extends CommandBase {

    Drivetrain drivetrain = Drivetrain.getInstance();

    private final Supplier<Double> forward, strafe, turn;
    private final Supplier<Boolean> shoot;

    public TeleopDrive(Supplier<Double> forward, Supplier<Double> strafe, Supplier<Double> turn, Supplier<Boolean> shoot) {
        this.forward = forward;
        this.strafe = strafe;
        this.turn = turn;
        this.shoot = shoot;

        addRequirements(drivetrain);
        setName("TeleOp Drive");
    }

    @Override
    public void initialize() {
        drivetrain.pmCompressor.enableDigital();
    }

    @Override
    public void execute() {
        drivetrain.driveTeleop(-forward.get(), strafe.get(), turn.get());

        if(shoot.get())
            //add thread.sleep
            drivetrain.setRelay(Relay.Value.kOn);
            //add thread.sleep
        else
            //add thread.sleep
            drivetrain.setRelay(Relay.Value.kOff);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.driveTeleop(0.0,0.0,0.0);
        drivetrain.setRelay(Relay.Value.kOff);
        drivetrain.pmCompressor.disable();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}