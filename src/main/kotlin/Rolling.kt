import java.awt.Rectangle
import java.awt.Robot
import java.awt.event.MouseEvent
import java.io.File
import javax.imageio.ImageIO

class Rolling() {
    private val robot = Robot()
    private val uiHandler = UIHandler()
    private val summonButtonLocation = uiHandler.findSummonButton() ?: error("Couldn't find summon button")

    fun rolling() {
        for (i in 1..1000) {
            val rarities = doOneRoll()
            if (rarities.sixCount >= 3
                || (rarities.sixCount + rarities.fiveCount) >= 6
                || (rarities.sixCount >= 2 && rarities.fiveCount >= 3)) {
                screenshotRoll("${rarities.sixCount}-${rarities.fiveCount}-${System.currentTimeMillis()}")
            }
        }
    }

    private fun doOneRoll(): Rarities {
        robot.click(summonButtonLocation + (10 to 10))
        Thread.sleep(40)
        val rarities = uiHandler.getRarities(summonButtonLocation)
        println(rarities)
        return Rarities(
            rarities.count { it == Rarity.Six },
            rarities.count { it == Rarity.Five },
            rarities.count { it == Rarity.Low },
        )
    }

    data class Rarities(
        val sixCount: Int,
        val fiveCount: Int,
        val lowCount: Int,
    )

    private fun screenshotRoll(screenshotName: String) {
        val startingPoint = summonButtonLocation + (-5 to -5)

        val screenshotRegion = Rectangle(startingPoint.first, startingPoint.second, 920, 730)
        val screenshot = robot.createScreenCapture(screenshotRegion)

        val file = File("$screenshotFolder$screenshotName.jpg")
        ImageIO.write(screenshot, "jpg", file)
    }

    companion object {
        private const val screenshotFolder = "screenshots/"
    }
}

fun Robot.click(point: Pair<Int, Int>, delay: Long = 0) {
    this.mouseMove(point.first, point.second)
    Thread.sleep(delay)
    this.mousePress(MouseEvent.BUTTON1_DOWN_MASK)
    Thread.sleep(delay)
    this.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK)
}






