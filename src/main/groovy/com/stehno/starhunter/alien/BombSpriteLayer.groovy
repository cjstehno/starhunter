package com.stehno.starhunter.alien

import com.stehno.games.ResourceManager
import com.stehno.games.SpriteLayer
import com.stehno.starhunter.StarHunterResources
import com.stehno.starhunter.player.PlayerSpriteLayer
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.Sound
import org.newdawn.slick.state.StateBasedGame
/**
 * Created by cjstehno on 4/20/2014.
 */
class BombSpriteLayer extends SpriteLayer {

    ResourceManager resourceManager
    AlienSpriteLayer alienSpriteLayer

    private final Map<AlienSprite,BombSprite> actives = [:] as Map<AlienSprite,BombSprite>
    private final Random random = new Random()
    private Sound sound
    private long lastDrop

    @Override
    BombSpriteLayer init( final GameContainer gc,final StateBasedGame sbg ) throws SlickException{
        sound = resourceManager.loadSound( StarHunterResources.AUDIO_ALIEN_BOMB )
        return this
    }

    @Override
    void update( final GameContainer gc,final StateBasedGame sbg,final int delta ) throws SlickException{
        // limit the bombing frequency (especially near bottom of screen)
        lastDrop += delta

        // TODO: I think the aliens are dropping more bombs than they should be

        if( lastDrop > 200 ){
            alienSpriteLayer.activeAliens().each { alien->
                if( alien.alive && !actives.containsKey( alien ) ){
                    if( random.nextInt( 10 ) > 8 ){
                        lastDrop = 0

                        actives[alien] = new BombSprite(
                            resourceManager,
                            alien.bombBayPosition,
                            (alien.speed + 0.2f) as float
                        ).init( gc )

                        sound.play()
                    }
                }
            }
        }

        def removables = []

        actives.each { alien, bomb->
            bomb.update( gc, delta )

            if( !bomb.alive ){
                removables << alien
            }
        }

        removables.each { actives.remove(it) }
    }

    @Override
    void render( final GameContainer gc,final StateBasedGame sbg,final Graphics g ) throws SlickException{
        actives.values()*.render gc, g
    }

    @Override @SuppressWarnings( "GroovyAssignabilityCheck" )
    void checkCollisions( final SpriteLayer other ){
        if( other instanceof PlayerSpriteLayer ){
            actives.values().each { bomb->
                if( bomb.colliding( other.playerSprite ) ){
                    bomb.model.kill()
                    other.killPlayer()
                }
            }
        }
    }
}
