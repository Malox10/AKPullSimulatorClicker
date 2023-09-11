import java.awt.Robot
import java.awt.event.MouseEvent

class Rolling() {
    private val robot = Robot()
    private val uiFinder = UIFinder()
    private val summonButtonLocation = uiFinder.findSummonButton() ?: error("Couldn't find summon button")

    fun rolling() {
        do {
            val rarities = doOneRoll()
        } while (rarities.sixCount < 3 && (rarities.sixCount + rarities.fiveCount) < 4)
    }

    private fun doOneRoll(): Rarities {
        robot.click(summonButtonLocation + (10 to 10))
        Thread.sleep(40)
        val rarities = uiFinder.getRarities(summonButtonLocation)
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
}

fun Robot.click(point: Pair<Int, Int>, delay: Long = 0) {
    this.mouseMove(point.first, point.second)
    Thread.sleep(delay)
    this.mousePress(MouseEvent.BUTTON1_DOWN_MASK)
    Thread.sleep(delay)
    this.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK)
}






