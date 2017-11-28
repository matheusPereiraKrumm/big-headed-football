package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.common.glut
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.SphereFaces
import br.furb.bigheadedfootball.world.objects.GraphicalObject

open class Player : GraphicalObject() {
    override var size = 1F
    override var color: Color = Color.YELLOW
    private var head: Head = Head()

    init {
        transformationDistortion.rotation90X()
        transformationDistortion.scale(1.1, 8.0, 1.1)
        transformation.translation(0.0, 3.5, 0.0)

        childGraphicalObjects.add(head)
    }

    override fun innerDraw() {
        glut {
            glutSolidCylinder(1.0, 1.0, SphereFaces.qtdFaces, SphereFaces.qtdFaces)
        }
    }
}