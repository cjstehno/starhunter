package com.stehno.starhunter
import com.stehno.games.ResourceManager
import com.stehno.starhunter.alien.AlienSpriteLayer
import com.stehno.starhunter.alien.BombSpriteLayer
import com.stehno.starhunter.hud.HudSpriteLayer
import com.stehno.starhunter.player.MissileSpriteLayer
import com.stehno.starhunter.player.PlayerSpriteLayer
import com.stehno.starhunter.stars.StarfieldSpriteLayer
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
    StarfieldSpriteLayer starfieldSpriteLayer
    HudSpriteLayer hudSpriteLayer

    PlayerSpriteLayer playerSpriteLayer

    private MissileSpriteLayer missileSpriteLayer
    private AlienSpriteLayer alienSpriteLayer
    private BombSpriteLayer bombSpriteLayer

    @Override
    int getID(){
        return STATE_ID;
    }

    @Override
    void init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException {
        starfieldSpriteLayer.init( gc, sbg )
        playerSpriteLayer.init( gc, sbg )

        missileSpriteLayer = new MissileSpriteLayer( resourceManager:resourceManager, player:playerSpriteLayer.playerSprite ).init( gc, sbg )

        alienSpriteLayer = new AlienSpriteLayer( resourceManager:resourceManager ).init( gc, sbg )

        bombSpriteLayer = new BombSpriteLayer( resourceManager:resourceManager, aliens:alienSpriteLayer ).init( gc, sbg )

        hudSpriteLayer.init( gc, sbg )
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        starfieldSpriteLayer.update( gc, sbg, delta )

        playerSpriteLayer.update( gc, sbg, delta )

        missileSpriteLayer.update( gc, sbg, delta )
        missileSpriteLayer.checkCollisions( alienSpriteLayer )

        alienSpriteLayer.update( gc, sbg, delta )
        alienSpriteLayer.checkCollisions( playerSpriteLayer )

        bombSpriteLayer.update( gc, sbg, delta )
        bombSpriteLayer.checkCollisions( playerSpriteLayer )

        hudSpriteLayer.update( gc, sbg, delta )
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        starfieldSpriteLayer.render( gc, sbg, g )

        playerSpriteLayer.render( gc, sbg, g )

        missileSpriteLayer.render( gc, sbg, g )

        alienSpriteLayer.render( gc, sbg, g )
        bombSpriteLayer.render( gc, sbg, g )

        hudSpriteLayer.render( gc, sbg, g )
    }
}
