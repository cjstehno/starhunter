package com.stehno.games.ui
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics

import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner)
class HorizontalCornerLayoutTest {

    private HorizontalCornerLayout layout

    @Mock private GameContainer gc
    @Mock private Graphics g

    @Before void before(){
        layout = new HorizontalCornerLayout()

        when( gc.getWidth() ).thenReturn( 800 )
        when( gc.getHeight() ).thenReturn( 600 )
    }

    @Test void 'layout: no constraints'(){
        def compA = addTestComponent( id:'A' )
        def compB = addTestComponent( id:'B' )
        def compC = addTestComponent( id:'C' )
        def compD = addTestComponent( id:'D' )
        def compE = addTestComponent( id:'E' )

        layoutLifecycle()

        assertComponent compA, 150f, 82.5f
        assertComponent compB, 550f, 82.5f
        assertComponent compC, 350f, 282.5f
        assertComponent compD, 150f, 482.5f
        assertComponent compE, 550f, 482.5f
    }

    @Test void 'layout: all top left aligned'(){
        def compA = addTestComponent( id:'A', Layout.HorizAlign.LEFT, Layout.VertAlign.TOP )
        def compB = addTestComponent( id:'B', Layout.HorizAlign.LEFT, Layout.VertAlign.TOP )
        def compC = addTestComponent( id:'C', Layout.HorizAlign.LEFT, Layout.VertAlign.TOP )
        def compD = addTestComponent( id:'D', Layout.HorizAlign.LEFT, Layout.VertAlign.TOP )
        def compE = addTestComponent( id:'E', Layout.HorizAlign.LEFT, Layout.VertAlign.TOP )

        layoutLifecycle()

        assertComponent compA, 0f,   0f
        assertComponent compB, 400f, 0f
        assertComponent compC, 0f,   200f
        assertComponent compD, 0f,   400f
        assertComponent compE, 400f, 400f
    }

    @Test void 'layout: all bottom right aligned'(){
        def compA = addTestComponent( id:'A', Layout.HorizAlign.RIGHT, Layout.VertAlign.BOTTOM )
        def compB = addTestComponent( id:'B', Layout.HorizAlign.RIGHT, Layout.VertAlign.BOTTOM )
        def compC = addTestComponent( id:'C', Layout.HorizAlign.RIGHT, Layout.VertAlign.BOTTOM )
        def compD = addTestComponent( id:'D', Layout.HorizAlign.RIGHT, Layout.VertAlign.BOTTOM )
        def compE = addTestComponent( id:'E', Layout.HorizAlign.RIGHT, Layout.VertAlign.BOTTOM )

        layoutLifecycle()

        assertComponent compA, 300f, 165f
        assertComponent compB, 700f, 165f
        assertComponent compC, 700f, 365f
        assertComponent compD, 300f, 565f
        assertComponent compE, 700f, 565f
    }

    @Test void 'layout: two in top corners'(){
        def compA = addTestComponent( id:'A', Layout.HorizAlign.LEFT, Layout.VertAlign.TOP, HorizontalCornerLayout.Cell.TOP_LEFT )
        def compB = addTestComponent( id:'B', Layout.HorizAlign.RIGHT, Layout.VertAlign.TOP, HorizontalCornerLayout.Cell.TOP_RIGHT )

        layoutLifecycle()

        assertComponent compA, 0f, 0f
        assertComponent compB, 700f, 0f
    }

    private void layoutLifecycle(){
        layout.init( gc )
        layout.update( gc, 100 )
        layout.render( gc, g )
    }

    private TestComponent addTestComponent( Map map, halign=null, valign=null, cell=null ){
        def comp = new TestComponent( map ).init( gc )

        def constraints = [:]
        if( halign ) constraints.halign = halign
        if( valign ) constraints.valign = valign
        if( cell ) constraints.cell = cell

        layout.addComponent( comp, constraints )

        return comp
    }

    private void assertComponent( final Component comp, final float x, final float y ){
        comp.verifyPosition( x, y )
        comp.verifyLifecycle()
    }
}
