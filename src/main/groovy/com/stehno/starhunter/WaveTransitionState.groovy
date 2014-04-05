package com.stehno.starhunter
import com.stehno.games.ResourceManager
import com.stehno.games.ui.Box
import com.stehno.games.ui.HorizontalStackLayout
import com.stehno.games.ui.Label
import com.stehno.games.ui.Layout
import org.newdawn.slick.Color
import org.newdawn.slick.Font
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame

import static com.stehno.starhunter.StarHunterResources.FONT_MAIN
/**
 * Created by cjstehno on 4/5/2014.
 */
class WaveTransitionState extends BasicGameState {

    static int STATE_ID = 4

    ResourceManager resourceManager
    StarfieldLayer starfieldLayer

    private PlayerLayer playerLayer
    private HudLayer hudLayer

    private Font font
    private Layout layout
    private Label message

    @Override
    int getID(){ STATE_ID }

    /*
        FIXME: need to carry information over from GamePlayState
     */

    @Override
    void init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        starfieldLayer.init( gc, sbg )
        playerLayer = new PlayerLayer( resourceManager:resourceManager ).init( gc, sbg )
        hudLayer = new HudLayer( resourceManager:resourceManager ).init( gc, sbg )

        font = resourceManager.loadFont( FONT_MAIN, 25f )
        layout = new HorizontalStackLayout()

        message = new Label(
            text: 'Wave # Completed',
            font: font,
            color: Color.red,
            padding: new Box( 10f, 0f, 0f, 0f )
        ).init( gc, sbg )

        // FIXME: Ready? ... 3... 2... 1... transition back

        layout.init( gc, sbg )
        layout.addComponent( message )
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        starfieldLayer.update( gc, sbg, delta )
        playerLayer.update( gc, sbg, delta )
        hudLayer.update( gc, sbg, delta )
        layout.update( gc, sbg, delta )
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        starfieldLayer.render( gc, sbg, g )
        playerLayer.render( gc, sbg, g )
        hudLayer.render( gc, sbg, g )
        layout.render( gc, sbg, g )
    }
}
