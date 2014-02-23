package com.stehno.starhunter
import com.stehno.games.ResourceManager
import org.newdawn.slick.*
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame

import static com.stehno.starhunter.StarHunterResources.AUDIO_BACKGROUND
import static com.stehno.starhunter.StarHunterResources.AUDIO_MENU_SELECT
import static com.stehno.starhunter.StarHunterResources.AUDIO_MENU_TOGGLE
import static com.stehno.starhunter.StarHunterResources.FONT_MAIN
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

    private static final String TITLE = "Star Hunter";

    private Font titleFont, optionFont
    private MenuItem selectedItem = MenuItem.PLAY
    private Sound menuToggle, menuSelect

    @Override
    public int getID(){
        return STATE_ID;
    }

    @Override
    public void init( final GameContainer gameContainer, final StateBasedGame stateBasedGame ) throws SlickException{
        titleFont = resourceManager.loadFont( FONT_MAIN, 55f )
        optionFont = resourceManager.loadFont( FONT_MAIN, 32f )

        final Music music = resourceManager.loadMusic( AUDIO_BACKGROUND )
        music.play();
        music.setVolume( 0.25f );

        menuToggle = resourceManager.loadSound( AUDIO_MENU_TOGGLE )
        menuSelect = resourceManager.loadSound( AUDIO_MENU_SELECT )
    }

    @Override
    public void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        final Input input = gc.getInput();
        if( input.isKeyPressed( Input.KEY_DOWN ) || input.isKeyPressed( Input.KEY_UP ) ){
            menuToggle.play();

            if( selectedItem == MenuItem.PLAY ){
                selectedItem = MenuItem.QUIT;
            } else {
                selectedItem = MenuItem.PLAY;
            }

        } else if( input.isKeyPressed( Input.KEY_ENTER ) ){
            menuSelect.play();

            if( selectedItem == MenuItem.PLAY ){
                sbg.enterState( GamePlayState.STATE_ID );
            } else {
                // TODO: is there something better?
                System.exit( 0 );
            }
        }

      /*  // FIXME: also need sound and color change on mouse-over, click selects

        if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON ) ){
//            input.getMouseX()
            // FIXME: I think I need to create a label class or something to do collsion detection
            if( overPlay ){

            } else if( overQuit ){

            }
        }*/
    }

    @Override
    public void render( final GameContainer gameContainer, final StateBasedGame stateBasedGame, final Graphics graphics ) throws SlickException{
        graphics.setColor( Color.red );
        graphics.setFont( titleFont );

        // TODO: this should probably be in the update method?
        final int titleX = ( gameContainer.getWidth() - titleFont.getWidth( TITLE ) ) / 2;
        graphics.drawString( TITLE, titleX, 200 );

        graphics.setFont( optionFont );

        // setup the play item
        graphics.setColor( selectedItem == MenuItem.PLAY ? Color.green : Color.gray );

        final int playX = (gameContainer.getWidth() - optionFont.getWidth( MenuItem.PLAY.getLabel() ) ) / 2;
        graphics.drawString( MenuItem.PLAY.getLabel(), playX, 300 );

        // setup the quit item
        graphics.setColor( selectedItem == MenuItem.QUIT ? Color.green : Color.gray );

        final int quitX = (gameContainer.getWidth() - optionFont.getWidth( MenuItem.QUIT.getLabel() ) ) / 2;
        graphics.drawString( MenuItem.QUIT.getLabel(), quitX, 350 );
    }
}

