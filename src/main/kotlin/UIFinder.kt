import java.awt.Color
import java.awt.Rectangle
import java.awt.Robot

class UIFinder {
    //Summon Button Location (301, 295)
    fun findSummonButton(): Pair<Int, Int>? {
        val robot = Robot()
        val screenshotRegion = Rectangle(0, 0, screenResolution.first, screenResolution.second)
        val screenshot = robot.createScreenCapture(screenshotRegion)

        for(row in 0 until screenshot.height) {
            for(column in 0 until screenshot.width) {
                val pixel = screenshot.getRGB(column, row)
                if(pixel == Colors.Summon.value.rgb) {
                    return column to row
                }
            }
        }

        return null
    }



    companion object {
        val screenResolution = 1920 to 1080
    }
}

enum class Colors(val value: Color) {
    Summon(Color(54, 110, 162)),
    Background(Color(248, 248, 248)),
    FiveStar(Color(0, 116, 217)),
    SixStar(Color(255, 65, 54))
}

val controlPointsOffset = listOf(
    400 to 240,
    400 to 345,
    400 to 450,
    400 to 555,
    400 to 660,
    520 to 240,
    520 to 345,
    520 to 450,
    520 to 555,
    520 to 660,
)