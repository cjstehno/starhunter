package com.stehno.games.ui

import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException

/**
 * Created by cjstehno on 3/1/14.
 */
class TestComponent extends Component {

    String id
    float componentWidth = 100
    float componentHeight = 35

    boolean initCalled, updateCalled, renderCalled

    @Override
    Component init( final GameContainer gc ) throws SlickException{
        initCalled = true
        return this
    }

    @Override
    void update( final GameContainer gc, final int delta ) throws SlickException{
        updateCalled = true
    }

    @Override
    void render( final GameContainer gc, final Graphics g ) throws SlickException{
        renderCalled = true
    }
}
