package com.chocobot.tapbird

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.chocobot.tapbird.states.GameStateManager
import com.chocobot.tapbird.states.MenuState

class BirdNFT : ApplicationAdapter() {
    companion object{
        val WIDTH = 480f
        val HEIGTH = 720f
        val TITLE = "Bird NFT para android"
        val TUBE_WIDTH: Float = 52f
        val MOVEMENT : Float = 100f
        val GROUND_OFFSET = -30f

    }
    lateinit var music : Music
    private lateinit var gameStateManager : GameStateManager
    private lateinit var batch: SpriteBatch
    override fun create() {
        batch = SpriteBatch()
        gameStateManager = GameStateManager()
        Gdx.gl.glClearColor(0f,1f,0f,1f)
        gameStateManager.push(MenuState(gameStateManager))
        initMusic()
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gameStateManager.update(Gdx.graphics.deltaTime)
        gameStateManager.render(batch)
    }

    override fun dispose() {
        batch.dispose()
        music.dispose()
    }
    fun initMusic(){
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"))
        music.isLooping = true
        music.volume = 0.10f
        music.play()
    }
}