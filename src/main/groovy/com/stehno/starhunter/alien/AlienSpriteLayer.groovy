package com.stehno.starhunter.alien

import com.stehno.games.ResourceManager
import com.stehno.games.SpriteLayer
import com.stehno.starhunter.NextWaveState
import com.stehno.starhunter.player.PlayerSpriteLayer
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.state.transition.EmptyTransition
import org.newdawn.slick.state.transition.HorizontalSplitTransition
/**
 * Controls the actions of the aliens.
 */
class AlienSpriteLayer extends SpriteLayer {

    ResourceManager resourceManager

    private final AlienWaveModel waveModel = new AlienWaveModel()

    private final Set<AlienSprite> actives = [] as Set<AlienSprite>
    private long elapsed

    @Override
    AlienSpriteLayer init( final GameContainer gc,final StateBasedGame sbg ) throws SlickException{
        startWave gc
        return this
    }

    @Override
    void update( final GameContainer gc,final StateBasedGame sbg,final int delta ) throws SlickException{
        actives*.update( gc, delta )

        actives.removeAll { it.dead }

        if( waveModel.waveComplete() ){
            if( elapsed > 1000 ){
                sbg.getState( NextWaveState.STATE_ID ).init(gc, sbg)

                sbg.enterState(
                    NextWaveState.STATE_ID,
                    new EmptyTransition(),
                    new HorizontalSplitTransition()
                )
                elapsed = 0

            } else {
                elapsed += delta
            }

        } else if( waveModel.waveChanged() ){
            startWave( gc )
        }
    }

    @Override
    void render( final GameContainer gc,final StateBasedGame sbg,final Graphics g ) throws SlickException{
        actives*.render gc, g
    }

    @Override @SuppressWarnings( "GroovyAssignabilityCheck" )
    void checkCollisions( final SpriteLayer other ){
        if( other instanceof PlayerSpriteLayer ){ // FIXME: not happy with this
            actives.each { alien->
                if( alien.colliding( other.playerSprite ) ){
                    killAlien alien
                    other.killPlayer()
                }
            }
        }
    }

    Collection<AlienSprite> activeAliens(){ actives }

    void startWave( final GameContainer gc ){
        actives.clear()

        waveModel.currentWaveSize.times {
            actives << new AlienSprite( resourceManager:resourceManager ).init( gc )
        }
    }

    void killAlien( final AlienSprite alien ){
        alien.model.kill()
        waveModel.currentWaveKilled++
    }
}
