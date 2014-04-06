package com.stehno.starhunter
import com.stehno.games.Layer
import com.stehno.games.ResourceManager
import com.stehno.games.ui.Box
import com.stehno.games.ui.HorizontalCornerLayout
import com.stehno.games.ui.Label
import com.stehno.games.ui.Layout
import com.stehno.starhunter.player.PlayerModel
import org.newdawn.slick.Color
import org.newdawn.slick.Font
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.StateBasedGame

import static com.stehno.starhunter.StarHunterResources.FONT_MAIN
import static com.stehno.starhunter.StarHunterResources.IMAGE_PLAYER_SHIP

/**
 * Created by cjstehno on 3/23/2014.
 */
class HudLayer extends Layer {

    ResourceManager resourceManager
    PlayerModel playerModel

    private Layout layout
    private Font font
    private Label scoreLabel
    private LivesDisplay livesDisplay

    @Override
    Layer init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException {
        font = resourceManager.loadFont( FONT_MAIN, 25f )

        layout = new HorizontalCornerLayout( updatable:true )

        livesDisplay = new LivesDisplay(
            image: resourceManager.loadImage( IMAGE_PLAYER_SHIP ).getScaledCopy( 0.1f ),
            font:font,
            color:Color.red,
            padding: new Box( 5f, 0f, 5f, 0f )
        ).init( gc, sbg )

        layout.addComponent(
            livesDisplay,
            [
                halign:Layout.HorizAlign.LEFT,
                valign:Layout.VertAlign.TOP,
                cell:HorizontalCornerLayout.Cell.TOP_LEFT
            ]
        )

        scoreLabel = new Label(
            text:'0',
            font:font,
            color:Color.red,
            padding: new Box( 5f, 0f, 0f, 5f )
        ).init( gc, sbg )

        layout.addComponent(
            scoreLabel,
            [
                halign:Layout.HorizAlign.RIGHT,
                valign:Layout.VertAlign.TOP,
                cell:HorizontalCornerLayout.Cell.TOP_RIGHT
            ]
        )

        layout.init( gc, sbg )

        return this
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg,final int delta ) throws SlickException {
        scoreLabel.text = playerModel.score as String
        livesDisplay.lives = playerModel.lives

        layout.update( gc, sbg, delta )
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg,final Graphics g ) throws SlickException {
        layout.render( gc, sbg, g )
    }
}
