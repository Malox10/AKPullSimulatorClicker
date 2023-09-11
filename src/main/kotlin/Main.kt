val mode = Mode.Setup

fun main() {
    when(mode) {
        Mode.Setup -> setup()
        Mode.Rolling -> rolling()
    }
}

fun setup() {
    val context = KeyboardMouseContext { x, y -> print("mouse at x:$x y:$y") }
}

fun rolling() {

}


enum class Mode() {
    Setup,
    Rolling
}
