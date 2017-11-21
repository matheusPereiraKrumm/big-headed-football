package br.furb.bigheadedfootball.world.actions

import br.furb.bigheadedfootball.common.glDrawable
import br.furb.bigheadedfootball.world.World
import br.furb.bigheadedfootball.world.actions.common.DataInputs
import br.furb.bigheadedfootball.world.actions.components.CamSpyAction
import br.furb.bigheadedfootball.world.actions.components.IInteraction
import br.furb.bigheadedfootball.world.actions.components.MainPlayerAction
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import kotlin.concurrent.thread

class WorldInteraction(val world: World) : KeyListener {
    private val interactions: Array<IInteraction> =
            arrayOf(MainPlayerAction(), CamSpyAction())
    private val data = DataInputs(world)

    init {
        thread {
            while (true) {
                val canRepaint = executeActions()
                if (canRepaint) glDrawable { display() }

                Thread.sleep(70)
            }
        }
    }

    override fun keyTyped(e: KeyEvent) {}

    @Synchronized override fun keyPressed(e: KeyEvent) {
        data.keysPressed.add(e.keyCode)
    }

    @Synchronized override fun keyReleased(e: KeyEvent) {
        data.keysPressed.remove(e.keyCode)
        data.keysReleased.add(e.keyCode)
    }

    @Synchronized private fun executeActions(): Boolean {
        var canRepaint = false
        interactions.forEach {
            if (it.hasChange(data)) {
                canRepaint = true
                it.executeChange(data)
            }
        }
        data.keysReleased.clear()
        return canRepaint
    }
}