package com.stehno.starhunter
import com.stehno.games.Layer
import com.stehno.games.ResourceManager
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.state.transition.EmptyTransition
import org.newdawn.slick.state.transition.HorizontalSplitTransition
/**
 * Created by cjstehno on 3/23/2014.
 */
class AlienLayer extends Layer {

    ResourceManager resourceManager
    HudLayer hudLayer

    private Set<Alien> actives = [] as Set<Alien>
    private int currentWaveSize = 4
    private int currentWaveKilled = 0
    private int currentWave = 1

    Collection<Alien> activeAliens(){ actives }

    @Override
    AlienLayer init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        currentWaveSize.times {
            actives << new Alien( resourceManager:resourceManager ).init( gc )
        }

        return this
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        actives*.update( gc, delta )

        actives.removeAll { it.dead }

        if( currentWaveKilled >= currentWaveSize ){
            // TODO: should this be out in game state?
            sbg.enterState(
                WaveTransitionState.STATE_ID,
                new EmptyTransition(),
                new HorizontalSplitTransition()
            )
        }
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        actives*.render( gc, g )
    }

    @Override @SuppressWarnings( "GroovyAssignabilityCheck" )
    void checkCollisions( final Layer other ){
        if( other instanceof PlayerLayer ){ // FIXME: not happy with this
            actives.each { alien->
                if( alien.colliding( other.player ) ){
                    killAlien alien
                    other.killPlayer()
                }
            }
        }
    }

    void killAlien( final Alien alien ){
        alien.kill()
        hudLayer.score( 100 )
        currentWaveKilled++
    }
}
