package br.furb.bigheadedfootball.world.actions.components

import br.furb.bigheadedfootball.world.actions.common.DataInputs
import br.furb.bigheadedfootball.world.components.SphereFaces
import java.awt.event.KeyEvent

class ChangeFacesSphereAction : IInteraction {
    override fun hasChange(dataInputs: DataInputs): Boolean {
        return dataInputs.keysPressed.contains(KeyEvent.VK_0) ||
                dataInputs.keysPressed.contains(KeyEvent.VK_MINUS)
    }

    override fun executeChange(dataInputs: DataInputs) {
        if (dataInputs.keysPressed.contains(KeyEvent.VK_0))
            SphereFaces.qtdFaces += 2

        if (dataInputs.keysPressed.contains(KeyEvent.VK_MINUS) && SphereFaces.qtdFaces > 5)
            SphereFaces.qtdFaces -= 2
    }
}