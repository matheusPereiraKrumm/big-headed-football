package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.common.glut
import br.furb.bigheadedfootball.world.components.Color

class MainPlayer : Player() {
    override var color: Color = Color.RED

    init {

        transformation.translation(25.0, 0.0, 60.0)
    }
}