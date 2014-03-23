package com.stehno.starhunter

import com.stehno.games.Layer
import com.stehno.games.ResourceManager
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.Sound

/**
 * Created by cjstehno on 3/23/2014.
 */
class BombLayer extends Layer {

    ResourceManager resourceManager
    AlienLayer aliens
    HudLayer hudLayer // FIXME: not happy with this

    private final Map<Alien,Bomb> actives = [:] as Map<Alien,Bomb>
    private final Random random = new Random()
    private Sound sound
    private long lastDrop

    @Override
    BombLayer init( final GameContainer gc ) throws SlickException{
        sound = resourceManager.loadSound( StarHunterResources.AUDIO_ALIEN_BOMB )
        return this
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException{
        /*
            - each alien can have one bomb in plat at a time
            - if an alien does not have an active bomb, there is a 50% chance it will drop one
         */

        // limit the bombing frequency (especially near bottom of screen)
        lastDrop += delta

        // TODO: I think the aliens are dropping more bombs than they should be

        if( lastDrop > 200 ){
            aliens.activeAliens().each { alien->
                if( alien.alive && !actives.containsKey( alien ) ){
                    if( random.nextInt( 10 ) > 8 ){
                        lastDrop = 0

                        actives[alien] = new Bomb(
                            resourceManager: resourceManager,
                            dropPosition: alien.bombBayPosition,
                            speed: alien.speed + 0.2f
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
    void render( final GameContainer gc,final Graphics g ) throws SlickException{
        actives.values()*.render( gc, g )
    }

    @Override
    void checkCollisions( final Layer other ){
        if( other instanceof PlayerLayer ){
            actives.values().each { bomb->
                if( bomb.colliding( other.player ) ){
                    bomb.kill()
                    other.player.kill()

                    hudLayer.decrementLives()
                }
            }
        }
    }
}
