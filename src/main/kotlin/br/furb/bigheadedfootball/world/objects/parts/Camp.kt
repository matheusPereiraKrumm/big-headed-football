package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import javax.media.opengl.GL

class Camp : GraphicalObject() {
    override var color: Color = Color.CAMP
    override var primitive: Int = GL.GL_QUADS

    init {
        addPoint(Point(0.0, 0.0, 0.0))
        addPoint(Point(50.0, 0.0, 0.0))
        addPoint(Point(50.0, 0.0, 140.0))
        addPoint(Point(0.0, 0.0, 140.0))
    }

}