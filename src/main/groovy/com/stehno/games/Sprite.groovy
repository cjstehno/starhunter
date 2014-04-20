package com.stehno.games

import org.newdawn.slick.Animation
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Renderable
import org.newdawn.slick.SlickException

import static com.stehno.games.SpriteModel.Status.ALIVE
import static com.stehno.games.SpriteModel.Status.DEAD
import static com.stehno.games.SpriteModel.Status.DYING

/**
 * Defines a sprite/character/actor in the game. A Sprite is responsible for rendering the data represented
 * by a SpriteModel. It should handle the visual and rendering logic.
 *
 * By default, a DefaultSpriteModel will be used as the configured SpriteModel.
 */
abstract class Sprite {

    ResourceManager resourceManager
    SpriteModel model = new DefaultSpriteModel()

    protected Renderable aliveRenderable
    protected Renderable dyingRenderable

    abstract Sprite init( final GameContainer gc ) throws SlickException

    abstract void update( final GameContainer gc, final int delta ) throws SlickException

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        switch( model.currentStatus() ){
            case ALIVE:
                aliveRenderable.draw( model.position.x, model.position.y )
                break

            case DYING:
                def renderable = ( dyingRenderable ?: aliveRenderable )
                renderable.draw( model.position.x, model.position.y )

                // TODO: a bit of a hack, may want Animation rather than Renderable for all
                if( renderable instanceof Animation ){
                    if( renderable.stopped ){
                        model.destroy()
                    }
                } else {
                    model.destroy()
                }

                break

            case DEAD:
                // nothing
                break
        }

        // FIXME: temp collision box - might be good debug option
//        g.setColor( Color.yellow )
//        g.draw( bounds )
    }

    /**
     * Determines whether or not this sprite is colliding with the specified sprite at this point in time.
     *
     * @param other the other sprite
     * @return true if the two sprites are colliding
     */
    boolean colliding( final Sprite other ){
        model.alive && other.model.alive && model.intersects( other.model )
    }

    void move( final float x, final float y, final int delta ){
        model.position.y += x * delta
        model.collisionBounds.y += y * delta
    }
}
