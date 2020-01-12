package com.grebneerg.tobor

import edu.wpi.first.wpilibj.Joystick

class LogitechF310(port: Int) : Joystick(port) {
    val leftStickX get() = getRawAxis(0)
    val leftStickY get() = getRawAxis(1)

    val rightStickX get() = getRawAxis(4)
    val rightStickY get() = getRawAxis(5)

    val leftTrigger get() = getRawAxis(2)
    val rightTrigger get() = getRawAxis(3)
}