package com.stehno.starhunter

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.stehno.starhunter.screens.GameScreen
import groovy.transform.TypeChecked

import static com.badlogic.gdx.Application.LOG_DEBUG

@TypeChecked
class StarHunter extends ApplicationAdapter {

    private Screen screen
    private SpriteBatch batch

    @Override
    void create() {
        Gdx.app.setLogLevel LOG_DEBUG

        Assets.instance.init(new AssetManager())

        setScreen(new GameScreen())
    }

    private void setScreen(Screen screen) {
        screen.show()
        screen.resize(Gdx.graphics.width, Gdx.graphics.height)
        screen.pause()
        this.screen = screen
    }

    @Override
    void render() {
        float deltaTime = Math.min(Gdx.graphics.deltaTime, (1.0f / 60.0f) as float)

        screen?.render(deltaTime)
    }

    @Override
    void resize(int width, int height) {
        screen?.resize(width, height)
    }

    @Override
    void pause() {
        screen?.pause()
    }

    @Override
    void resume() {
        screen?.resume()
    }

    @Override
    void dispose() {
        screen?.hide()
        batch.dispose()
    }
}
