package com.stehno.starhunter.player

import com.stehno.games.ResourceManager
import com.stehno.games.Sprite
import org.newdawn.slick.GameContainer
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Vector2f

import static com.stehno.starhunter.StarHunterResources.getIMAGE_PLAYER_MISSILE

/**
 * Sprite implementation for the player missiles.
 */
class MissileSprite extends Sprite {

    private final Vector2f launchPosition

    MissileSprite( final ResourceManager resourceManager, final Vector2f launchPosition ){
        this.resourceManager = resourceManager
        this.launchPosition = launchPosition
    }

    @Override
    MissileSprite init( final GameContainer gc ) throws SlickException{
        aliveRenderable = resourceManager.loadImage( IMAGE_PLAYER_MISSILE )

        model.position = new Vector2f(
            launchPosition.x - (aliveRenderable.width / 2) as float,
            launchPosition.y
        )

        model.collisionBounds = new Rectangle(
            model.position.x,
            model.position.y,
            aliveRenderable.width,
            aliveRenderable.height
        )

        return this
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException {
        move( 0f, -2f, delta )

        if( model.collisionBounds.y < 0 ){
            model.destroy()
        }
    }
}
