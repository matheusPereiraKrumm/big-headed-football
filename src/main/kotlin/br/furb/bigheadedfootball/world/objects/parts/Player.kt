package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import javax.media.opengl.GL

open class Player : GraphicalObject() {
    override var size = 1F
    override var primitive: Int = GL.GL_QUAD_STRIP
    override var color: Color = Color.YELLOW
    var head: Head = Head()

    init {
        transformation.showMatrix()
        transformationDistortion.scale(2.0, 7.0, 2.0)
        transformation.showMatrix()
        //transformation.translation(-3.5, 3.5, -3.5)
        transformation.translation(0.0, 3.5, 0.0)
        transformation.showMatrix()
        
        addPoint(Point(1.0, -3.5, 1.0))
        addPoint(Point(-1.0, -3.5, 1.0))
        
        addPoint(Point(1.0, -3.5, -1.0))
        addPoint(Point(-1.0, -3.5, -1.0))
        
        addPoint(Point(1.0, 3.5, -1.0))
        addPoint(Point(-1.0, 3.5, -1.0))
        
        addPoint(Point(1.0, 3.5, 1.0))
        addPoint(Point(-1.0, 3.5, 1.0))
        
        addPoint(Point(1.0, -3.5, 1.0))
        addPoint(Point(-1.0, -3.5, 1.0))

        childGraphicalObjects.add(head)
    }
}