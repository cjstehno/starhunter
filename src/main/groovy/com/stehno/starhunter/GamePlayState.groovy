package com.stehno.starhunter
import com.stehno.games.ResourceManager
import com.stehno.starhunter.alien.AlienModel
import com.stehno.starhunter.player.PlayerLayer
import com.stehno.starhunter.player.PlayerModel
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
    StarfieldLayer starfieldLayer
    HudLayer hudLayer

    PlayerModel playerModel
    AlienModel alienModel

    private PlayerLayer playerLayer
    private MissileLayer missileLayer
    private AlienLayer alienLayer
    private BombLayer bombLayer

    @Override
    int getID(){
        return STATE_ID;
    }

    @Override
    void init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException {
        starfieldLayer.init( gc, sbg )

        playerLayer = new PlayerLayer(
            resourceManager:resourceManager,
            model: playerModel
        ).init( gc, sbg )

        missileLayer = new MissileLayer( resourceManager:resourceManager, player:playerLayer.player ).init( gc, sbg )

        alienLayer = new AlienLayer(
            resourceManager:resourceManager,
            playerModel: playerModel,
            alienModel: alienModel
        ).init( gc, sbg )

        bombLayer = new BombLayer( resourceManager:resourceManager, aliens:alienLayer ).init( gc, sbg )

        hudLayer.init( gc, sbg )
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        starfieldLayer.update( gc, sbg, delta )

        playerLayer.update( gc, sbg, delta )

        // FIXME: collision detection needs work - maybe outside of layers?

        missileLayer.update( gc, sbg, delta )
        missileLayer.checkCollisions( alienLayer )

        alienLayer.update( gc, sbg, delta )
        alienLayer.checkCollisions( playerLayer )

        bombLayer.update( gc, sbg, delta )
        bombLayer.checkCollisions( playerLayer )

        hudLayer.update( gc, sbg, delta )
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        starfieldLayer.render( gc, sbg, g )

        playerLayer.render( gc, sbg, g )

        missileLayer.render( gc, sbg, g )

        alienLayer.render( gc, sbg, g )
        bombLayer.render( gc, sbg, g )

        hudLayer.render( gc, sbg, g )
    }
}

