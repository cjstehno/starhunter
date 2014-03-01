package com.stehno.starhunter

import com.stehno.games.ResourceManager
import com.stehno.games.ui.Box
import com.stehno.games.ui.Button
import com.stehno.games.ui.HorizontalStackLayout
import com.stehno.games.ui.Label
import com.stehno.games.ui.Layout
import org.newdawn.slick.*
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame

import static com.stehno.starhunter.StarHunterResources.*

/**
 * Game state for the main menu screen.
 */
class MenuState extends BasicGameState {

    static final int STATE_ID = 100

    ResourceManager resourceManager

    private static enum MenuItem {
        PLAY("Play"),
        QUIT("Quit");

        private final String label;

        private MenuItem(final String label){
            this.label = label;
        }

        public String getLabel(){
            return label;
        }
    }

    private Font titleFont, optionFont
    private Sound menuToggle, menuSelect
    private Layout layout

    @Override
    public int getID(){
        return STATE_ID;
    }

    @Override
    public void init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        titleFont = resourceManager.loadFont( FONT_MAIN, 55f )
        optionFont = resourceManager.loadFont( FONT_MAIN, 32f )

        final Music music = resourceManager.loadMusic( AUDIO_BACKGROUND )
        music.play();
        music.setVolume( 0.25f );

        menuToggle = resourceManager.loadSound( AUDIO_MENU_TOGGLE )
        menuSelect = resourceManager.loadSound( AUDIO_MENU_SELECT )

        layout = new HorizontalStackLayout()

        layout.addComponent(
            new Label(
                text: 'Star Hunter',
                font: titleFont,
                color: Color.red,
                padding: new Box( 200, 0, 0, 0 )
            ).init( gc )
        )

        layout.addComponent(
            new Button(
                text: 'Play',
                font: optionFont,
                color: Color.gray,
                focusColor: Color.green,
                padding: new Box( 50, 0, 0, 0 ),
                onClick: { cgc, cg->
                    sbg.enterState( GamePlayState.STATE_ID );
                }
            ).init( gc )
        )

        layout.addComponent(
            new Button(
                text: 'Quit',
                font: optionFont,
                color: Color.gray,
                focusColor: Color.green,
                padding: new Box( 50, 0, 0, 0 ),
                onClick: { cgc, cg->
                    // TODO: is there something better?
                    System.exit( 0 );
                }
            ).init( gc )
        )

        layout.init( gc )
    }

    @Override
    public void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        layout.update( gc, delta )
    }

    @Override
    public void render( final GameContainer gc, final StateBasedGame stateBasedGame, final Graphics g ) throws SlickException{
        layout.render( gc, g )
    }
}

