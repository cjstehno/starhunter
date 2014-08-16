package com.stehno.starhunter

import com.stehno.games.ResourceManager
import com.stehno.games.ui.Box
import com.stehno.games.ui.Button
import com.stehno.games.ui.HorizontalStackLayout
import com.stehno.games.ui.Label
import com.stehno.games.ui.Layout
import com.stehno.starhunter.stars.StarfieldSpriteLayer
import org.newdawn.slick.*
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.state.transition.EmptyTransition
import org.newdawn.slick.state.transition.HorizontalSplitTransition

import static com.stehno.starhunter.StarHunterResources.*

/**
 * Game state for the main menu screen.
 */
class MenuState extends BasicGameState {

    static final int STATE_ID = 100

    ResourceManager resourceManager
    StarfieldSpriteLayer starfieldLayer

    private static enum MenuItem {
        PLAY( 'Play' ),
        QUIT( 'Quit' );

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
        starfieldLayer.init( gc, sbg )

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
            ).init( gc, sbg )
        )

        layout.addComponent(
            new Button(
                text: 'Play',
                font: optionFont,
                color: Color.gray,
                focusColor: Color.green,
                padding: new Box( 50, 0, 0, 0 ),
                onFocus: { cgc, del->
                    menuToggle.play()
                },
                onClick: { cgc, del->
                    menuSelect.play()

                    // TODO: this does not really work well
                    sbg.getState( GamePlayState.STATE_ID ).init( gc, sbg )

                    sbg.enterState(
                        GamePlayState.STATE_ID,
                        new EmptyTransition(),
                        new HorizontalSplitTransition()
                    )
                }
            ).init( gc, sbg )
        )

        layout.addComponent(
            new Button(
                text: 'Quit',
                font: optionFont,
                color: Color.gray,
                focusColor: Color.green,
                padding: new Box( 50, 0, 0, 0 ),
                onFocus: { cgc, del->
                    menuToggle.play()
                },
                onClick: { cgc, del->
                    menuSelect.play()
                    // TODO: is there something better?
                    System.exit( 0 );
                }
            ).init( gc, sbg )
        )

        layout.init( gc, sbg )
    }

    @Override
    public void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        starfieldLayer.update( gc, sbg, delta )
        layout.update( gc, sbg, delta )
    }

    @Override
    public void render( final GameContainer gc, final StateBasedGame stateBasedGame, final Graphics g ) throws SlickException{
        starfieldLayer.render( gc, stateBasedGame, g )
        layout.render( gc, stateBasedGame, g )
    }
}

