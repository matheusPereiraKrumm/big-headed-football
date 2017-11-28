package br.furb.bigheadedfootball.world.components

data class Point(var x: Double,
                 var y: Double,
                 var z: Double,
                 var w: Double = 1.0) {

    fun diff(otherPoint: Point) = Point(
            x - otherPoint.x,
            y - otherPoint.y,
            z - otherPoint.z)

    fun inverted() = Point(-x, -y, -z)
    fun innerIn2D(smallerPoint: Point, biggerPoint: Point): Boolean {
        return (smallerPoint.x <= x && x <= biggerPoint.x &&
                smallerPoint.z <= z && z <= biggerPoint.z)
    }
}

