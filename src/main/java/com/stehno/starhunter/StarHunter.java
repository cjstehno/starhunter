package com.stehno.starhunter;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * TODO: document
 */
public class StarHunter extends StateBasedGame {

    public StarHunter(){
        super( "Star Hunter" );
    }

    @Override
    public void initStatesList( final GameContainer gameContainer ) throws SlickException {
        addState( new MenuState() );
        enterState( MenuState.STATE_ID );
    }

    public static void main( final String[] args ){
        try {
            AppGameContainer agc = new AppGameContainer( new StarHunter() );
            agc.setDisplayMode( 1024, 768, false );

            // TODO: this should be a command line option
            agc.setShowFPS( true );

            agc.start();

        } catch( SlickException sex ){
            sex.printStackTrace();
        }
    }
}