package br.furb.bigheadedfootball.aplication.componets

data class Point(var x: Double,
                   var y: Double,
                   var z: Double,
                   var w: Double = 1.0) {

    fun spread() = x to y to z

}