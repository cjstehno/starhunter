package com.stehno.games.ui
import com.stehno.games.LifecycleParticipant
import groovy.transform.Immutable
import org.newdawn.slick.geom.Vector2f
/**
 * The base class for UI components.
 */
abstract class Component implements LifecycleParticipant<Component> {

    Box padding = Box.empty()
    Vector2f position = new Vector2f(0f,0f)

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
