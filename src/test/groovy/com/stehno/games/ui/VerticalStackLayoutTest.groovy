package com.stehno.games.ui
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.geom.Vector2f

import static com.stehno.games.ui.Layout.VertAlign.*
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner)
class VerticalStackLayoutTest {

    private VerticalStackLayout layout

    @Mock private GameContainer gc
    @Mock private Graphics g

    @Before void before(){
        layout = new VerticalStackLayout()

        when( gc.getHeight() ).thenReturn( 800 )
    }

    @Test void simple(){
        def compA = new TestComponent( id:'A' ).init( gc )
        layout.addComponent( compA )

        def compB = new TestComponent( id:'B' ).init( gc )
        layout.addComponent( compB )

        def compC = new TestComponent( id:'C' ).init( gc )
        layout.addComponent( compC )

        layout.init( gc )
        layout.update( gc, 100 )
        layout.render( gc, g )

        assertComponent compA, MIDDLE, 0f, 382.5f
        assertComponent compB, MIDDLE, 100f, 382.5f
        assertComponent compC, MIDDLE, 200f, 382.5f
    }

    @Test void complex(){
        def compA = new TestComponent( id:'A', padding:new Box(1, 2, 3, 4) ).init( gc )
        layout.addComponent( compA, [valign:TOP] )

        def compB = new TestComponent( id:'B', componentHeight:50 ).init( gc )
        layout.addComponent( compB, [valign:MIDDLE] )

        def compC = new TestComponent( id:'C' ).init( gc )
        layout.addComponent( compC, [valign:BOTTOM] )

        layout.init( gc )
        layout.update( gc, 100 )
        layout.render( gc, g )

        assertComponent compA, TOP, 3, 1
        assertComponent compB, MIDDLE, 107, 375
        assertComponent compC, BOTTOM, 207, 765
    }

    private void assertComponent( final Component comp, final Layout.VertAlign align, final float x, final float y ){
        assert layout.components[comp].valign == align
        assert comp.position == new Vector2f( x, y )
        assert comp.initCalled
        assert comp.updateCalled
        assert comp.renderCalled
    }
}
