import java.awt.Robot

val mode = Mode.Rolling
//set to rolling and hover over the summon button then start the program

fun main() {
    when(mode) {
        Mode.Setup -> setup()
        Mode.Rolling -> {
            val roller = Rolling()
            roller.rolling()
        }
    }
}

fun setup() {
    val robot = Robot()
    val context = KeyboardMouseContext { x, y ->
        print("mouse at x:$x y:$y color: ${robot.getPixelColor(x, y)}")
    }
}

enum class Mode {
    Setup,
    Rolling
}
