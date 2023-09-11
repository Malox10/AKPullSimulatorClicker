import java.awt.Robot

val mode = Mode.Setup

fun main() {
    when(mode) {
        Mode.Setup -> setup()
        Mode.Rolling -> rolling()
    }
}

fun setup() {
    val robot = Robot()
    val context = KeyboardMouseContext { x, y ->
        print("mouse at x:$x y:$y color: ${robot.getPixelColor(x, y)}")
    }
}

fun rolling() {
    val uiFinder = UIFinder()
    val summonButtonLocation = uiFinder.findSummonButton() ?: error("Couldn't find summon button")
    println("summon button at $summonButtonLocation")
}


enum class Mode {
    Setup,
    Rolling
}

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = this.first + other.first to this.second + other.second