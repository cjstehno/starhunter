package com.stehno.starhunter
import com.stehno.games.ResourceManager
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.Input
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Vector2f

import static com.stehno.starhunter.StarHunterResources.IMAGE_PLAYER_SHIP

/**
 * Represents the player's ship state.
 */
class Player {

    ResourceManager resourceManager

    private Image image
    private Vector2f position
    private float ceiling

    Vector2f getCannonPosition(){
        new Vector2f(
            position.x + (image.width / 2) as float,    // center of ship
            position.y                                  // top of the ship
        )
    }

    Player init( final GameContainer gc ) throws SlickException {
        image = resourceManager.loadImage( IMAGE_PLAYER_SHIP ).getScaledCopy( 0.25f )

        position = new Vector2f( (gc.width - image.width)/2 as float, gc.height-image.height-25 )

        // limit the player to the bottom of the screen
        ceiling = 2 * gc.height / 3

        return this
    }

    void update( final GameContainer gc, final int delta ) throws SlickException{
        Input input = gc.getInput()

        if( position.x > 0 && input.isKeyDown( Input.KEY_LEFT ) ){
            position.x -= 1 * delta
        }
        if( position.x < (gc.width-image.width) && input.isKeyDown( Input.KEY_RIGHT ) ){
            position.x += 1 * delta
        }

        if( position.y > ceiling && input.isKeyDown( Input.KEY_UP ) ){
            position.y -= 1 * delta
        }
        if( position.y < (gc.height-image.height) && input.isKeyDown( Input.KEY_DOWN ) ){
            position.y += 1 * delta
        }
    }

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        g.drawImage( image, position.x, position.y )
    }
}
