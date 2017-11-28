package br.furb.bigheadedfootball.world.actions.components

import br.furb.bigheadedfootball.world.actions.common.DataInputs
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.components.PointCommom
import br.furb.bigheadedfootball.world.components.Transformation
import java.awt.event.KeyEvent

class MainPlayerAction : IInteraction {
    override fun hasChange(dataInputs: DataInputs): Boolean {
        return dataInputs.keysPressed.contains(KeyEvent.VK_UP) ||
                dataInputs.keysPressed.contains(KeyEvent.VK_DOWN) ||
                dataInputs.keysPressed.contains(KeyEvent.VK_LEFT) ||
                dataInputs.keysPressed.contains(KeyEvent.VK_RIGHT)
    }

    override fun executeChange(dataInputs: DataInputs) {
        val transformation = Transformation(dataInputs.mainPlayer().transformation)
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

        if(canMoveInWorld(transformation, dataInputs)){
            dataInputs.mainPlayer().transformation = transformation
        }
    }

    private fun canMoveInWorld(transformation: Transformation, dataInputs: DataInputs): Boolean {
        val bBoxCamp = dataInputs.camp().boundingBoxUpdated()
        val bBoxMainPlayer = dataInputs.mainPlayer().boundingBoxUpdated(transformation)

        if(bBoxMainPlayer.out2D(bBoxCamp)) return false

        dataInputs.otherPlayers().forEach {
            val bbox = it.boundingBoxUpdated()
            if(bBoxMainPlayer.inner2D(bbox)) return false
        }

        return true
    }
}