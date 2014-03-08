package com.stehno.starhunter
import com.stehno.games.ResourceManager
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
/**
 * The game state for the main game play screen.
 */
class GamePlayState extends BasicGameState {

    static int STATE_ID = 2

    ResourceManager resourceManager

    private Player player
    private PlayerMissiles playerMissiles
    private Aliens aliens

    @Override
    int getID(){
        return STATE_ID;
    }

    @Override
    void init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException {
        player = new Player( resourceManager:resourceManager ).init( gc )

        playerMissiles = new PlayerMissiles( resourceManager:resourceManager, player:player ).init( gc )

        aliens = new Aliens( resourceManager:resourceManager ).init( gc )
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        player.update( gc, delta )

        playerMissiles.update( gc, delta )
        playerMissiles.checkCollisions( aliens.activeAliens() )

        aliens.update( gc, delta )
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        player.render( gc, g )

        playerMissiles.render( gc, g )

        aliens.render( gc, g )
    }
}

