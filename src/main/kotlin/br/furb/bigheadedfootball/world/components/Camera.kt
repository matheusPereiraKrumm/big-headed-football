package br.furb.bigheadedfootball.world.components

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

    var lookAt: Point
        get() {
            if(isFromPlayer)
                return player.transformation.transformPoint(field)

            return field
        }
    var eye: Point
        get(){
            if(isFromPlayer)
                return player.transformation.transformPoint(field)

            return field
        }
    var topCam : Point


    constructor(camp: Camp) :
            this(Point(25.0,0.0,10.0),
                    Point(25.0,
                            50.0,
                            90.0)){
        isFromCamp = true
        this.camp = camp
    }

    constructor(player: Player) :
            this(Point( 2.0,  5.0, -10.0),
                 Point(6.0, 10.0, 20.0)){
        isFromPlayer = true
        this.player = player
    }

}

