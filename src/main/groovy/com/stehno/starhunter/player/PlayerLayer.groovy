package com.stehno.starhunter.player
import com.stehno.games.Layer
import com.stehno.games.ResourceManager
import com.stehno.starhunter.GameOverState
import com.stehno.starhunter.RespawnTransitionState
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
    PlayerModel model

    private Player player

    Player getPlayer(){ player }

    @Override
    PlayerLayer init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        spawnPlayer( gc )
        return this
    }

    void spawnPlayer( GameContainer gc ){
        player = new Player( resourceManager: resourceManager ).init( gc )
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        player.update( gc, delta )

        if( player.dead ){
            if( model.lives <= 0 ){
                sbg.enterState(
                    GameOverState.STATE_ID,
                    new EmptyTransition(),
                    new HorizontalSplitTransition()
                )
            } else {
                sbg.enterState(
                    RespawnTransitionState.STATE_ID,
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
        model.lives--
    }
}
