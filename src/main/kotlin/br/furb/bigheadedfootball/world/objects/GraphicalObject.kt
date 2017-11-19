package br.furb.bigheadedfootball.world.objects

import br.furb.bigheadedfootball.common.*
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.components.Transformation

abstract class GraphicalObject {
    abstract var primitive: Int
    abstract var color: Color
    abstract var size: Float
    var points: ArrayList<Point> = ArrayList()
        private set
    var boundingBox: BoundingBox? = null
        private set
    var transformation: Transformation = Transformation()
        protected set
    var transformationDistortion: Transformation = Transformation()
        protected set
    var childGraphicalObjects: ArrayList<GraphicalObject> = ArrayList()
        private set

/*
    open fun draw() {
        useTransformation {
            gl {
                glColor(color)
                useLight {
                    drawGl(primitive) {
                        points.forEach { glVertex3d(it.x, it.y, it.z) }
                    }
                }
                childGraphicalObjects.forEach { it.draw() }
            }
        }
    }
*/
    fun draw() {
        useTransformation {
            gl {
                useTransformation(transformationDistortion){
                    glColor(color)
                    useLight {
                        glut{
                            glutSolidCube(size)
                        }
                    }
                }
                childGraphicalObjects.forEach { it.draw() }
            }
        }
    }

    fun addPoint(point: Point) {
        points.add(point)
        boundingBox = boundingBox?.refreshBBox(point) ?: BoundingBox(point)
    }
}