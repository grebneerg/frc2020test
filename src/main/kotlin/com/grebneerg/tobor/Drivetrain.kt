package com.grebneerg.tobor

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj2.command.Subsystem

class Drivetrain : Subsystem {
    private val leftMaster = CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless)
    private val leftFollower = CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless)
    private val rightMaster = CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless)
    private val rightFollower = CANSparkMax(7, CANSparkMaxLowLevel.MotorType.kBrushless)

    init {
        leftMaster.restoreFactoryDefaults()
        leftFollower.restoreFactoryDefaults()
        rightMaster.restoreFactoryDefaults()
        rightFollower.restoreFactoryDefaults()

        leftFollower.idleMode = CANSparkMax.IdleMode.kBrake
        leftMaster.idleMode = CANSparkMax.IdleMode.kBrake
        rightFollower.idleMode = CANSparkMax.IdleMode.kBrake
        rightMaster.idleMode = CANSparkMax.IdleMode.kBrake

        leftMaster.inverted = true

        leftFollower.follow(leftMaster)
        rightFollower.follow(rightMaster)
    }

    fun setSpeedsPercent(left: Double, right: Double) {
        leftMaster.set(left.bound(-1.0, 1.0))
        rightMaster.set(right.bound(-1.0, 1.0))
    }
}