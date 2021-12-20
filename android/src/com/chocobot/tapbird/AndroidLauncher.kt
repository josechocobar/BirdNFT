package com.chocobot.tapbird

import com.badlogic.gdx.backends.android.AndroidApplication
import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy
import com.chocobot.tapbird.BirdNFT

class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        config.resolutionStrategy.calcMeasures(BirdNFT.WIDTH.toInt(), BirdNFT.HEIGTH.toInt())
        initialize(BirdNFT(), config)
    }
}