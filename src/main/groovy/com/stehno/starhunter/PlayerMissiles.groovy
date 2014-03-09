package com.stehno.starhunter
import com.stehno.games.ResourceManager
import org.newdawn.slick.*

import static com.stehno.starhunter.StarHunterResources.AUDIO_PLAYER_MISSILE

/**
 * Manages the players missiles.
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
        if( player.alive ){
            Input input = gc.getInput()

            // check for missile fire
            if( input.isKeyPressed( Input.KEY_SPACE ) && actives.size() < 5 ){
                sound.play()

                actives << new Missile(
                    resourceManager: resourceManager,
                    launcherPosition: player.cannonPosition
                ).init( gc )
            }
        }

        actives*.update( gc, delta )

        actives.removeAll { !it.alive }
    }

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        actives*.render( gc, g )
    }

    void checkCollisions( final Collection<Alien> aliens ){
        actives.each { missile->
            aliens.each { alien->
                if( alien.colliding( missile ) ){
                    alien.kill()
                    missile.kill()
                }
            }
        }
    }
}
