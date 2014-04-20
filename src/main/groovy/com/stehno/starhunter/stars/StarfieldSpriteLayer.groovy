package com.stehno.starhunter.stars
import com.stehno.games.ResourceManager
import com.stehno.games.SpriteLayer
import com.stehno.starhunter.StarHunterResources
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.SlickException
import org.newdawn.slick.state.StateBasedGame
/**
 * Manages the starfield background layer.
 */
class StarfieldSpriteLayer extends SpriteLayer {

    ResourceManager resourceManager

    private Image image

    @Override
    StarfieldSpriteLayer init( final GameContainer gc,final StateBasedGame sbg ) throws SlickException{
        if( !image ){
            image = resourceManager.loadImage( StarHunterResources.IMAGE_STARFIELD )
        }
        return this
    }

    @Override
    void update( final GameContainer gc,final StateBasedGame sbg,final int delta ) throws SlickException{
        // nothing
    }

    @Override
    void render( final GameContainer gc,final StateBasedGame sbg,final Graphics g ) throws SlickException{
        g.drawImage image, 0f, 0f
    }
}
