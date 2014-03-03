package com.stehno.starhunter
import org.newdawn.slick.GameContainer
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Vector2f

import static com.stehno.starhunter.StarHunterResources.IMAGE_PLAYER_MISSILE
/**
 * Created by cjstehno on 3/3/14.
 */
class Missile extends Actor {

    Vector2f launcherPosition

    @Override
    Missile init( final GameContainer gc ) throws SlickException{
        aliveRenderable = resourceManager.loadImage( IMAGE_PLAYER_MISSILE )

        position = new Vector2f(
            launcherPosition.x - (aliveRenderable.width / 2) as float,
            launcherPosition.y
        )

        bounds = new Rectangle( position.x, position.y, aliveRenderable.width, aliveRenderable.height )

        return this
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException{
        position.y -= 2 * delta
        bounds.y -= 2 * delta

        if( bounds.y < 0 ){
            state = State.DEAD
        }
    }
}
