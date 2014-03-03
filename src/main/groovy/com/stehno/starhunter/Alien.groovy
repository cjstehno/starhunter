package com.stehno.starhunter
import org.newdawn.slick.Animation
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Image
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Vector2f

import static com.stehno.starhunter.StarHunterResources.IMAGES_ALIEN_SHIP
import static com.stehno.starhunter.StarHunterResources.IMAGES_EXPLOSION

/**
 * TODO: document
 */
class Alien extends Actor {

    @Override
    Alien init( final GameContainer gc ) throws SlickException {
        aliveRenderable = new Animation(
            resourceManager.loadImages( IMAGES_ALIEN_SHIP )*.getScaledCopy( 0.12f ) as Image[],
            750
        )

        dyingRenderable = new Animation(
            resourceManager.loadImages( IMAGES_EXPLOSION ),
            100
        )
        dyingRenderable.looping = false

        // FIXME: temp starting point
        position = new Vector2f(200f, 200f)
        bounds = new Rectangle( position.x, position.y, aliveRenderable.width, aliveRenderable.height )

        return this
    }

    @Override
    void update( final GameContainer gc, final int delta ) throws SlickException{
    }
}
