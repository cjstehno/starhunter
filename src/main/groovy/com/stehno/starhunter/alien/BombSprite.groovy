package com.stehno.starhunter.alien

import com.stehno.games.ResourceManager
import com.stehno.games.Sprite
import org.newdawn.slick.GameContainer
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Vector2f

import static com.stehno.starhunter.StarHunterResources.getIMAGE_ALIEN_BOMB

/**
 * Sprite representing the alien bombs.
 */
class BombSprite extends Sprite {

    private final Vector2f dropPosition
    private final float speed

    BombSprite( final ResourceManager resourceManager, final Vector2f dropPosition, final float speed ){
        this.resourceManager = resourceManager
        this.dropPosition = dropPosition
        this.speed = speed
    }

    @Override
    BombSprite init( final GameContainer gc ) throws SlickException{
        aliveRenderable = resourceManager.loadImage( IMAGE_ALIEN_BOMB )

        model.position = new Vector2f(
            dropPosition.x - (aliveRenderable.width / 2) as float,
            dropPosition.y
        )

        model.collisionBounds = new Rectangle(
            model.position.x + aliveRenderable.width/3 as float,
            model.position.y + aliveRenderable.height/3 as float,
            aliveRenderable.width/3 as float,
            aliveRenderable.height/3 as float
        )

        return this
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException{
        move 0f, speed, delta

        if( model.collisionBounds.y > gc.height ){
            model.destroy()
        }
    }
}
