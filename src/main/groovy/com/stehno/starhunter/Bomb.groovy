package com.stehno.starhunter
import org.newdawn.slick.GameContainer
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Vector2f

import static com.stehno.starhunter.StarHunterResources.IMAGE_ALIEN_BOMB
/**
 * Created by cjstehno on 3/9/14.
 */
class Bomb extends Actor {

    Vector2f dropPosition
    float speed

    @Override
    Bomb init( final GameContainer gc ) throws SlickException{
        aliveRenderable = resourceManager.loadImage( IMAGE_ALIEN_BOMB )

        position = new Vector2f(
            dropPosition.x - (aliveRenderable.width / 2) as float,
            dropPosition.y
        )

        bounds = new Rectangle( position.x, position.y, aliveRenderable.width, aliveRenderable.height )

        return this
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException {
        float movement = speed * delta
        position.y +=  movement
        bounds.y += movement

        if( bounds.y > gc.height ){
            destroy()
        }
    }
}
