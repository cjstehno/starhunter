package com.stehno.starhunter.player
import com.stehno.games.ResourceManager
import com.stehno.games.SpriteLayer
import com.stehno.starhunter.alien.AlienSpriteLayer
import org.newdawn.slick.*
import org.newdawn.slick.state.StateBasedGame

import static com.stehno.starhunter.StarHunterResources.AUDIO_PLAYER_MISSILE
/**
 * Manages the players missiles.
 */
class MissileSpriteLayer extends SpriteLayer {

    ResourceManager resourceManager
    PlayerSpriteLayer playerSpriteLayer

    private Set<MissileSprite> actives = [] as Set<MissileSprite>
    private Sound sound

    @Override
    MissileSpriteLayer init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException {
        sound = resourceManager.loadSound( AUDIO_PLAYER_MISSILE )
        return this
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException {
        if( playerSpriteLayer.playerAlive ){
            Input input = gc.getInput()

            // check for missile fire
            if( input.isKeyPressed( Input.KEY_SPACE ) && actives.size() < 5 ){
                sound.play()

                actives << new MissileSprite( resourceManager, playerSpriteLayer.launcherPosition ).init( gc )
            }
        }

        actives*.update( gc, delta )

        actives.removeAll { !it.alive }
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException {
        actives*.render( gc, g )
    }

    @Override
    void checkCollisions( final SpriteLayer other ){
        if( other instanceof AlienSpriteLayer ){
            actives.each { missile->
                other.activeAliens().each { alien->
                    if( alien.colliding( missile ) ){
                        other.killAlien alien
                        missile.kill()
                        playerSpriteLayer.playerSprite.model.addScore(100)
                    }
                }
            }
        }
    }
}
