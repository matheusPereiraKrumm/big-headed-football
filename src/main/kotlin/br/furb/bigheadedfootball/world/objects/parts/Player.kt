package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import javax.media.opengl.GL

open class Player : GraphicalObject() {
    override var primitive: Int = GL.GL_QUAD_STRIP
    override var color: Color = Color.YELLOW

    init {
        addPoint(Point(1.0, 0.0, 1.0))
        addPoint(Point(-1.0, 0.0, 1.0))
        addPoint(Point(1.0, 0.0, -1.0))
        addPoint(Point(-1.0, 0.0, -1.0))
        addPoint(Point(1.0, 7.0, -1.0))
        addPoint(Point(-1.0, 7.0, -1.0))
        addPoint(Point(1.0, 7.0, 1.0))
        addPoint(Point(-1.0, 7.0, 1.0))
        addPoint(Point(1.0, 0.0, 1.0))
        addPoint(Point(-1.0, 0.0, 1.0))
    }
}