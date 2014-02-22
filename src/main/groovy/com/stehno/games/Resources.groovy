package com.stehno.games

import groovy.transform.Immutable

/**
 * Simple game-specific resource loading configuration DSL. Used to define the specific
 * resources to be available via the resource loader.
 *
 * Resources.loaders {
 *      audio 'background', 'some_sounds.ogg'
 *      image 'badguy', 'alien_sprite.png'
 * }
 */
class Resources {

    final Map<String,Resource> audioLoaders = new HashMap<>()
    final Map<String,Resource> imageLoaders = new HashMap<>()

    static Resources loaders( Closure closure ){
        Resources resources = new Resources()
        closure.delegate = resources
        closure()
        return resources
    }

    void audio( final String name, final String path ){
        audioLoaders[name] = new Resource( "aud/$path" )
    }

    void image( final String name, final String path ){
        imageLoaders[name] = new Resource( "img/$path" )
    }

//    void font()// FIXME: working here

}

@Immutable
class Resource {

    String path

    URL resolveUrl( ClassLoader classLoader ){
        classLoader.getResource( path )
    }

    InputStream resolveStream( ClassLoader classLoader ){
        classLoader.getResourceAsStream( path )
    }
}
