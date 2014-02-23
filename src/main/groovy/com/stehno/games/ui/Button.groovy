package com.stehno.games.ui

import org.newdawn.slick.Color
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.SlickException

/**
 * Created by cjstehno on 2/23/14.
 */
class Button extends Label {

    Color focusColor
    boolean inFocus
    Closure onClick

    void update( final GameContainer gc, final int delta ) throws SlickException {
        super.update( gc, delta )

        if( isOver( gc.input ) ){
            inFocus = true

            if( gc.input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) ){
                onClick?.call( gc, delta )
            }

        } else {
            inFocus = false
        }
    }

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        g.setColor( inFocus ? focusColor : color )
        g.setFont( font )
        g.drawString( text, position.x, position.y )
    }

    private boolean isOver( final Input input ){
        input.mouseX > position.x && input.mouseX < (position.x + font.getWidth(text)) &&
            input.mouseY > position.y && input.mouseY < (position.y + font.getHeight(text))
    }
}
