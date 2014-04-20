package com.stehno.games

import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Vector2f
/**
 * A SpriteModel contains the state data relevant to rendering and managing a sprite. This data should be
 * inclusive of all data required to render a given sprite.
 */
interface SpriteModel {

    public static enum Status { ALIVE, DYING, DEAD }

    Status currentStatus()

    Vector2f getPosition()

    void setPosition( final Vector2f position )

    void destroy()
    void kill()

    boolean isAlive()

    boolean isDead()

    boolean intersects( final SpriteModel other )

    Shape getCollisionBounds()
}


