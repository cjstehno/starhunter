package com.stehno.starhunter
import com.stehno.games.ResourceManager
import com.stehno.games.ui.Box
import com.stehno.games.ui.HorizontalStackLayout
import com.stehno.games.ui.Label
import com.stehno.games.ui.Layout
import com.stehno.starhunter.alien.AlienModel
import com.stehno.starhunter.player.PlayerLayer
import org.newdawn.slick.Color
import org.newdawn.slick.Font
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.state.transition.EmptyTransition
import org.newdawn.slick.state.transition.HorizontalSplitTransition

import static com.stehno.starhunter.StarHunterResources.FONT_MAIN
/**
 * Created by cjstehno on 4/5/2014.
 */
class WaveTransitionState extends BasicGameState {

    static int STATE_ID = 4

    ResourceManager resourceManager
    StarfieldLayer starfieldLayer
    HudLayer hudLayer
    AlienModel alienModel

    private final readyMessages = ['Ready?','3...', '2...', '1...']
    private int messageIdx = 0
    private PlayerLayer playerLayer
    private Font font
    private Layout layout
    private Label message
    private long elapsed

    @Override
    int getID(){ STATE_ID }

    @Override
    void init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        messageIdx = 0
        elapsed = 0

        starfieldLayer.init( gc, sbg )
        playerLayer = new PlayerLayer( resourceManager:resourceManager ).init( gc, sbg )
        hudLayer.init( gc, sbg )

        font = resourceManager.loadFont( FONT_MAIN, 40f )
        layout = new HorizontalStackLayout( updatable:true )

        message = new Label(
            text: "Wave ${alienModel.currentWave} Completed",
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
        playerLayer.update( gc, sbg, delta )
        hudLayer.update( gc, sbg, delta )
        layout.update( gc, sbg, delta )

        if( elapsed > 1000 ){
            if( messageIdx < readyMessages.size() ){
                message.text = readyMessages[messageIdx++]
                elapsed = 0

            } else {
                alienModel.setupNextWave()

                sbg.enterState(
                    GamePlayState.STATE_ID,
                    new EmptyTransition(),
                    new HorizontalSplitTransition()
                )
            }
        }

        elapsed += delta
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        starfieldLayer.render( gc, sbg, g )
        playerLayer.render( gc, sbg, g )
        hudLayer.render( gc, sbg, g )
        layout.render( gc, sbg, g )
    }
}
