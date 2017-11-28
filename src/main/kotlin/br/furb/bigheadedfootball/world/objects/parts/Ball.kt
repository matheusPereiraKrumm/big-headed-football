package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.common.glut
import br.furb.bigheadedfootball.world.components.*
import br.furb.bigheadedfootball.world.objects.GraphicalObject

class Ball : GraphicalObject() {
    override var size = 1F
    override var color = Color.WHITE

    init {
        transformation.translation(0.0, -2.5, -2.0)
    }

    override fun innerDraw() {
        glut {
            glutSolidSphere(1.0, SphereFaces.qtdFaces, SphereFaces.qtdFaces)
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