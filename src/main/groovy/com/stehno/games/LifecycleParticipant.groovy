package com.stehno.games

import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.StateBasedGame

/**
 * A common interface for the Slick2d lifecycle workflow methods.
 */
interface LifecycleParticipant<T>{

    T init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException

    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException

    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException
}
