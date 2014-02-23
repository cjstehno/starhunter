package com.stehno.games.ui
import org.newdawn.slick.*
import org.newdawn.slick.geom.Vector2f

/**
 * A simple textual label component.
 */
class Label extends Component {

    String text
    Font font
    Color color = Color.white
    Vector2f position = new Vector2f(0f,0f)

    void init( final GameContainer gc ) throws SlickException{
        updatePosition( gc )
    }

    void update( final GameContainer gc, final int delta ) throws SlickException {
        updatePosition( gc )
    }

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        g.setColor( color )
        g.setFont( font )
        g.drawString( text, position.x, position.y )
    }

    private void updatePosition( GameContainer gc ){
        // FIXME: this needs to come out once we have layouts
        position.x = ( gc.getWidth() - font.getWidth( text ) ) / 2
    }
}
