package com.stehno.games.ui

import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException

/**
 * The base class for UI component layout managers.
 */
abstract class Layout {

    static enum HorizAlign { LEFT, CENTER, RIGHT }

    static enum VertAlign { TOP, MIDDLE, BOTTOM }

    final Map<Component,Map<String,Object>> components = new LinkedHashMap<>()

    /**
     * Adds a component to the layout without any specific constraints.
     *
     * @param component the component to be added
     */
    void leftShift( Component component ){
        addComponent( component )
    }

    /**
     * Adds a component with the optionally specified layout constraints. The layout constraints will be specific
     * to the layout implementation.
     *
     * The order components are added is maintained and used during update and rendering.
     *
     * @param component the component being added
     * @param constraints the layout constraints
     */
    void addComponent( final Component component, final Map<String,Object> constraints = [:] ){
        components[component] = constraints
    }

    /**
     *
     * @param gc
     * @throws SlickException
     */
    void init( final GameContainer gc ) throws SlickException {
        // Nothing special
    }

    void update( final GameContainer gc, final int delta ) throws SlickException {
        components.each { Component c, constraints->
            c.update( gc, delta )
        }
    }

    void render( final GameContainer gc, final Graphics g ) throws SlickException {
        components.each { Component c, constraints->
            c.render( gc, g )
        }
    }
}
