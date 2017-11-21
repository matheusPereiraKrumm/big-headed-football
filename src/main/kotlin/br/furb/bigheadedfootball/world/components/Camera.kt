package br.furb.bigheadedfootball.world.components

import br.furb.bigheadedfootball.common.glu
import br.furb.bigheadedfootball.world.objects.parts.Camp
import br.furb.bigheadedfootball.world.objects.parts.Player

class Camera {
    constructor(lookAt: Point, eye: Point) {
        this.lookAt = lookAt
        this.eye = eye
        this.topCam = Point(0.0, 1.0, 0.0)
    }
    private var isFromPlayer = false
    private lateinit var player: Player
    private var isFromCamp = false
    private lateinit var camp: Camp
    var canSeeLeft = false
    var canSeeRight = false

    var lookAt: Point
        get() = transformCamPoint(field)

    var eye: Point
        get() = transformCamPoint(field)
    var topCam : Point

    private fun transformCamPoint(point: Point): Point {
        var finalValue = point

        val radians = 1.0
        if(canSeeLeft)
            finalValue = returnRotationPoint(finalValue, radians)
        if(canSeeRight)
            finalValue = returnRotationPoint(finalValue, -radians)
        if(isFromPlayer)
            finalValue = player.transformation.transformPoint(finalValue)

        return finalValue
    }

    private fun returnRotationPoint(point: Point, radians: Double): Point {
        val transform = Transformation()
        transform.rotateY(radians)
        return transform.transformPoint(point)
    }

    constructor(camp: Camp) :
            this(Point(25.0,0.0,10.0),
                    Point(25.0,
                            50.0,
                            90.0)){
        isFromCamp = true
        this.camp = camp
    }

    constructor(player: Player) :
            this(Point( 1.5,  .4, -3.0),
                 Point(5.0, 10.0, 20.0)){
        isFromPlayer = true
        this.player = player
    }

    fun draw(){
        glu {
            gluLookAt(eye.x, eye.y, eye.z,
                    lookAt.x, lookAt.y, lookAt.z,
                    topCam.x, topCam.y, topCam.z)
        }
    }

}

