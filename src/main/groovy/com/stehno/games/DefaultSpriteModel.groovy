package com.stehno.games

import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Vector2f

import static com.stehno.games.SpriteModel.Status.ALIVE
import static com.stehno.games.SpriteModel.Status.DEAD
import static com.stehno.games.SpriteModel.Status.DYING

/**
 * Simple default implementation of a SpriteModel. This class should be extended to provide extra or custom
 * behavior for a sprite.
 */
class DefaultSpriteModel implements SpriteModel {

    Vector2f position
    Shape collisionBounds

    private SpriteModel.Status status = ALIVE

    @Override
    SpriteModel.Status currentStatus(){ status }

    @Override
    void kill(){
        if( status == ALIVE ) status = DYING
    }

    @Override
    void destroy(){
        status = DEAD
    }

    @Override
    boolean isAlive(){ status == ALIVE }

    @Override
    boolean isDead(){ status == DEAD }

    @Override
    boolean intersects( final SpriteModel other ){
        collisionBounds.intersects( other.collisionBounds )
    }
}
