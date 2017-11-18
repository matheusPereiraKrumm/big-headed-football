package br.furb.bigheadedfootball.world.objects

import br.furb.bigheadedfootball.common.*
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.components.Transformation

abstract class GraphicalObject {
    abstract var primitive: Int
    abstract var color: Color
    var points: ArrayList<Point> = ArrayList()
        private set
    var boundingBox: BoundingBox? = null
        private set
    var transformation: Transformation = Transformation()
        private set

    open fun draw() {
        useTransformation {
            gl {
                glColor(color)
                useLight {
                    drawGl(primitive) {
                        points.forEach { glVertex3d(it.x, it.y, it.z) }
                    }
                }
            }
        }
    }

    fun addPoint(point: Point) {
        points.add(point)
        boundingBox = boundingBox?.refreshBBox(point) ?: BoundingBox(point)
    }
}