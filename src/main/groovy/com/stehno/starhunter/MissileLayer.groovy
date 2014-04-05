package com.stehno.starhunter

import com.stehno.games.Layer
import com.stehno.games.ResourceManager
import com.stehno.starhunter.player.Player
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.SlickException
import org.newdawn.slick.Sound
import org.newdawn.slick.state.StateBasedGame

import static com.stehno.starhunter.StarHunterResources.getAUDIO_PLAYER_MISSILE

/**
 * Created by cjstehno on 3/23/2014.
 */
class MissileLayer extends Layer {

    ResourceManager resourceManager
    Player player

    private Set<Missile> actives = [] as Set<Missile>
    private Sound sound

    @Override
    MissileLayer init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        sound = resourceManager.loadSound( AUDIO_PLAYER_MISSILE )
        return this
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg,final int delta ) throws SlickException{
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

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg,final Graphics g ) throws SlickException{
        actives*.render( gc, g )
    }

    @Override
    void checkCollisions( final Layer other ){
        if( other instanceof AlienLayer ){
            actives.each { missile->
                other.activeAliens().each { alien->
                    if( alien.colliding( missile ) ){
                        other.killAlien alien
                        missile.kill()
                    }
                }
            }
        }
    }
}
