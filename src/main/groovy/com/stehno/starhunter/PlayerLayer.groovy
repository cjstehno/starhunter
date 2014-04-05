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
class PlayerLayer extends Layer {

    ResourceManager resourceManager
    HudLayer hudLayer

    private Player player
    private int lives = 3

    Player getPlayer(){ player }

    @Override
    PlayerLayer init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        player = new Player( resourceManager:resourceManager ).init( gc )
        return this
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        player.update( gc, delta )

        if( player.dead ){
            // TODO: should these be out in game state?
            if( lives <= 0 ){
                sbg.enterState(
                    GameOverState.STATE_ID,
                    new EmptyTransition(),
                    new HorizontalSplitTransition()
                )
            } else {
                // FIXME: enter next life state... then respawn
                println 'You shall rise again...'

                // FIXME: temp - just game over for now
                sbg.enterState(
                    GameOverState.STATE_ID,
                    new EmptyTransition(),
                    new HorizontalSplitTransition()
                )
            }
        }
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        player.render( gc, g )
    }

    void killPlayer(){
        player.kill()
        lives--
        hudLayer.decrementLives()
    }
}
