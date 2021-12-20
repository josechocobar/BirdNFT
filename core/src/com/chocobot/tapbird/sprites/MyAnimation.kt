package com.chocobot.tapbird.sprites

import com.badlogic.gdx.graphics.g2d.TextureRegion

class MyAnimation(
    val  frames: MutableList<TextureRegion> = mutableListOf<TextureRegion>(),
    var maxFrameTime:Float = 0f,
    var currentFrameTime:Float = 0f,
    var frame : Int =0,
    var frameCount : Int,
    var textureRegion: TextureRegion,
    var cycleTime : Float
) {
    init {
        val frameWidth = textureRegion.regionWidth/frameCount
        for (i in 0 until frameCount){
            frames.add(TextureRegion(textureRegion,i*frameWidth,0,frameWidth,textureRegion.regionHeight))
        }
        maxFrameTime = cycleTime/frameCount
    }
    fun update(dt:Float){
        currentFrameTime+=dt
        if (currentFrameTime > maxFrameTime){
            frame++
            currentFrameTime = 0f
        }
        if(frame >= frameCount){
            frame= 0
        }
    }
    fun getFrame(): TextureRegion{
        return frames.get(frame)
    }
}