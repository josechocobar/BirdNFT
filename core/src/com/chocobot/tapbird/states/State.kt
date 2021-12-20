package com.chocobot.tapbird.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class State(var gameStateManager: GameStateManager) {

    protected abstract fun handleInput()
    abstract fun update(dt:Float)
    abstract fun render(spriteBatch: SpriteBatch)
    abstract fun dispose()
}

