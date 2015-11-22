package com.packt.snake.desktop
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.stehno.starhunter.StarHunter
import groovy.transform.TypeChecked

@TypeChecked
public class DesktopLauncher {

    static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration()
        new LwjglApplication(new StarHunter(), config)
    }
}
