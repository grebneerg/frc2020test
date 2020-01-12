package com.grebneerg.tobor

import com.revrobotics.ColorMatch
import com.revrobotics.ColorSensorV3
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

class Robot : TimedRobot() {
    val colorSensorPort = I2C.Port.kOnboard

    private val colorSensor = ColorSensorV3(colorSensorPort)

    private val matcher = ColorMatch()
    val BLUE = ColorMatch.makeColor(0.0, 0.5, 0.5)
    val RED = ColorMatch.makeColor(.50, 0.0, 0.0)
    val GREEN = ColorMatch.makeColor(0.0, 0.5, 0.0)
    val YELLOW = ColorMatch.makeColor(0.5, 0.5, 0.0)

    val drivetrain = Drivetrain()

    val gamepad = LogitechF310(0)

    override fun robotInit() {
        println("Hello World from Kotlin!")

        matcher.addColorMatch(BLUE)
        matcher.addColorMatch(RED)
        matcher.addColorMatch(GREEN)
        matcher.addColorMatch(YELLOW)
    }

    override fun robotPeriodic() {
        val color = colorSensor.color
        val proximity = colorSensor.proximity

        SmartDashboard.putNumber("blue", color.blue)
        SmartDashboard.putNumber("red", color.red)
        SmartDashboard.putNumber("green", color.green)

        SmartDashboard.putNumber("proximity", proximity.toDouble())

        val match = matcher.matchClosestColor(color)
        SmartDashboard.putString("match", when (match.color) {
            BLUE -> "Blue"
            RED -> "Red"
            GREEN -> "Green"
            YELLOW -> "Yellow"
            else -> "Unknown"
        })
        SmartDashboard.putNumber("Match confidence", match.confidence)

//        println("beep")
    }

    override fun teleopInit() {
        drivetrain.setSpeedsPercent(0.0, 0.0)
    }

    override fun teleopPeriodic() {
        drivetrain.setSpeedsPercent(gamepad.leftStickY, gamepad.rightStickY)
    }
}
