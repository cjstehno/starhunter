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

    private Alien alien

    Collection<Alien> actives(){
        [alien]
    }

    Aliens init( final GameContainer gc ) throws SlickException {
        alien = new Alien( resourceManager:resourceManager ).init( gc )

        return this
    }

    void update( final GameContainer gc, final int delta ) throws SlickException{
        alien.update( gc, delta )
    }

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        alien.render( gc, g )
    }
}
