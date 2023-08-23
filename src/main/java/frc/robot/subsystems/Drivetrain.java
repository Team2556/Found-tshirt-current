package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Drivetrain extends SubsystemBase {
    private static Drivetrain instance;

    private final WPI_TalonSRX frontLeft = new WPI_TalonSRX(3);
    private final WPI_TalonSRX backLeft = new WPI_TalonSRX(1);
    private final WPI_TalonSRX backRight = new WPI_TalonSRX(4);
    private final WPI_TalonSRX frontRight = new WPI_TalonSRX(2);
    public final Compressor pmCompressor = new Compressor(5, PneumaticsModuleType.CTREPCM);

    private final Relay relay = new Relay(0, Relay.Direction.kBoth);

    MecanumDrive driveMecanum = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    public Drivetrain() {
        frontLeft.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);

        frontRight.setInverted(true);
        backRight.setInverted(true);
    }

    public void driveTeleop(double forward, double strafe, double turn) {
        driveMecanum.driveCartesian(forward, strafe, turn);
    }

    public void setRelay(Relay.Value value) {
        relay.set(value);
    }

    public static Drivetrain getInstance() {
        if(instance == null)
            instance = new Drivetrain();
        return instance;
    }
}
