package com.stehno.games

import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException

/**
 * Created by cjstehno on 3/23/2014.
 */
interface LifecycleParticipant<T>{

    T init( final GameContainer gc ) throws SlickException

    void update( final GameContainer gc, final int delta ) throws SlickException

    void render( final GameContainer gc, final Graphics g ) throws SlickException
}