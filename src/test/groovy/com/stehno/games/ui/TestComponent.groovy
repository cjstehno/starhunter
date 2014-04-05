package com.stehno.games.ui

import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Vector2f
import org.newdawn.slick.state.StateBasedGame

/**
 * Simple UI component useful for unit testing.
 */
class TestComponent extends Component {

    String id
    float componentWidth = 100
    float componentHeight = 35

    boolean initCalled, updateCalled, renderCalled

    @Override
    Component init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        initCalled = true
        return this
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        updateCalled = true
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        renderCalled = true
    }

    void verifyLifecycle(){
        assert initCalled
        assert updateCalled
        assert renderCalled
    }

    void verifyPosition( final float x, final float y ){
        assert position == new Vector2f( x, y )
    }
}
