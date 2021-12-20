package com.chocobot.tapbird.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.chocobot.tapbird.BirdNFT
import com.chocobot.tapbird.BirdNFT.Companion.HEIGTH
import com.chocobot.tapbird.BirdNFT.Companion.MOVEMENT

class Bird(
    var x: Float,
    var y: Float,
    var position: Vector3 = Vector3(x, y, 0f),
    var velocity: Vector3 = Vector3(0f, 1f, 0f),
    val gravity: Float = -10f,
    val maxHeight : Float = BirdNFT.HEIGTH/2,

    var texture: Texture = Texture("birdanimation.png"),
    var birdAnimation: MyAnimation = MyAnimation(
        textureRegion = TextureRegion(texture),
        frameCount = 3,
        cycleTime = 0.5f
    ),
    var bound: Rectangle = Rectangle(
        position.x, position.y, texture.width / 3.toFloat(),
        texture.height.toFloat()
    ),
    val flap : Sound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"))

) {
    fun updatePosition(deltaTime: Float) {
        birdAnimation.update(deltaTime)
        when {
            position.y > 0 -> {

                velocity.add(0f, gravity, 0f)
                velocity.scl(deltaTime)
                position.add(MOVEMENT * deltaTime, velocity.y, 0f)
                velocity.scl(1 / deltaTime)
                bound.setPosition(position.x, position.y)
            }
            position.y < 0 -> {
                position.y = 0f
            }
        }




    }

    fun getTexture(): TextureRegion {
        return birdAnimation.getFrame()
    }

    fun jump() {
        if (velocity.y >250f){
            velocity.y = 50f
        }
        if (position.y <= maxHeight - texture.height){
            velocity.add(0f, 250f, 0f)
            flap.play()
        }else{
            velocity.y = 0f
            position.y = maxHeight - texture.height
        }
    }

    fun dispose() {
        texture.dispose()
        flap.dispose()
    }
}