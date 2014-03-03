package com.stehno.starhunter

import com.stehno.games.ResourceManager
import org.newdawn.slick.Color
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Vector2f

/**
 * Specifies the standard game workflow for a game character (or actor).
 */
abstract class Actor {
    // TODO: support for animated actors (sprite sheet?)
    // TODO: support for other image states (like dying explosion)

    ResourceManager resourceManager
    boolean alive = true

    protected Image image
    protected Vector2f position
    protected Shape bounds

    abstract Actor init( final GameContainer gc ) throws SlickException

    abstract void update( final GameContainer gc, final int delta ) throws SlickException

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        // FIXME: need to see if this is best way to handle deads
        if( alive ){
            g.drawImage( image, position.x, position.y )

            // FIXME: temp collision box - might be good debug option
            g.setColor( Color.yellow )
            g.draw( bounds )
        }
    }

    boolean colliding( Actor other ){
        alive && other.alive && bounds.intersects( other.bounds )
    }

    Shape getBounds(){ bounds }
}