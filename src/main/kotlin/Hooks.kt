import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeHookException
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener
import com.github.kwhat.jnativehook.mouse.NativeMouseListener
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener
import kotlin.system.exitProcess

class KeyboardMouseContext(val block: (Int, Int) -> Unit) {
    private var mousePosition: Pair<Int, Int>? = null

    private fun invokeBlock() {
        println("block invoked")
        mousePosition?.let { (x, y) -> block(x, y) }
    }

    private val keyListener = SimpleKeyListener { invokeBlock() }

    private val mouseListener = SimpleMouseListener { x, y ->
        mousePosition = x to y
    }

    init {
        registerKeyListener(keyListener, mouseListener, mouseListener)
        println("listeners initialized")
    }
}

class SimpleKeyListener(private val key: Int = NativeKeyEvent.VC_P, val block: () -> Unit) : NativeKeyListener {
    override fun nativeKeyPressed(e: NativeKeyEvent) {
        if (e.keyCode == key) {
            block()
        }
    }
}

class SimpleMouseListener(val block: (Int, Int) -> Unit) : NativeMouseInputListener {
    override fun nativeMouseMoved(e: NativeMouseEvent) {
        block(e.x, e.y)
    }

    override fun nativeMouseClicked(e: NativeMouseEvent) {}

    override fun nativeMousePressed(e: NativeMouseEvent) {}

    override fun nativeMouseDragged(e: NativeMouseEvent) {}
}

fun registerKeyListener(
    keyListener: NativeKeyListener,
    nativeMouseListener: NativeMouseListener,
    nativeMouseMotionListener: NativeMouseMotionListener
) {
    try {
        GlobalScreen.registerNativeHook()
    } catch (ex: NativeHookException) {
        System.err.println("There was a problem registering the native hook.")
        System.err.println(ex.message)
        exitProcess(1)
    }
    GlobalScreen.addNativeKeyListener(keyListener)
    GlobalScreen.addNativeMouseListener(nativeMouseListener)
    GlobalScreen.addNativeMouseMotionListener(nativeMouseMotionListener)
}