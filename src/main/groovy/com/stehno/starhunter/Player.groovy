package com.stehno.starhunter
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Input
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Vector2f

import static com.stehno.starhunter.StarHunterResources.IMAGE_PLAYER_SHIP
/**
 * Represents the player's ship state.
 */
class Player extends Actor {

    private float ceiling

    Vector2f getCannonPosition(){
        new Vector2f(
            position.x + (aliveRenderable.width / 2) as float,    // center of ship
            position.y                                  // top of the ship
        )
    }

    Player init( final GameContainer gc ) throws SlickException {
        aliveRenderable = resourceManager.loadImage( IMAGE_PLAYER_SHIP ).getScaledCopy( 0.25f )

        position = new Vector2f( (gc.width - aliveRenderable.width)/2 as float, gc.height-aliveRenderable.height-25 )

        bounds = new Rectangle( position.x, position.y, aliveRenderable.width, aliveRenderable.height )

        // limit the player to the bottom of the screen
        ceiling = 2 * gc.height / 3

        return this
    }

    void update( final GameContainer gc, final int delta ) throws SlickException{
        Input input = gc.getInput()

        if( position.x > 0 && input.isKeyDown( Input.KEY_LEFT ) ){
            position.x -= 1 * delta
            bounds.x -= 1 * delta
        }
        if( position.x < (gc.width-aliveRenderable.width) && input.isKeyDown( Input.KEY_RIGHT ) ){
            position.x += 1 * delta
            bounds.x += 1 * delta
        }

        if( position.y > ceiling && input.isKeyDown( Input.KEY_UP ) ){
            position.y -= 1 * delta
            bounds.y -= 1 * delta
        }
        if( position.y < (gc.height-aliveRenderable.height) && input.isKeyDown( Input.KEY_DOWN ) ){
            position.y += 1 * delta
            bounds.y += 1 * delta
        }
    }
}
