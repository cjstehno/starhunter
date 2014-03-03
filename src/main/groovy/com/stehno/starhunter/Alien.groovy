package com.stehno.starhunter
import org.newdawn.slick.GameContainer
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Vector2f

import static com.stehno.starhunter.StarHunterResources.IMAGE_ALIEN_SHIP
/**
 * Created by cjstehno on 3/2/14.
 */
class Alien extends Actor {

    @Override
    Alien init( final GameContainer gc ) throws SlickException {
        image = resourceManager.loadImage( IMAGE_ALIEN_SHIP ).getScaledCopy( 0.12f )

        // FIXME: temp starting point
        position = new Vector2f(200f, 200f)
        bounds = new Rectangle( position.x, position.y, image.width, image.height )

        return this
    }

    @Override
    void update( final GameContainer gc, final int delta ) throws SlickException{
    }
}
