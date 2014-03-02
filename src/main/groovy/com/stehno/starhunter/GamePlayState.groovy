package com.stehno.starhunter
import com.stehno.games.ResourceManager
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.Input
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Vector2f
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame

/**
 * The game state for the main game play screen.
 */
class GamePlayState  extends BasicGameState {

    static int STATE_ID = 2

    ResourceManager resourceManager

    private Image playerImage, playerMissile
    private Map<Image,Vector2f> playerActiveMissiles = [:]
    private Vector2f playerPosition
    private float playerCeiling

    @Override
    int getID(){
        return STATE_ID;
    }

    @Override
    void init( final GameContainer gc, final StateBasedGame sbg ) throws SlickException{
        playerImage = resourceManager.loadImage( StarHunterResources.IMAGE_PLAYER_SHIP ).getScaledCopy( 0.25f )
        playerMissile = resourceManager.loadImage( StarHunterResources.IMAGE_PLAYER_MISSILE )

        playerPosition = new Vector2f( (gc.width - playerImage.width)/2, gc.height-playerImage.height-25 )

        // limit the player to the bottom of the screen
        playerCeiling = 2 * gc.height / 3
    }

    @Override
    void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        Input input = gc.getInput()

        if( playerPosition.x > 0 && input.isKeyDown( Input.KEY_LEFT ) ){
            playerPosition.x -= 1 * delta
        }
        if( playerPosition.x < (gc.width-playerImage.width) && input.isKeyDown( Input.KEY_RIGHT ) ){
            playerPosition.x += 1 * delta
        }

        if( playerPosition.y > playerCeiling && input.isKeyDown( Input.KEY_UP ) ){
            playerPosition.y -= 1 * delta
        }
        if( playerPosition.y < (gc.height-playerImage.height) && input.isKeyDown( Input.KEY_DOWN ) ){
            playerPosition.y += 1 * delta
        }

        // check for missile fire
        if( input.isKeyPressed( Input.KEY_SPACE ) && playerActiveMissiles.size() < 5 ){
            float missileX = playerPosition.x + (playerImage.width / 2) - (playerMissile.width / 2) // center of ship
            float missileY = playerPosition.y                                                       // top of ship
            playerActiveMissiles[playerMissile.copy()] = new Vector2f( missileX, missileY )
        }

        def outOfRange = []

        // update missile locations
        playerActiveMissiles.each { img, pos->
            pos.y -= 2 * delta
            if( pos.y < 0 ){
                outOfRange << img
            }
        }

        outOfRange.each {
            playerActiveMissiles.remove( it )
        }
    }

    @Override
    void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        g.drawImage( playerImage, playerPosition.x, playerPosition.y )

        playerActiveMissiles.each { img, pos->
            g.drawImage( img, pos.x, pos.y )
        }
    }
}

