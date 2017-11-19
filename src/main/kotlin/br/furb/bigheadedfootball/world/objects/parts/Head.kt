package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.objects.GraphicalObject

class Head : GraphicalObject() {
    override var size = 2.5F
    override var color: Color = Color.WHITE

    init {
        transformation.translation(0.0, 2.5, 0.0)
    }
}