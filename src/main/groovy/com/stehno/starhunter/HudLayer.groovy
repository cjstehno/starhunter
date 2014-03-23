package com.stehno.starhunter
import com.stehno.games.Layer
import com.stehno.games.ResourceManager
import com.stehno.games.ui.Box
import com.stehno.games.ui.HorizontalCornerLayout
import com.stehno.games.ui.Label
import com.stehno.games.ui.Layout
import org.newdawn.slick.Color
import org.newdawn.slick.Font
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException

import static com.stehno.starhunter.StarHunterResources.FONT_MAIN
import static com.stehno.starhunter.StarHunterResources.getIMAGE_PLAYER_SHIP

/**
 * Created by cjstehno on 3/23/2014.
 */
class HudLayer extends Layer {

    ResourceManager resourceManager

    private Layout layout
    private Font font
    private int currentScore = 0
    private Label scoreLabel
    private LivesDisplay livesDisplay

    @Override
    Layer init( final GameContainer gc ) throws SlickException {
        font = resourceManager.loadFont( FONT_MAIN, 25f )

        layout = new HorizontalCornerLayout( updatable:true )

        livesDisplay = new LivesDisplay(
            image: resourceManager.loadImage( IMAGE_PLAYER_SHIP ).getScaledCopy( 0.1f ),
            font:font,
            color:Color.red,
            padding: new Box( 5f, 0f, 5f, 0f )
        ).init( gc )

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
        ).init( gc )

        layout.addComponent(
            scoreLabel,
            [
                halign:Layout.HorizAlign.RIGHT,
                valign:Layout.VertAlign.TOP,
                cell:HorizontalCornerLayout.Cell.TOP_RIGHT
            ]
        )

        layout.init( gc )

        return this
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException {
        layout.update( gc, delta )
    }

    @Override
    void render( final GameContainer gc,final Graphics g ) throws SlickException {
        layout.render( gc, g )
    }

    void decrementLives(){
        livesDisplay.lives--
    }

    void score( int points ){
        currentScore += points
        scoreLabel.text = currentScore as String
    }
}
