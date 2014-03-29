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

    private PlayerLayer playerLayer
    private MissileLayer missileLayer
    private AlienLayer alienLayer
    private BombLayer bombLayer
    private StarfieldLayer starfieldLayer
    private HudLayer hud

    @Override
    int getID(){
        return STATE_ID;
    }

    @Override
    void init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException {
        starfieldLayer = new StarfieldLayer( resourceManager:resourceManager ).init( gc )

        playerLayer = new PlayerLayer( resourceManager:resourceManager ).init( gc )
        missileLayer = new MissileLayer( resourceManager:resourceManager, player:playerLayer.player ).init( gc )

        alienLayer = new AlienLayer( resourceManager:resourceManager ).init( gc )
        bombLayer = new BombLayer( resourceManager:resourceManager, aliens:alienLayer ).init( gc )

        hud = new HudLayer( resourceManager:resourceManager ).init( gc )
        missileLayer.hudLayer = hud
        alienLayer.hudLayer = hud
        bombLayer.hudLayer = hud
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        starfieldLayer.update( gc, delta )

        playerLayer.update( gc, delta )

        // FIXME: collision detection needs work - maybe outside of layers?

        missileLayer.update( gc, delta )
        missileLayer.checkCollisions( alienLayer )

        alienLayer.update( gc, delta )
        alienLayer.checkCollisions( playerLayer )

        bombLayer.update( gc, delta )
        bombLayer.checkCollisions( playerLayer )

        hud.update( gc, delta )
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        starfieldLayer.render( gc, g )

        playerLayer.render( gc, g )

        missileLayer.render( gc, g )

        alienLayer.render( gc, g )
        bombLayer.render( gc, g )

        hud.render( gc, g )
    }
}

