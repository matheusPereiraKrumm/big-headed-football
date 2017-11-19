package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.components.PointCommom
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import javax.media.opengl.GL

class Camp : GraphicalObject() {
    override var size = 1F
    override var color: Color = Color.CAMP
    override var primitive: Int = GL.GL_QUADS

    init {
        transformation.scale(50.0, 1.0, 140.0)
        transformation.translation(25.0, -.5, 70.0)


        addPoint(PointCommom.neutralPoint())
        addPoint(Point(50.0, 0.0, 0.0))
        addPoint(Point(50.0, 0.0, 140.0))
        addPoint(Point(0.0, 0.0, 140.0))
    }

}