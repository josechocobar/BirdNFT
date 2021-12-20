package com.chocobot.tapbird.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.*

class GameStateManager {
    var states = Stack<State>()
    fun push(state: State){
        states.push(state)
    }
    fun pop(){
        states.pop()
    }
    fun set(state: State){
        states.pop().dispose()
        states.push(state)
    }
    fun update(deltaTime : Float){
        states.peek().update(deltaTime)
    }
    fun render(spriteBatch : SpriteBatch){
        states.peek().render(spriteBatch)
    }
}