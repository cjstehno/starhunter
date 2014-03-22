package com.stehno.games.ui
import org.newdawn.slick.GameContainer
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Vector2f

import static com.stehno.games.ui.Layout.HorizAlign.CENTER
import static com.stehno.games.ui.Layout.HorizAlign.RIGHT
import static com.stehno.games.ui.Layout.VertAlign.BOTTOM
import static com.stehno.games.ui.Layout.VertAlign.MIDDLE
/**
 * Layout that adds components at the corners in a horizontal manner.
 *
 * This layout supports the following constraints:
 *
 * halign - horizontal alignment of component in cell
 * valign - vertical alignment of component in cell
 * cell - the layout cell the component should reside in
 *
 * +-----+-----+
 * |  A  |  B  |
 * +-----+-----+
 * |     C     |
 * +-----+-----+
 * |  D  |  E  |
 * +-----+-----+
 */
class HorizontalCornerLayout extends Layout {

    static enum Cell {
        TOP_LEFT(
            { gw,gh-> new Vector2f( 0f, 0f ) },
            { w-> w/2 as float },
            { h-> h/3 as float }
        ),

        TOP_RIGHT(
            { gw,gh-> new Vector2f( gw/2 as float, 0f ) },
            { w-> w/2 as float },
            { h-> h/3 as float }
        ),

        BODY(
            { gw,gh-> new Vector2f( 0f, gh/3 as float ) },
            { w-> w as float },
            { h-> h/3 as float }
        ),

        BOTTOM_LEFT(
            { gw,gh-> new Vector2f( 0f, 2*gh/3 as float ) },
            { w-> w/2 as float },
            { h-> h/3 as float }
        ),

        BOTTOM_RIGHT(
            { gw,gh-> new Vector2f( gw/2 as float, 2*gh/3 as float ) },
            { w-> w/2 as float },
            { h-> h/3 as float }
        )

        private final Closure offsetCalc
        private final Closure widthCalc
        private final Closure heightCalc

        private Cell( Closure offsetCalc, Closure widthCalc, Closure heightCalc ){
            this.offsetCalc = offsetCalc
            this.widthCalc = widthCalc
            this.heightCalc = heightCalc
        }

        Vector2f offset( int gameW, int gameH ){
            offsetCalc(gameW as float, gameH as float)
        }

        float width( int gameW ){
            widthCalc(gameW as float)
        }

        float height( int gameH ){
            heightCalc( gameH as float)
        }
    }

    boolean updatable

    private Cell lastCell

    @Override
    void addComponent( final Component component, final Map<String, Object> constraints ){
        // horizontal center by default
        if( !constraints.containsKey('halign') ){
            constraints.halign = CENTER
        }

        // vertical middle by default
        if( !constraints.containsKey('valign') ){
            constraints.valign = MIDDLE
        }

        if( !constraints.containsKey('cell') ){
            constraints.cell = lastCell ? lastCell.next() : Cell.TOP_LEFT
            lastCell = constraints.cell
            // TODO: what should happen if more than 5 components?
        }
        // FIXME: disallow multiple components in the same cell

        // TODO: should I validate the component size based on the target cell?

        super.addComponent( component, constraints )
    }

    @Override
    void init( final GameContainer gc ) throws SlickException {
        calculatePositions( gc.width, gc.height )
        super.init( gc )
    }

    @Override
    void update( final GameContainer gc, final int delta ) throws SlickException{
        if( updatable ){
            calculatePositions( gc.width, gc.height )
        }

        super.update( gc, delta )
    }

    private void calculatePositions( final int gameWidth, final int gameHeight ){
        Cell.values().each { cell->
            Vector2f offset = cell.offset( gameWidth, gameHeight )

            def compEntry = components.find { k,v-> v.cell == cell }
            def component = compEntry.key
            def constraints = compEntry.value

            if( constraints.halign == CENTER ){
                component.position.x = offset.x + ( cell.width( gameWidth ) - component.width )/2

            } else if( constraints.halign == RIGHT ){
                component.position.x = offset.x + ( cell.width( gameWidth ) - component.width )

            } else {
                component.position.x = offset.x + component.padding.left
            }

            if( constraints.valign == MIDDLE ){
                component.position.y = offset.y + ( cell.height( gameHeight ) - component.height)/2

            } else if( constraints.valign == BOTTOM ){
                component.position.y = offset.y + ( cell.height( gameHeight ) - component.height)

            } else {
                component.position.y = offset.y + component.padding.top
            }
        }
    }
}
