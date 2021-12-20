package com.chocobot.tapbird.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.chocobot.tapbird.BirdNFT

class MenuState(gameStateManager: GameStateManager) : State(gameStateManager) {
    private val background = Texture("background.png")
    private val playButton = Texture("playbtn.png")
    var camera = OrthographicCamera()
    var mouse = Vector3()

    init {
        camera.setToOrtho(false, BirdNFT.WIDTH / 2, BirdNFT.HEIGTH / 2)
    }


    override fun handleInput() {

        if (Gdx.input.justTouched()) {
            gameStateManager.set(PlayState(gameStateManager))
        }

    }

    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(spriteBatch: SpriteBatch) {
        spriteBatch.projectionMatrix = camera.combined
        spriteBatch.begin()
        spriteBatch.draw(background, 0f, 0f)
        spriteBatch.draw(playButton, camera.position.x-playButton.width/2,camera.position.y)
        spriteBatch.end()
    }

    override fun dispose() {
        background.dispose()
        playButton.dispose()
    }
}