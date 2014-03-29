package com.stehno.starhunter
import com.stehno.games.Layer
import com.stehno.games.ResourceManager
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.SlickException

/**
 * Simple static star field background.
 */
class StarfieldLayer extends Layer {

    ResourceManager resourceManager

    private Image image

    @Override
    StarfieldLayer init( final GameContainer gc ) throws SlickException {
        image = resourceManager.loadImage( StarHunterResources.IMAGE_STARFIELD )
        return this
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException{

    }

    @Override
    void render( final GameContainer gc,final Graphics g ) throws SlickException{
        g.drawImage( image, 0f, 0f )
    }
}
