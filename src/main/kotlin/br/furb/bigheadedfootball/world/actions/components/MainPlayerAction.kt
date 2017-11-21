package br.furb.bigheadedfootball.world.actions.components

import br.furb.bigheadedfootball.world.actions.common.DataInputs
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.components.PointCommom
import java.awt.event.KeyEvent

class MainPlayerAction : IInteraction {
    override fun hasChange(dataInputs: DataInputs): Boolean {
        return dataInputs.keysPressed.contains(KeyEvent.VK_UP) ||
                dataInputs.keysPressed.contains(KeyEvent.VK_DOWN) ||
                dataInputs.keysPressed.contains(KeyEvent.VK_LEFT) ||
                dataInputs.keysPressed.contains(KeyEvent.VK_RIGHT)
    }

    override fun executeChange(dataInputs: DataInputs) {
        val transformation = dataInputs.mainPlayer().transformation
        val originPoint = transformation.transformPoint(PointCommom.neutralPoint())
        val destinedZPoint = transformation.transformPoint(Point(0.0, 0.0, -0.6))
        val positiveMoveZPoint = destinedZPoint.diff(originPoint)

        if (dataInputs.keysPressed.contains(KeyEvent.VK_UP))
            transformation.translation(positiveMoveZPoint)

        if (dataInputs.keysPressed.contains(KeyEvent.VK_DOWN))
            transformation.translation(positiveMoveZPoint.inverted())

        val radians = 0.2
        if (dataInputs.keysPressed.contains(KeyEvent.VK_LEFT))
            transformation.rotation(radians)

        if (dataInputs.keysPressed.contains(KeyEvent.VK_RIGHT))
            transformation.rotation(-radians)
    }
}