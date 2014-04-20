package com.stehno.starhunter.hud

import com.stehno.games.ui.Component
import org.newdawn.slick.Color
import org.newdawn.slick.Font
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.StateBasedGame

/**
 *
 * +-------+------+
 * | Image | x #  |
 * +-------+------+
 */
class LivesDisplay extends Component {
    // TODO: turn this into label+icon component

    Image image
    Font font
    Color color = Color.white
    int lives

    private String text

    @Override
    Component init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        text = "x $lives"
        return this
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        text = "x $lives"
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        g.drawImage( image, position.x, position.y )

        g.setColor( color )
        g.setFont( font )
        g.drawString( text, position.x + 5 + image.width as float, position.y )
    }

    @Override
    float getComponentWidth(){
        return image.width + font.getWidth( text )
    }

    @Override
    float getComponentHeight(){
        return image.height + font.getHeight( text )
    }
}
