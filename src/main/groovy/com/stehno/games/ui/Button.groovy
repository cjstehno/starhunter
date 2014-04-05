package com.stehno.games.ui

import org.newdawn.slick.Color
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.StateBasedGame

/**
 * Simple text-based button component.
 */
class Button extends Label {

    Color focusColor
    boolean inFocus

    Closure onClick
    Closure onFocus
    Closure onBlur

    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException {
        super.update( gc, delta )

        boolean alreadyInFocus = inFocus

        if( isOver( gc.input ) ){
            inFocus = true

            if( !alreadyInFocus ) onFocus?.call( gc, delta )

            if( gc.input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) ){
                onClick?.call( gc, delta )
            }

        } else {
            inFocus = false

            if( alreadyInFocus) onBlur?.call( gc, delta )
        }
    }

    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException {
        g.setColor( inFocus ? focusColor : color )
        g.setFont( font )
        g.drawString( text, position.x, position.y )
    }

    private boolean isOver( final Input input ){
        input.mouseX > position.x && input.mouseX < (position.x + font.getWidth(text)) &&
            input.mouseY > position.y && input.mouseY < (position.y + font.getHeight(text))
    }
}
