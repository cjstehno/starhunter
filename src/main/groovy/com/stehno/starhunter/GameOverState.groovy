package com.stehno.starhunter
import com.stehno.games.ResourceManager
import com.stehno.games.ui.Box
import com.stehno.games.ui.HorizontalStackLayout
import com.stehno.games.ui.Label
import com.stehno.games.ui.Layout
import com.stehno.starhunter.alien.AlienModel
import com.stehno.starhunter.player.PlayerModel
import org.newdawn.slick.*
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.state.transition.EmptyTransition
import org.newdawn.slick.state.transition.HorizontalSplitTransition

import static com.stehno.starhunter.StarHunterResources.FONT_MAIN
/**
 * Game state representing a game end condition (player loses last life).
 */
class GameOverState  extends BasicGameState {

    static int STATE_ID = 3
    public static final int DISPLAY_WAIT_TIME = 10000

    ResourceManager resourceManager
    StarfieldLayer starfieldLayer
    HudLayer hudLayer
    AlienModel alienModel
    PlayerModel playerModel

    private Font font
    private Layout layout
    private Label message
    private long elapsed = 0

    @Override
    int getID(){ STATE_ID }

    @Override
    void init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        starfieldLayer.init( gc, sbg )
        hudLayer.init( gc, sbg )

        font = resourceManager.loadFont( FONT_MAIN, 40f )
        layout = new HorizontalStackLayout()

        message = new Label(
            text: 'Game Over, Man!',
            font: font,
            color: Color.red,
            padding: new Box( 300f, 0f, 0f, 0f )
        ).init( gc, sbg )

        layout.addComponent( message )
        layout.init( gc, sbg )
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        starfieldLayer.update( gc, sbg, delta )
        hudLayer.update( gc, sbg, delta )
        layout.update( gc, sbg, delta )

        // wait about 10s, then go back to menu
        if( elapsed > DISPLAY_WAIT_TIME ){
            elapsed = 0
            alienModel.reset()
            playerModel.reset()

            sbg.enterState(
                MenuState.STATE_ID,
                new EmptyTransition(),
                new HorizontalSplitTransition()
            )
        } else {
            elapsed += delta
        }
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        starfieldLayer.render( gc, sbg, g )
        hudLayer.render( gc, sbg, g )
        layout.render( gc, sbg, g )
    }
}
