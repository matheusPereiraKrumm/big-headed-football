package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class MainPlayer : Player(), KeyListener {
    override var color: Color = Color.RED

    init {
        transformation.translation(25.0, 0.0, 60.0)
    }



    var keysActive = ArrayList<Int>()

    override fun keyTyped(p0: KeyEvent) {

    }

    override fun keyPressed(p0: KeyEvent) {
        println(p0.keyCode)
        println(p0.keyChar)
        if(!keysActive.contains(p0.keyCode))
            keysActive.add(p0.keyCode)
        if(keysActive.count() > 0){

        }
    }

    override fun keyReleased(p0: KeyEvent) {
        keysActive.remove(p0.keyCode)
    }
}