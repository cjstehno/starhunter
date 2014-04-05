package com.stehno.starhunter
import com.stehno.games.ResourceManager
import com.stehno.starhunter.alien.AlienModel
import com.stehno.starhunter.player.PlayerModel
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
        configureStates()

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

    private void configureStates(){
        StarfieldLayer starfieldLayer = new StarfieldLayer(
            resourceManager: resourceManager
        )

        PlayerModel playerModel = new PlayerModel()
        AlienModel alienModel = new AlienModel()

        HudLayer hudLayer = new HudLayer(
            resourceManager: resourceManager,
            playerModel: playerModel
        )

        addState( new MenuState(
            resourceManager: resourceManager,
            starfieldLayer: starfieldLayer
        ))

        addState( new GamePlayState(
            resourceManager: resourceManager,
            starfieldLayer: starfieldLayer,
            hudLayer: hudLayer,
            playerModel: playerModel,
            alienModel: alienModel
        ))

        addState( new GameOverState(
            resourceManager: resourceManager,
            starfieldLayer: starfieldLayer,
            hudLayer: hudLayer,
            alienModel: alienModel,
            playerModel: playerModel
        ))

        addState( new WaveTransitionState(
            resourceManager: resourceManager,
            starfieldLayer: starfieldLayer,
            hudLayer: hudLayer,
            alienModel: alienModel
        ))
    }
}
