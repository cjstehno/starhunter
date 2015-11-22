package com.stehno.starhunter.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import groovy.transform.TypeChecked

import static com.badlogic.gdx.graphics.Color.BLACK

@TypeChecked
class GameScreen extends ScreenAdapter {

    @Override
    void render(float delta) {

        Gdx.gl.glClearColor BLACK.r, BLACK.g, BLACK.b, BLACK.a
        Gdx.gl.glClear GL20.GL_COLOR_BUFFER_BIT
    }
}
