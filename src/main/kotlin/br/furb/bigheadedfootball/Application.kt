package br.furb.bigheadedfootball

import br.furb.bigheadedfootball.world.World
import br.furb.bigheadedfootball.world.actions.WorldInteraction
import java.awt.BorderLayout
import java.awt.Cursor
import javax.media.opengl.GLCanvas
import javax.media.opengl.GLCapabilities
import javax.swing.JFrame
import javax.swing.WindowConstants


class Main : JFrame() {

    var world: World = World()
    val canvas: GLCanvas

    init {

        title = "Big Headed Football"
        setBounds(300, 250, 400, 400 + 22)
        extendedState = JFrame.MAXIMIZED_BOTH
        isUndecorated = true
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        contentPane.layout = BorderLayout()

        val glCaps = GLCapabilities()
        glCaps.redBits = 8
        glCaps.blueBits = 8
        glCaps.greenBits = 8
        glCaps.alphaBits = 8
        cursor = Cursor(Cursor.HAND_CURSOR)
        canvas = GLCanvas(glCaps)
        add(canvas, BorderLayout.CENTER)
        canvas.addGLEventListener(world)
        canvas.addKeyListener(WorldInteraction(world))
        //canvas.addKeyListener(world.mainPlayer)

    }
}

fun main(args: Array<String>) {
    val m = Main()
    m.isVisible = true
    m.canvas.requestFocus()
}