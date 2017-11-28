package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.common.glut
import br.furb.bigheadedfootball.world.components.*
import br.furb.bigheadedfootball.world.objects.GraphicalObject

class Head : GraphicalObject() {
    override var size = 2.0F
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