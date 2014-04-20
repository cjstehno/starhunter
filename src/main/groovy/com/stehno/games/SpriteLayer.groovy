package com.stehno.games
/**
 * A SpriteLayer is a manager/controller used to manage one or more Sprites (though this is not
 * a requirement). SpriteLayers should handle most of the game logic and Sprite-to-Sprite interaction
 * logic for it's Sprites - actual sprite rendering should be offloaded to the Sprites themselves.
 */
abstract class SpriteLayer implements LifecycleParticipant<SpriteLayer>{

    /**
     * Provides a means of checking collisions with the contents of the other SpriteLayer.
     *
     * @param other the sprite layer to be checked against this layer.
     */
    void checkCollisions( final SpriteLayer other ){
        // Ignore collisions by default
    }
}
