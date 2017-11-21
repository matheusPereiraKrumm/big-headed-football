package br.furb.bigheadedfootball.world.actions.common

import br.furb.bigheadedfootball.world.World

class DataInputs(val world: World) {
    val keysPressed = HashSet<Int>()
    val keysReleased = HashSet<Int>()
    fun mainPlayer() = world.mainPlayer
    fun cam() = world.camera
}