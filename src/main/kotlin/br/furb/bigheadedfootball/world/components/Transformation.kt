package br.furb.bigheadedfootball.world.components

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
            result[i] = matrix[i % 4] * aux[i / 4 * 4] +
                    matrix[i % 4 + 4] * aux[i / 4 * 4 + 1] +
                    matrix[i % 4 + 8] * aux[i / 4 * 4 + 2] +
                    matrix[i % 4 + 12] * aux[i / 4 * 4 + 3]
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
}