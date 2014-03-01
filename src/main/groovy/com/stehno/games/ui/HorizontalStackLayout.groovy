package com.stehno.games.ui

import org.newdawn.slick.GameContainer
import org.newdawn.slick.SlickException

import static com.stehno.games.ui.Layout.HorizAlign.CENTER
import static com.stehno.games.ui.Layout.HorizAlign.RIGHT

/**
 * A UI layout which positions components one on top of the other from the top down.
 *
 * Added components will be centered (halign: HorizAlign.CENTER) by default.
 */
class HorizontalStackLayout extends Layout {

    boolean updatable

    @Override
    void addComponent( final Component component, final Map<String, Object> constraints=[:] ){
        // centered by default
        if( !constraints.containsKey('halign') ){
            constraints.halign = CENTER
        }

        super.addComponent( component, constraints )
    }

    @Override
    void init( final GameContainer gc ) throws SlickException{
        calculatePositions( gc.width )
        super.init( gc )
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException {
        if( updatable ){
            calculatePositions( gc.width )
        }

        super.update( gc, delta )
    }

    private void calculatePositions( final int gameWidth ){
        float yoffset = 0.0
        components.each { component, constraints->
            if( constraints.halign == CENTER ){
                component.position.x = (gameWidth - component.width)/2

            } else if( constraints.halign == RIGHT ){
                component.position.x = gameWidth - component.width

            } else {
                component.position.x = component.padding.left
            }

            component.position.y = yoffset + component.padding.top

            yoffset += component.height
        }
    }
}
