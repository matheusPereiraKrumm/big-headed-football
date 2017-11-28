package br.furb.bigheadedfootball.world.objects

import br.furb.bigheadedfootball.common.drawGl
import br.furb.bigheadedfootball.common.gl
import br.furb.bigheadedfootball.common.glColor
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import javax.media.opengl.GL

class BoundingBox(point: Point) {
    var smallerPoint: Point = point.copy()
        private set
    var biggerPoint: Point = point.copy()
        private set
    private val center: Point = point.copy()

    fun refreshBBox(point: Point): BoundingBox {
        if (point.x < smallerPoint.x)
            smallerPoint.x = point.x
        else {
            if (point.x > biggerPoint.x) biggerPoint.x = point.x
        }
        if (point.y < smallerPoint.y)
            smallerPoint.y = point.y
        else {
            if (point.y > biggerPoint.y) biggerPoint.y = point.y
        }
        if (point.z < smallerPoint.z)
            smallerPoint.z = point.z
        else {
            if (point.z > biggerPoint.z) biggerPoint.z = point.z
        }
        return this
    }

    fun calcCenter() {
        center.x = (biggerPoint.x + smallerPoint.x) / 2
        center.y = (biggerPoint.y + smallerPoint.y) / 2
        center.z = (biggerPoint.z + smallerPoint.z) / 2
    }

    fun center(): Point {
        calcCenter()
        return center
    }

    fun inner2D(otherBBox: BoundingBox): Boolean {
        val pointsOfHere = arrayListOf(
                smallerPoint,
                biggerPoint,
                smallerPoint.copy(z = biggerPoint.z),
                smallerPoint.copy(x = biggerPoint.x)
        )

        val pointsOfOther = arrayListOf(
                otherBBox.smallerPoint,
                otherBBox.biggerPoint,
                otherBBox.smallerPoint.copy(z = otherBBox.biggerPoint.z),
                otherBBox.smallerPoint.copy(x = otherBBox.biggerPoint.x)
        )

        pointsOfHere.forEach {
            if(it.innerIn2D(otherBBox.smallerPoint, otherBBox.biggerPoint))
                return true
        }
        pointsOfOther.forEach {
            if(it.innerIn2D(smallerPoint, biggerPoint))
                return true
        }
        return false
    }

    fun draw() {
        gl {
            glColor(Color.RED)
            drawGl(GL.GL_LINE_STRIP) {
                glVertex3d(smallerPoint.x, smallerPoint.y, smallerPoint.z)
                glVertex3d(biggerPoint.x, smallerPoint.y, smallerPoint.z)
                glVertex3d(biggerPoint.x, biggerPoint.y, smallerPoint.z)
                glVertex3d(smallerPoint.x, biggerPoint.y, smallerPoint.z)
                glVertex3d(smallerPoint.x, smallerPoint.y, smallerPoint.z)
                glVertex3d(smallerPoint.x, smallerPoint.y, biggerPoint.z)
                glVertex3d(biggerPoint.x, smallerPoint.y, biggerPoint.z)
                glVertex3d(biggerPoint.x, biggerPoint.y, biggerPoint.z)
                glVertex3d(smallerPoint.x, biggerPoint.y, biggerPoint.z)
                glVertex3d(smallerPoint.x, smallerPoint.y, biggerPoint.z)
            }
        }
    }

    fun out2D(bBoxCamp: BoundingBox): Boolean {
        return smallerPoint.x < bBoxCamp.smallerPoint.x ||
                smallerPoint.x > bBoxCamp.biggerPoint.x ||
                smallerPoint.z < bBoxCamp.smallerPoint.z ||
                smallerPoint.z > bBoxCamp.biggerPoint.z
    }

}