package com.stehno.games.ui

import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException

/**
 * Created by cjstehno on 2/23/14.
 */
abstract class Component {

    // TODO: might be nice to have init return an instance fo the component
    abstract void init( final GameContainer gc ) throws SlickException

    abstract void update( final GameContainer gc, final int delta ) throws SlickException

    abstract void render( final GameContainer gc, final Graphics g ) throws SlickException
}
