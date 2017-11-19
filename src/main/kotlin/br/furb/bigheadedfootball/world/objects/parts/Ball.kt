package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import javax.media.opengl.GL

class Ball : GraphicalObject() {
    override var size = 2F
    override var primitive = GL.GL_QUAD_STRIP
    override var color = Color.BALL

    init {
        transformation.translation(0.0, -2.5, -2.0)
        addPoint(Point(1.0, 0.0, 1.0))
        addPoint(Point(-1.0, 0.0, 1.0))
        addPoint(Point(1.0, 0.0, -1.0))
        addPoint(Point(-1.0, 0.0, -1.0))
        addPoint(Point(1.0, 2.0, -1.0))
        addPoint(Point(-1.0, 2.0, -1.0))
        addPoint(Point(1.0, 2.0, 1.0))
        addPoint(Point(-1.0, 2.0, 1.0))
        addPoint(Point(1.0, 0.0, 1.0))
        addPoint(Point(-1.0, 0.0, 1.0))
    }
}