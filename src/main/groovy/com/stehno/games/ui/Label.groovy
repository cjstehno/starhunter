package com.stehno.games.ui
import org.newdawn.slick.*
/**
 * A simple textual label component.
 */
class Label extends Component {

    String text
    Font font
    Color color = Color.white

    Component init( final GameContainer gc ) throws SlickException{
        return this
    }

    void update( final GameContainer gc, final int delta ) throws SlickException {
        /* nothing special */
    }

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
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
