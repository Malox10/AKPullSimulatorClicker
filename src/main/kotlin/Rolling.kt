class Rolling() {
    fun rolling() {
        val uiFinder = UIFinder()
        val summonButtonLocation = uiFinder.findSummonButton() ?: error("Couldn't find summon button")
        println("summon button at $summonButtonLocation")
        val rarities = uiFinder.getRarities(summonButtonLocation)
        println(rarities)
    }
}






