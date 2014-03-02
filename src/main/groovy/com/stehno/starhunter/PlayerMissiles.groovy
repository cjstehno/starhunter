package com.stehno.starhunter
import com.stehno.games.ResourceManager
import org.newdawn.slick.*
import org.newdawn.slick.geom.Vector2f

import static com.stehno.starhunter.StarHunterResources.getAUDIO_PLAYER_MISSILE
import static com.stehno.starhunter.StarHunterResources.getIMAGE_PLAYER_MISSILE

/**
 * Represents and manages the player ship missiles.
 */
class PlayerMissiles {

    ResourceManager resourceManager
    Player player

    private Map<Image,Vector2f> actives = [:]
    private Image image
    private Sound sound

    PlayerMissiles init( final GameContainer gc ) throws SlickException {
        image = resourceManager.loadImage( IMAGE_PLAYER_MISSILE )
        sound = resourceManager.loadSound( AUDIO_PLAYER_MISSILE )

        return this
    }

    void update( final GameContainer gc, final int delta ) throws SlickException{
        Input input = gc.getInput()

        // check for missile fire
        if( input.isKeyPressed( Input.KEY_SPACE ) && actives.size() < 5 ){
            sound.play()

            def cannon = player.cannonPosition
            actives[image.copy()] = new Vector2f(
                cannon.x - (image.width / 2) as float,
                cannon.y
            )
        }

        def outOfRange = []

        // update missile locations
        actives.each { img, pos->
            pos.y -= 2 * delta
            if( pos.y < 0 ){
                outOfRange << img
            }
        }

        outOfRange.each {
            actives.remove( it )
        }
    }

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        actives.each { img, pos->
            g.drawImage( img, pos.x, pos.y )
        }
    }
}
