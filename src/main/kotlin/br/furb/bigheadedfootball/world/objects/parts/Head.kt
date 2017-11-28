package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.common.glut
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.SphereFaces
import br.furb.bigheadedfootball.world.objects.GraphicalObject

class Head : GraphicalObject() {
    override var size = 2.5F
    override var color: Color = Color.BROWN

    init {
        transformation.scale(1.0, 2.0, 1.0)
        transformation.translation(0.0, 2.5, 0.0)
    }


    override fun innerDraw() {
        glut {
            glutSolidSphere(1.7, SphereFaces.qtdFaces, SphereFaces.qtdFaces)
        }
    }
}