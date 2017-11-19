package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.objects.GraphicalObject

open class Player : GraphicalObject() {
    override var size = 1F
    override var color: Color = Color.YELLOW
    private var head: Head = Head()

    init {
        transformationDistortion.scale(2.0, 7.0, 2.0)
        transformation.translation(0.0, 3.5, 0.0)

        childGraphicalObjects.add(head)
    }
}