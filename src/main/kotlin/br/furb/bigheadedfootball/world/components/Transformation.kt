package br.furb.bigheadedfootball.world.components

import br.furb.bigheadedfootball.world.objects.GraphicalObject

class Transformation {
    private val identity = doubleArrayOf(
            1.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 1.0)
    private var matrix = identity.clone()

    fun reset() {
        matrix = identity.clone()
    }

    fun translation(point: Point) {
        translation(point.x, point.y, point.z)
    }

    fun translation(x: Double, y: Double, z: Double) {
        val aux = identity.clone()
        aux[12] = x
        aux[13] = y
        aux[14] = z
        multMatrix(aux)
    }

    fun scale(x: Double, y: Double, z: Double) {
        val aux = identity.clone()
        aux[0] = x
        aux[5] = y
        aux[10] = z
        multMatrix(aux)
    }

    fun multMatrix(t: Transformation){
        multMatrix(t.matrix)
    }

    fun multMatrix(aux: DoubleArray){
        val result = identity.clone()
        for (i in 0..15)
            result[i] = aux[i % 4] * matrix[i / 4 * 4] +
                    aux[i % 4 + 4] * matrix[i / 4 * 4 + 1] +
                    aux[i % 4 + 8] * matrix[i / 4 * 4 + 2] +
                    aux[i % 4 + 12] * matrix[i / 4 * 4 + 3]
        matrix = result
    }

    fun transformPoint(point: Point): Point {
        return Point(
                matrix[0] * point.x + matrix[4] * point.y + matrix[8] * point.z + matrix[12] * point.w,
                matrix[1] * point.x + matrix[5] * point.y + matrix[9] * point.z + matrix[13] * point.w,
                matrix[2] * point.x + matrix[6] * point.y + matrix[10] * point.z + matrix[14] * point.w,
                matrix[3] * point.x + matrix[7] * point.y + matrix[11] * point.z + matrix[15] * point.w)
    }

    fun GetDate(): DoubleArray {
        return matrix
    }

    fun rotateY(radians: Double) {
        val aux = identity.clone()
        aux[0] = Math.cos(radians)
        aux[8] = Math.sin(radians)
        aux[2] = -Math.sin(radians)
        aux[10] = Math.cos(radians)
        multMatrix(aux)
    }

    fun showMatrix() {

        println("______________________")
        println("|" + matrix[0] + " | " + matrix[4] + " | " + matrix[8]  + " | " + matrix[12])
        println("|" + matrix[1] + " | " + matrix[5] + " | " + matrix[9]  + " | " + matrix[13])
        println("|" + matrix[2] + " | " + matrix[6] + " | " + matrix[10] + " | " + matrix[14])
        println("|" + matrix[3] + " | " + matrix[7] + " | " + matrix[11] + " | " + matrix[15])
    }

    fun rotation(radians: Double){
        transformWithPoint(PointCommom.neutralPoint()) {
            rotateY(radians)
        }
    }

    fun rotation(radians: Double, point: Point) {
        transformWithPoint(point) {
            rotateY(radians)
        }
    }

    private fun transformWithPoint(center: Point, block: () -> Unit) {
        val centerPoint = transformPoint(center)
        val inverted = centerPoint.inverted()
        translation(inverted)
        block()
        translation(centerPoint)
    }
}