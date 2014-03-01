package com.stehno.games.ui
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.geom.Vector2f

import static com.stehno.games.ui.Layout.HorizAlign.*
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner)
class HorizontalStackLayoutTest {

    private HorizontalStackLayout layout

    @Mock private GameContainer gc
    @Mock private Graphics g

    @Before void before(){
        layout = new HorizontalStackLayout()

        when( gc.getWidth() ).thenReturn( 800 )
    }

    @Test void simple(){
        def compA = new TestComponent( id: 'A' ).init( gc )
        layout.addComponent( compA )

        def compB = new TestComponent( id: 'B' ).init( gc )
        layout.addComponent( compB )

        def compC = new TestComponent( id: 'C' ).init( gc )
        layout.addComponent( compC )

        layout.init( gc )
        layout.update( gc, 100 )
        layout.render( gc, g )

        assertComponent compA, CENTER, 350, 0
        assertComponent compB, CENTER, 350, 35
        assertComponent compC, CENTER, 350, 70
    }

    @Test void complex(){
        def compA = new TestComponent( id:'A', padding:new Box(1, 2, 3, 4) ).init( gc )
        layout.addComponent( compA, [halign:LEFT] )

        def compB = new TestComponent( id:'B', componentHeight:50 ).init( gc )
        layout.addComponent( compB, [halign:CENTER] )

        def compC = new TestComponent( id:'C' ).init( gc )
        layout.addComponent( compC, [halign:RIGHT] )

        layout.init( gc )
        layout.update( gc, 100 )
        layout.render( gc, g )

        assertComponent compA, LEFT, 3, 1
        assertComponent compB, CENTER, 350, 38
        assertComponent compC, RIGHT, 700, 88
    }

    private void assertComponent( final Component comp, final Layout.HorizAlign align, final float x, final float y ){
        assert layout.components[comp].halign == align
        assert comp.position == new Vector2f( x, y )
        assert comp.initCalled
        assert comp.updateCalled
        assert comp.renderCalled
    }
}
