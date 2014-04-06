package com.stehno.starhunter
import com.stehno.games.Layer
import com.stehno.games.ResourceManager
import com.stehno.starhunter.alien.AlienModel
import com.stehno.starhunter.player.PlayerLayer
import com.stehno.starhunter.player.PlayerModel
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
    PlayerModel playerModel
    AlienModel alienModel

    private Set<Alien> actives = [] as Set<Alien>
    private long elapsed

    Collection<Alien> activeAliens(){ actives }

    @Override
    AlienLayer init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        startWave( gc )

        return this
    }

    void startWave( GameContainer gc ){
        actives.clear()

        alienModel.currentWaveSize.times {
            actives << new Alien( resourceManager: resourceManager ).init( gc )
        }
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        actives*.update( gc, delta )

        actives.removeAll { it.dead }

        if( alienModel.waveComplete() ){
            if( elapsed > 1000 ){
                sbg.getState( WaveTransitionState.STATE_ID ).init(gc, sbg)

                sbg.enterState(
                    WaveTransitionState.STATE_ID,
                    new EmptyTransition(),
                    new HorizontalSplitTransition()
                )
                elapsed = 0

            } else {
                elapsed += delta
            }

        } else if( alienModel.waveChanged() ){
            startWave( gc )
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
        playerModel.score += 100
        alienModel.currentWaveKilled++
    }
}
