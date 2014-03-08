package com.stehno.starhunter
import com.stehno.games.ResourceManager
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
/**
 * Manages the alien enemies.
 */
class Aliens {

    ResourceManager resourceManager
    int waveSize = 4

    private Set<Alien> actives = [] as Set<Alien>

    Collection<Alien> activeAliens(){ actives }

    Aliens init( final GameContainer gc ) throws SlickException {
        waveSize.times {
            actives << new Alien( resourceManager:resourceManager ).init( gc )
        }

        return this
    }

    void update( final GameContainer gc, final int delta ) throws SlickException{
        actives*.update( gc, delta )

        actives.removeAll { it.dead }
    }

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        actives*.render( gc, g )
    }
}
