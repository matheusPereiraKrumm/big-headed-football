package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import javax.media.opengl.GL

class Head : GraphicalObject() {
    override var size = 2.5F
    override var primitive: Int = GL.GL_QUAD_STRIP
    override var color: Color = Color.WHITE

    init {
        transformation.translation(0.0, 2.5, 0.0)

        addPoint(Point(1.5, -1.5, 1.5))
        addPoint(Point(-1.5, -1.5, 1.5))

        addPoint(Point(1.5, -1.5, -1.5))
        addPoint(Point(-1.5, -1.5, -1.5))

        addPoint(Point(1.5, 1.5, -1.5))
        addPoint(Point(-1.5, 1.5, -1.5))

        addPoint(Point(1.5, 1.5, 1.5))
        addPoint(Point(-1.5, 1.5, 1.5))

        addPoint(Point(1.5, -1.5, 1.5))
        addPoint(Point(-1.5, -1.5, 1.5))
    }
}