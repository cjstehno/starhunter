package com.stehno.starhunter.player

import com.stehno.games.ResourceManager
import com.stehno.games.SpriteLayer
import com.stehno.starhunter.GameOverState
import com.stehno.starhunter.RespawnState
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Vector2f
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.state.transition.EmptyTransition
import org.newdawn.slick.state.transition.HorizontalSplitTransition

/**
 * Created by cjstehno on 4/20/2014.
 */
class PlayerSpriteLayer extends SpriteLayer {

    ResourceManager resourceManager

    private PlayerSprite playerSprite

    PlayerSprite getPlayerSprite(){ playerSprite }

    @Override
    PlayerSpriteLayer init( final GameContainer gc,final StateBasedGame sbg ) throws SlickException{
        spawnPlayer gc
        return this
    }

    @Override
    void update( final GameContainer gc,final StateBasedGame sbg,final int delta ) throws SlickException{
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
                    RespawnState.STATE_ID,
                    new EmptyTransition(),
                    new HorizontalSplitTransition()
                )
            }
        }
    }

    @Override
    void render( final GameContainer gc,final StateBasedGame sbg,final Graphics g ) throws SlickException{
        playerSprite.render gc, g
    }

    void spawnPlayer( final GameContainer gc ){
        playerSprite = new PlayerSprite( resourceManager: resourceManager ).init( gc )
    }

    boolean isPlayerAlive(){
        playerSprite.model.alive
    }

    Vector2f getLauncherPosition(){
        playerSprite.launcherPosition
    }

    void killPlayer(){
        playerSprite.model.kill()
        playerSprite.model.decrementLives()
    }
}
