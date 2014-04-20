package com.stehno.starhunter.alien

import com.stehno.games.Sprite
import com.stehno.starhunter.StarHunterResources
import org.newdawn.slick.Animation
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Image
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Vector2f

/**
 * Visual representation of the alien enemy.
 */
class AlienSprite extends Sprite {

    private final Random random = new Random()
    private float speed

    Vector2f getBombBayPosition(){
        new Vector2f(
            model.position.x + (aliveRenderable.width / 2) as float,
            (model.position.y) as float
        )
    }

    @Override
    AlienSprite init( final GameContainer gc ) throws SlickException{
        aliveRenderable = new Animation(
            resourceManager.loadImages( StarHunterResources.IMAGES_ALIEN_SHIP )*.getScaledCopy( 0.12f ) as Image[],
            750
        )

        dyingRenderable = new Animation(
            resourceManager.loadImages( StarHunterResources.IMAGES_EXPLOSION ),
            50
        )
        dyingRenderable.looping = false

        spawn gc

        return this
    }

    @Override
    void update( final GameContainer gc,final int delta ) throws SlickException{
        move 0f, speed, delta

        if( model.collisionBounds.y > gc.height ){
            if( model.alive ) spawn gc
        }
    }

    private void spawn( final GameContainer gc ){
        speed = ((random.nextInt( 4 ) + 1) as float) / 10f

        float xpos = random.nextInt( (gc.width - (aliveRenderable.width as int)) as int) as float

        model.position = new Vector2f( xpos, 0f - (aliveRenderable.height as float) as float )
        model.collisionBounds = new Rectangle(
            model.position.x,
            model.position.y,
            aliveRenderable.width as float,
            aliveRenderable.height as float
        )
    }
}
