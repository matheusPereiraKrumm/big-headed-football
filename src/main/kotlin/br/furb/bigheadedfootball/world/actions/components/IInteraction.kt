package br.furb.bigheadedfootball.world.actions.components

import br.furb.bigheadedfootball.world.actions.common.DataInputs

interface IInteraction {
    fun hasChange(dataInputs: DataInputs): Boolean
    fun executeChange(dataInputs: DataInputs)
}