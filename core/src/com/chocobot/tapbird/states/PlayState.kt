package com.chocobot.tapbird.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Array
import com.chocobot.tapbird.BirdNFT
import com.chocobot.tapbird.BirdNFT.Companion.GROUND_OFFSET
import com.chocobot.tapbird.BirdNFT.Companion.TUBE_WIDTH
import com.chocobot.tapbird.sprites.Bird
import com.chocobot.tapbird.sprites.Tube

class PlayState(gameStateManager: GameStateManager) : State(gameStateManager) {
    private var bird = Bird(50f, 320f)
    private val backgroung = Texture("background.png")
    private val ground :Texture = Texture("ground.png")
    private var groundPos1 = Vector2(0f,0f)
    private var groundPos2 = Vector2(0f,0f)
    private val TUBE_SPACING = 125F
    private val TUBE_COUNT = 4
    var camera = OrthographicCamera()
    var mouse = Vector3()
    var tubeList: MutableList<Tube> = mutableListOf<Tube>()

    init {
        camera.setToOrtho(false, BirdNFT.WIDTH / 2, BirdNFT.HEIGTH / 2)
        for (i in 1..4){
            tubeList.add(Tube(i * (TUBE_SPACING + BirdNFT.TUBE_WIDTH)))
        }
        groundPos1 = Vector2(camera.position.x-camera.viewportWidth/2, GROUND_OFFSET)
        groundPos2 = Vector2(camera.position.x-camera.viewportWidth/2+ground.width, GROUND_OFFSET)

    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump()

        }
    }

    override fun update(dt: Float) {
        handleInput()
        updateGround()
        bird.updatePosition(dt)
        camera.position.x = bird.position.x + 80f

        for (item in tubeList) {
            if (camera.position.x - (camera.viewportWidth / 2) > item.posTopTube.x + item.topTube.width) {
                item.reposition(item.posTopTube.x + (TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT)
            }
            if ((item.collides(bird.bound)) || bird.position.y == 0f){
                //bird.position.y=0f
                gameStateManager.set(MenuState(gameStateManager))
            }
        }
        if (bird.position.y <= ground.height+ GROUND_OFFSET){
            gameStateManager.set(MenuState(gameStateManager))
        }


        camera.update()

    }

    override fun render(spriteBatch: SpriteBatch) {
        spriteBatch.projectionMatrix = camera.combined
        spriteBatch.begin()
        spriteBatch.draw(
            backgroung,
            camera.position.x - (camera.viewportWidth) / 2,
            camera.position.y - (camera.viewportHeight) / 2
        )
        spriteBatch.draw(bird.getTexture(), bird.position.x, bird.position.y)

        if (tubeList.isNotEmpty()) {
            for (item in tubeList) {
                spriteBatch.draw(item.topTube, item.posTopTube.x, item.posTopTube.y)
                spriteBatch.draw(item.bottomTube, item.posBotTube.x, item.posBotTube.y)
            }
        }
        spriteBatch.draw(ground,groundPos1.x,groundPos1.y)
        spriteBatch.draw(ground,groundPos2.x,groundPos2.y)

        spriteBatch.end()
    }

    override fun dispose() {
        backgroung.dispose()
        ground.dispose()
        bird.dispose()
        for (item in tubeList){
            item.dispose()
        }
        System.out.println("PLAYSTATE DISPOSED")
    }
    fun updateGround(){
        if(camera.position.x - (camera.viewportWidth/2) > groundPos1.x + ground.width){
            groundPos1.add((ground.width*2).toFloat(),0f)
        }
        if(camera.position.x - (camera.viewportWidth/2) > groundPos2.x + ground.width){
            groundPos2.add((ground.width*2).toFloat(),0f)
        }
    }
}