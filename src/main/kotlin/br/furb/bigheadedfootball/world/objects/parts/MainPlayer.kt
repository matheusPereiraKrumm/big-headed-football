package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.common.glDrawable
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.components.PointCommom
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class MainPlayer : Player(), KeyListener {
    override var color: Color = Color.RED

    init {
        transformation.translation(25.0, 0.0, 60.0)
    }


    private val pressed = HashSet<Int>()

    override fun keyTyped(p0: KeyEvent) {}

    private val UP = 38
    private val DOWN = 40
    private val LEFT = 37
    private val RIGTH = 39
    override fun keyPressed(e: KeyEvent) {
        println(e.keyCode)
        pressed.add(e.keyCode)
        if (pressed.isNotEmpty()) {
            val orignPoint = transformation.transformPoint(PointCommom.neutralPoint())
            val destinedZPoint = transformation.transformPoint(Point(0.0,0.0, -1.0))
            val positiveMoveZPoint = destinedZPoint.diff(orignPoint)

            if (pressed.contains(UP))
                transformation.translation(positiveMoveZPoint)

            if (pressed.contains(DOWN))
                transformation.translation(positiveMoveZPoint.inverted())

            val radians = 0.5
            if (pressed.contains(LEFT))
                rotation(radians)

            if (pressed.contains(RIGTH))
                rotation(-radians)


            glDrawable {
                display()
            }
        }
    }

    override fun keyReleased(e: KeyEvent) {
        pressed.remove(e.keyCode)
    }

    fun rotation(radians : Double){
        transformWithCenterBBox {
            transformation.rotateY(radians)
        }
    }

    private fun transformWithCenterBBox(block: () -> Unit) {
        val center = PointCommom.neutralPoint()
        val centerPoint = transformation.transformPoint(center)
        val inverted = centerPoint.inverted()

        transformation.translation(inverted)
        block()
        transformation.translation(centerPoint)
    }

}