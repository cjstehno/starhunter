package com.stehno.games.ui

import org.newdawn.slick.GameContainer
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.StateBasedGame

import static com.stehno.games.ui.Layout.VertAlign.BOTTOM
import static com.stehno.games.ui.Layout.VertAlign.MIDDLE
import static com.stehno.games.ui.Layout.VertAlign.TOP

/**
 * A UI layout which positions components one after the other from left to right.
 *
 * Added components will be centered (valign: VertAlign.MIDDLE) by default.
 *
 * +-----+-----+
 * |     |     |
 * |     |     |
 * |  A  |  B  |
 * |     |     |
 * |     |     |
 * +-----+-----+
 */
class VerticalStackLayout extends Layout {

    boolean updatable

    @Override
    void addComponent( final Component component, final Map<String, Object> constraints=[:] ){
        // middle by default
        if( !constraints.containsKey('valign') ){
            constraints.valign = MIDDLE
        }

        super.addComponent( component, constraints )
    }

    @Override
    void init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        calculatePositions( gc.height )
        super.init( gc, sbg )
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException {
        if( updatable ){
            calculatePositions( gc.height )
        }

        super.update( gc, sbg, delta )
    }

    private void calculatePositions( final int gameHeight ){
        float xoffset = 0.0

        components.each { component, constraints->
            if( constraints.valign == TOP ){
                component.position.y = component.padding.top

            } else if( constraints.valign == BOTTOM ){
                component.position.y = gameHeight - component.height

            } else {
                component.position.y = (gameHeight - component.height)/2
            }

            component.position.x = xoffset + component.padding.left

            xoffset += component.width
        }
    }
}
