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

    private final ResourceManager resourceManager

    StarHunter(){
        super( "Star Hunter" )

        resourceManager = new ResourceManager( StarHunter.class.getClassLoader(), StarHunterResources.resources() )
    }

    @Override
    void initStatesList( final GameContainer gameContainer ) throws SlickException {
        addState( new MenuState( resourceManager:resourceManager ) )
        addState( new GamePlayState( resourceManager:resourceManager ) )

//        enterState( MenuState.STATE_ID ) // FIXME: just for development speed
        enterState( GamePlayState.STATE_ID )
    }

    static void main( final String[] args ){
        try {
            AppGameContainer agc = new AppGameContainer( new StarHunter() )
            agc.setDisplayMode( 1024, 768, false )

            // TODO: this should be a command line option
            agc.showFPS = true

            agc.start();

        } catch( SlickException sex ){
            sex.printStackTrace()
        }
    }
}
