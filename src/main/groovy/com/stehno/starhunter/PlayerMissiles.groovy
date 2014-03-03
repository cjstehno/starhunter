package com.stehno.starhunter
import com.stehno.games.ResourceManager
import org.newdawn.slick.*

import static com.stehno.starhunter.StarHunterResources.getAUDIO_PLAYER_MISSILE
/**
 * Represents and manages the player ship missiles.
 */
class PlayerMissiles {

    ResourceManager resourceManager
    Player player

    private Set<Missile> actives = [] as Set<Missile>
    private Sound sound

    PlayerMissiles init( final GameContainer gc ) throws SlickException {
        sound = resourceManager.loadSound( AUDIO_PLAYER_MISSILE )

        return this
    }

    void update( final GameContainer gc, final int delta ) throws SlickException{
        Input input = gc.getInput()

        // check for missile fire
        if( input.isKeyPressed( Input.KEY_SPACE ) && actives.size() < 5 ){
            sound.play()

            actives << new Missile(
                resourceManager: resourceManager,
                launcherPosition: player.cannonPosition
            ).init( gc )
        }

        actives.each { missile->
            missile.update( gc, delta )
            missile.alive = !missile.outOfBounds // TODO: just have the missile do this internally
        }

        actives.removeAll { !it.alive }
    }

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        actives*.render( gc, g )
    }

    void checkCollision( final Alien alien ){
        actives.each { missile->
            if( alien.colliding( missile ) ){
                alien.alive = false
                missile.alive = false
            }
        }
    }
}
