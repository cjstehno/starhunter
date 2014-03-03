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
 *
 * Paths are relative to the resource root for each resource type on the classpath.
 */
class Resources {

    final Map<String,Resource> audioLoaders = new HashMap<>()
    final Map<String,Resource> imageLoaders = new HashMap<>()
    final Map<String,Resource> fontLoaders = new HashMap<>()

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

    void images( final String name, final String pathPattern, final Range range ){
        int padding = pathPattern.count('#')
        range.each {
            image( "${name}_${it}", pathPattern.replace( '#'*padding, (it as String).padLeft(padding, '0') ) )
        }
    }

    void font( final String name, final String path ){
        fontLoaders[name] = new Resource( "fnt/$path" )
    }
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
