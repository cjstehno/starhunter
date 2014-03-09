package com.stehno.starhunter
import org.newdawn.slick.Animation
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Image
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.geom.Vector2f

import static com.stehno.starhunter.StarHunterResources.IMAGES_ALIEN_SHIP
import static com.stehno.starhunter.StarHunterResources.IMAGES_EXPLOSION

/**
 * TODO: document
 */
class Alien extends Actor {

    private final Random random = new Random()
    private float speed

    float getSpeed(){ speed }

    @SuppressWarnings( "GroovyAssignabilityCheck" )
    Vector2f getBombBayPosition(){
        new Vector2f(
            position.x + (aliveRenderable.width / 2) as float,
            (position.y) as float
        )
    }

    @Override
    Alien init( final GameContainer gc ) throws SlickException {
        aliveRenderable = new Animation(
            resourceManager.loadImages( IMAGES_ALIEN_SHIP )*.getScaledCopy( 0.12f ) as Image[],
            750
        )

        dyingRenderable = new Animation(
            resourceManager.loadImages( IMAGES_EXPLOSION ),
            50
        )
        dyingRenderable.looping = false

        spawn gc

        return this
    }

    @Override
    void update( final GameContainer gc, final int delta ) throws SlickException {
        float movement = speed * delta
        position.y += movement
        bounds.y += movement

        if( bounds.y > gc.height ){
            if( alive ) spawn gc
        }
    }

    private void spawn( final GameContainer gc ){
        speed = ((random.nextInt( 4 ) + 1) as float) / 10f

        float xpos = random.nextInt( (gc.width - aliveRenderable.width) as int) as float

        position = new Vector2f( xpos, 0f - aliveRenderable.height as float )
        bounds = new Rectangle( position.x, position.y, aliveRenderable.width as float, aliveRenderable.height as float )
    }
}
