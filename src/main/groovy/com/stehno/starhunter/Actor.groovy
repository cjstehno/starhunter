package com.stehno.starhunter
import com.stehno.games.ResourceManager
import org.newdawn.slick.*
import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Vector2f
/**
 * Specifies the standard game workflow for a game character (or actor).
 */
abstract class Actor {

    ResourceManager resourceManager

    protected static enum State { ALIVE, DYING, DEAD }
    protected State state = State.ALIVE
    protected Renderable aliveRenderable, dyingRenderable
    protected Vector2f position
    protected Shape bounds

    boolean isAlive(){ state == State.ALIVE }

    Shape getBounds(){ bounds }

    void kill(){
        if( state == State.ALIVE ) state = State.DYING
    }

    abstract Actor init( final GameContainer gc ) throws SlickException

    abstract void update( final GameContainer gc, final int delta ) throws SlickException

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        switch( state ){
            case State.ALIVE:
                aliveRenderable.draw( position.x, position.y )
                break

            case State.DYING:
                def renderable = ( dyingRenderable ?: aliveRenderable )
                renderable.draw( position.x, position.y )

                // TODO: a bit of a hack, may want Animation rather than Renderable for all
                if( renderable instanceof Animation ){
                    if( renderable.stopped ){
                        state = State.DEAD
                    }
                } else {
                    state = State.DEAD
                }

                break

            case State.DEAD:
                // nothing
                break
        }

        // FIXME: temp collision box - might be good debug option
//        g.setColor( Color.yellow )
//        g.draw( bounds )
    }

    boolean colliding( Actor other ){
        alive && other.alive && bounds.intersects( other.bounds )
    }
}