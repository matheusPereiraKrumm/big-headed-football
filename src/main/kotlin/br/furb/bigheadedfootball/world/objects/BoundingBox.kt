package br.furb.bigheadedfootball.world.objects

import br.furb.bigheadedfootball.world.components.Point

class BoundingBox(point: Point) {
    var smallerPoint : Point = point.copy()
        private set
    var biggerPoint : Point = point.copy()
        private set
    private val center: Point = point.copy()

    fun refreshBBox(point: Point) : BoundingBox {
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

    fun center() : Point{
        calcCenter()
        return center
    }

}