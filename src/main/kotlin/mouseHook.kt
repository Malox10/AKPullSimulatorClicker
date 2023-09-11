import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeHookException
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener

class SimpleMouseListener(val block: (Int, Int) -> Unit) : NativeMouseInputListener {
    override fun nativeMouseClicked(e: NativeMouseEvent) {
        println("Mouse Clicked: " + e.clickCount)
    }

    override fun nativeMousePressed(e: NativeMouseEvent) {
        println("Mouse Pressed: " + e.button)
    }

    override fun nativeMouseMoved(e: NativeMouseEvent) {
        block(e.x, e.y)
    }

    override fun nativeMouseDragged(e: NativeMouseEvent) {
        println("Mouse Dragged: " + e.x + ", " + e.y)
    }
}