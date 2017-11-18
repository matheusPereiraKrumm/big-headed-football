package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import javax.media.opengl.GL

class Goal : GraphicalObject() {
    override var primitive: Int = GL.GL_QUADS
    override var color: Color = Color.BLACK

    init {
        addPoint(Point(15.0, 0.0, 0.0))
        addPoint(Point(35.0, 0.0, 0.0))
        addPoint(Point(35.0, 10.0, 0.0))
        addPoint(Point(15.0, 10.0, 0.0))
    }
}