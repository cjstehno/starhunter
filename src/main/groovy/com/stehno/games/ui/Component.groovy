package com.stehno.games.ui

import groovy.transform.Immutable
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Vector2f

/**
 * The base class for UI components.
 */
abstract class Component {

    Box padding = Box.empty()
    Vector2f position = new Vector2f(0f,0f)

    abstract Component init( final GameContainer gc ) throws SlickException

    abstract void update( final GameContainer gc, final int delta ) throws SlickException

    abstract void render( final GameContainer gc, final Graphics g ) throws SlickException

    float getWidth(){
        padding.left + getComponentWidth() + padding.right
    }

    float getHeight(){
        padding.top + getComponentHeight() + padding.bottom
    }

    abstract float getComponentWidth()

    abstract float getComponentHeight()
}

@Immutable
class Box {
    float top
    float bottom
    float left
    float right

    static empty(){
        new Box(0.0f, 0.0f, 0.0f, 0.0f)
    }
}
