package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.common.glut
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.objects.GraphicalObject

class Ball : GraphicalObject() {
    override var size = 2F
    override var color = Color.BALL

    init {
        transformation.translation(0.0, -2.5, -2.0)
    }

    override fun innerDraw() {
        glut {
            glutSolidSphere(1.0, 70, 70)
        }
    }
}