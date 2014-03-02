package com.stehno.starhunter
import com.stehno.games.ResourceManager
import org.newdawn.slick.Color
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Vector2f

import static com.stehno.starhunter.StarHunterResources.getIMAGE_ALIEN_SHIP
/**
 * Created by cjstehno on 3/2/14.
 */
class Alien {

    ResourceManager resourceManager

    private Image image
    private Vector2f position
    private Rectangle bounds

    Alien init( final GameContainer gc ) throws SlickException {
        image = resourceManager.loadImage( IMAGE_ALIEN_SHIP ).getScaledCopy( 0.12f )

        // FIXME: temp starting point
        position = new Vector2f(200f, 200f)
        bounds = new Rectangle( position.x, position.y, image.width, image.height )

        return this
    }

    void update( final GameContainer gc, final int delta ) throws SlickException{
    }

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        g.drawImage( image, position.x, position.y )

        g.setColor( Color.yellow )
        g.draw( bounds )
    }
}
