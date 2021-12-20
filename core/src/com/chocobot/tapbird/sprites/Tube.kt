package com.chocobot.tapbird.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

data class Tube(
    var x: Float,
    val topTube: Texture = Texture("toptube.png"),
    val bottomTube: Texture = Texture("bottomtube.png"),
    val TUBE_WIDTH: Float = 52f,
    val FLUCTUATION: Int = 130,
    val TUBE_GAP: Float = 100f,
    val LOWEST_OPENING: Float = 120f,
    var posTopTube: Vector2 = Vector2(x, (0..130).random() + TUBE_GAP + LOWEST_OPENING),
    var posBotTube: Vector2 = Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.height),
    var boundTop : Rectangle= Rectangle(posTopTube.x,posTopTube.y,
        topTube.width.toFloat(), topTube.height.toFloat()
    ),
    var boundBottom : Rectangle = Rectangle(posBotTube.x,posBotTube.y,
        bottomTube.width.toFloat(), bottomTube.height.toFloat()
    )

) {
    fun reposition(x:Float){
        posTopTube.set(x, (0..130).random() + TUBE_GAP + LOWEST_OPENING)
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.height)
        boundTop.setPosition(posTopTube.x,posTopTube.y)
        boundBottom.setPosition(posBotTube.x,posBotTube.y)
    }
    fun collides(player: Rectangle): Boolean {
        return player.overlaps(boundTop) || player.overlaps(boundBottom)
    }

    fun dispose() {
        topTube.dispose()
        bottomTube.dispose()
    }

}