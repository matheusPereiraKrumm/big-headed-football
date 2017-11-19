package br.furb.bigheadedfootball.world.objects

import br.furb.bigheadedfootball.common.*
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Transformation

abstract class GraphicalObject {
    abstract var color: Color
    abstract var size: Float
    var boundingBox: BoundingBox? = null
        private set
    var transformation: Transformation = Transformation()
        protected set
    var transformationDistortion: Transformation = Transformation()
        protected set
    var childGraphicalObjects: ArrayList<GraphicalObject> = ArrayList()
        private set

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
}