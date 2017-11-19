package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.objects.GraphicalObject

class Camp : GraphicalObject() {
    override var size = 1F
    override var color: Color = Color.CAMP

    init {
        transformation.scale(50.0, 1.0, 140.0)
        transformation.translation(25.0, -.5, 70.0)
    }

}