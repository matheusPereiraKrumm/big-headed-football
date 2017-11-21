package br.furb.bigheadedfootball.world.actions.components

import br.furb.bigheadedfootball.world.actions.common.DataInputs
import java.awt.event.KeyEvent

class CamSpyAction : IInteraction {
    override fun hasChange(dataInputs: DataInputs): Boolean {
        return dataInputs.keysPressed.contains(KeyEvent.VK_D) ||
                dataInputs.keysPressed.contains(KeyEvent.VK_A) ||
                dataInputs.keysReleased.contains(KeyEvent.VK_D) ||
                dataInputs.keysReleased.contains(KeyEvent.VK_A)
    }

    override fun executeChange(dataInputs: DataInputs) {
        dataInputs.cam().canSeeRight = dataInputs.keysPressed.contains(KeyEvent.VK_D)

        dataInputs.cam().canSeeLeft = dataInputs.keysPressed.contains(KeyEvent.VK_A)
    }
}