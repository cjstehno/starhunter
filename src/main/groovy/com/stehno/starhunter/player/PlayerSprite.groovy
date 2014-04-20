package com.stehno.starhunter.player

import com.stehno.games.Sprite
import com.stehno.starhunter.StarHunterResources
import org.newdawn.slick.Animation
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Input
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Vector2f
/**
 * Sprite representing the player (our hero).
 */
class PlayerSprite extends Sprite {

    private float ceiling

    /**
     * Used to retrieve the current position of the player's missile launcher.
     * @return
     */
    Vector2f getLauncherPosition(){
        new Vector2f(
            model.position.x + (aliveRenderable.width / 2) as float,    // center of ship
            model.position.y                                            // top of the ship
        )
    }

    @Override
    PlayerSprite init( final GameContainer gc ) throws SlickException{
        aliveRenderable = resourceManager.loadImage( StarHunterResources.IMAGE_PLAYER_SHIP ).getScaledCopy( 0.25f )

        dyingRenderable = new Animation(
            resourceManager.loadImages( StarHunterResources.IMAGES_EXPLOSION ),
            50
        )
        dyingRenderable.looping = false

        model.position = new Vector2f(
            (gc.width - aliveRenderable.width)/2 as float,
            gc.height-aliveRenderable.height-25
        )

        model.collisionBounds = new Rectangle(
            model.position.x,
            model.position.y,
            aliveRenderable.width,
            aliveRenderable.height
        )

        // limit the player to the bottom of the screen
        ceiling = 2 * gc.height / 3

        return this
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException{
        if( model.alive ){
            Input input = gc.getInput()

            if( model.position.x > 0 && input.isKeyDown( Input.KEY_LEFT ) ){
                move( -1f, 0f, delta )
            }
            if( model.position.x < (gc.width-aliveRenderable.width) && input.isKeyDown( Input.KEY_RIGHT ) ){
                move( 1f, 0f, delta )
            }

            if( model.position.y > ceiling && input.isKeyDown( Input.KEY_UP ) ){
                move( 0f, -1f, delta )
            }
            if( model.position.y < (gc.height-aliveRenderable.height) && input.isKeyDown( Input.KEY_DOWN ) ){
                move( 0f, 1f, delta )
            }
        }
    }
}
