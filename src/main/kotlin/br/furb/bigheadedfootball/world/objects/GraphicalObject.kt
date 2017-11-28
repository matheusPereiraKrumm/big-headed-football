package br.furb.bigheadedfootball.world.objects

import br.furb.bigheadedfootball.common.*
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.Point
import br.furb.bigheadedfootball.world.components.Transformation


abstract class GraphicalObject {
    abstract var color: Color
    abstract var size: Float
    var boundingBox: BoundingBox? = null
        private set
    var transformation: Transformation = Transformation()
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
                        innerDraw()
                    }
                }
                childGraphicalObjects.forEach { it.draw() }
            }
        }
        drawBBox()
    }

    open fun drawBBox() {

    }

    protected open fun innerDraw() {
        glut{
            glutSolidCube(size)
        }
    }

    fun boundingBoxUpdated(): BoundingBox = boundingBoxUpdated(transformation)

    fun boundingBoxUpdated(transformationOther: Transformation): BoundingBox {
        val bBox = newBBox(transformationOther)

        childGraphicalObjects.forEach { it.apoendInBBox(bBox, transformationOther) }

        return bBox
    }

    protected open fun newBBox(transformationOther: Transformation): BoundingBox {
        val (minPoint, maxPoint) = minMaxPoint(transformationOther)

        val bBox = BoundingBox(minPoint)
        bBox.refreshBBox(maxPoint)
        return bBox
    }

    protected fun tamanhoToBBox() = size.toDouble()/2

    private fun apoendInBBox(bBox: BoundingBox, parentTransformation: Transformation) {
        val (minPoint, maxPoint) = minMaxPointWithParentTransform(parentTransformation)

        bBox.refreshBBox(minPoint)
        bBox.refreshBBox(maxPoint)
    }

    protected fun realTransformPointWithParentTransform(parentTransformation: Transformation, point: Point): Point =
            parentTransformation.transformPoint(realTransformPoint(transformation, point))

    protected fun realTransformPoint(transformation: Transformation, minPoint: Point): Point =
            transformation.transformPoint(transformationDistortion.transformPoint(minPoint))


    open fun minMaxPointWithParentTransform(transformationOther: Transformation): Pair<Point, Point>{
        val minPoint = realTransformPointWithParentTransform(transformationOther, Point(-tamanhoToBBox(), -tamanhoToBBox(),-tamanhoToBBox()))
        val maxPoint = realTransformPointWithParentTransform(transformationOther, Point(tamanhoToBBox(), tamanhoToBBox(),tamanhoToBBox()))
        return minPoint to maxPoint
    }

    open fun minMaxPoint(transformationOther: Transformation): Pair<Point, Point>{
        val minPoint = realTransformPoint(transformationOther, Point(-tamanhoToBBox(), -tamanhoToBBox(),-tamanhoToBBox()))
        val maxPoint = realTransformPoint(transformationOther, Point(tamanhoToBBox(), tamanhoToBBox(),tamanhoToBBox()))
        return minPoint to maxPoint
    }
}