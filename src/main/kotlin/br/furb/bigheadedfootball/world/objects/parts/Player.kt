package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.common.glut
import br.furb.bigheadedfootball.world.components.*
import br.furb.bigheadedfootball.world.objects.GraphicalObject

open class Player : GraphicalObject() {
    override var size = 2F
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

    override fun drawBBox() {
        boundingBoxUpdated().draw()
    }


    override fun minMaxPoint(transformationOther: Transformation): Pair<Point, Point> {
        val center = realTransformPoint(transformationOther, PointCommom.neutralPoint())
        val minPointBBox = center.copy(center.x - size, center.y - size, center.z - size)
        val maxPointBBox = center.copy(center.x + size, center.y + size, center.z + size)
        return minPointBBox to maxPointBBox
    }

    override fun minMaxPointWithParentTransform(transformationOther: Transformation): Pair<Point, Point> {
        val center = realTransformPointWithParentTransform(transformationOther, PointCommom.neutralPoint())
        val minPointBBox = center.copy(center.x - size, center.y - size, center.z - size)
        val maxPointBBox = center.copy(center.x + size, center.y + size, center.z + size)
        return minPointBBox to maxPointBBox
    }
}