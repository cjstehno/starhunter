package com.stehno.games
/**
 * Created by cjstehno on 3/23/2014.
 */
abstract class Layer implements LifecycleParticipant<Layer>{

    void checkCollisions( Layer other ){
        // Ignore collisions by default
    }
}
