package com.stehno.starhunter

import com.stehno.games.Layer
import com.stehno.games.ResourceManager
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException

/**
 * Created by cjstehno on 3/23/2014.
 */
class AlienLayer extends Layer {

    ResourceManager resourceManager
    int waveSize = 4
    HudLayer hudLayer // FIXME: not happy with this

    private Set<Alien> actives = [] as Set<Alien>

    Collection<Alien> activeAliens(){ actives }

    @Override
    AlienLayer init( final GameContainer gc ) throws SlickException{
        waveSize.times {
            actives << new Alien( resourceManager:resourceManager ).init( gc )
        }

        return this
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException{
        actives*.update( gc, delta )

        actives.removeAll { it.dead }
    }

    @Override
    void render( final GameContainer gc,final Graphics g ) throws SlickException{
        actives*.render( gc, g )
    }

    @Override
    void checkCollisions( final Layer other ){
        if( other instanceof PlayerLayer ){ // FIXME: not happy with this
            actives.each { alien->
                if( alien.colliding( other.player ) ){
                    alien.kill()
                    other.player.kill()

                    hudLayer.decrementLives()
                }
            }
        }
    }
}
