package com.stehno.games.ui
import org.newdawn.slick.*
import org.newdawn.slick.state.StateBasedGame

/**
 * A simple textual label component.
 */
class Label extends Component {

    String text
    Font font
    Color color = Color.white

    Component init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        return this
    }

    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException {
        /* nothing special */
    }

    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException {
        g.setColor( color )
        g.setFont( font )
        g.drawString( text, position.x, position.y )
    }

    @Override
    float getComponentWidth(){
        font.getWidth( text )
    }

    @Override
    float getComponentHeight(){
        font.getHeight( text )
    }
}
