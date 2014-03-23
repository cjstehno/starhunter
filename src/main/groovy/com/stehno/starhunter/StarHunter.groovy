package com.stehno.starhunter
import com.stehno.games.ResourceManager
import org.newdawn.slick.AppGameContainer
import org.newdawn.slick.GameContainer
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.StateBasedGame
/**
 * Main entry point for the StarHunter game.
 */
class StarHunter  extends StateBasedGame {

    private static final String AUTOSTART = 'starhunter.autostart'
    private final ResourceManager resourceManager

    StarHunter(){
        super( "Star Hunter" )

        resourceManager = new ResourceManager( StarHunter.class.getClassLoader(), StarHunterResources.resources() )
    }

    @Override
    void initStatesList( final GameContainer gameContainer ) throws SlickException {
        addState( new MenuState( resourceManager:resourceManager ) )
        addState( new GamePlayState( resourceManager:resourceManager ) )

        if( System.getProperty( AUTOSTART ) ){
            // Useful for development to skip the startup screen, when working on GamePlayState
            enterState( GamePlayState.STATE_ID )

        } else {
            enterState( MenuState.STATE_ID )
        }
    }

    static void main( final String[] args ){
        try {
            AppGameContainer agc = new AppGameContainer( new StarHunter() )

            // TODO: support for command line options
            // TODO: fullscreen should be default with windowed option
            // TODO: find best supported resolutions or allow options on start
            agc.setDisplayMode( 1024, 768, false )

            agc.setTargetFrameRate( 100 )

            // TODO: this should be a command line option
            agc.showFPS = false

            agc.start();

        } catch( SlickException sex ){
            sex.printStackTrace()
        }
    }
}
