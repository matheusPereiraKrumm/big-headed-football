package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.objects.GraphicalObject

class Goal : GraphicalObject() {
    override var size = 1F
    override var color: Color = Color.BLACK

    init {
        transformationDistortion.scale(15.0, 10.0, 2.0)
        transformation.translation(25.0, 5.0, -1.0)
    }
}