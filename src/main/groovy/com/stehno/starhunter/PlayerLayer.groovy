package com.stehno.starhunter

import com.stehno.games.Layer
import com.stehno.games.ResourceManager
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException

/**
 * Created by cjstehno on 3/23/2014.
 */
class PlayerLayer extends Layer {

    ResourceManager resourceManager

    private Player player

    Player getPlayer(){ player }

    @Override
    PlayerLayer init( final GameContainer gc ) throws SlickException{
        player = new Player( resourceManager:resourceManager ).init( gc )
        return this
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException{
        player.update( gc, delta )
    }

    @Override
    void render( final GameContainer gc,final Graphics g ) throws SlickException{
        player.render( gc, g )
    }
}
