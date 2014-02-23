package com.stehno.games

import org.newdawn.slick.*

/**
 * A simple abstraction for resource loading with standardized file locations.
 */
class ResourceManager {
    // TODO: pull out an interface for this

    private final ClassLoader classLoader
    private final Resources resources

    public ResourceManager( final ClassLoader classLoader, final Resources resources ){
        this.classLoader = classLoader
        this.resources = resources
    }

    Music loadMusic( final String name ){
        new Music( loadUrl( resources.audioLoaders, name ) )
    }

    Sound loadSound( final String name ){
        new Sound( loadUrl( resources.audioLoaders, name ) )
    }

    Image loadImage( final String name ){
        new Image( loadStream( resources.imageLoaders, name ), name, false )
    }

    Font loadFont( final String name, final float fontSize ){
        new TrueTypeFont(
            java.awt.Font.createFont(
                java.awt.Font.TRUETYPE_FONT,
                loadStream( resources.fontLoaders, name )
            ).deriveFont( fontSize ),
            true
        )
    }

    private InputStream loadStream( final Map<String,Resource> loaders, final String name ){
        def loader = loaders[name]
        if( loader ){
            return loader.resolveStream( classLoader )
        } else {
            throw new MissingResourceException("No resource loader was found for '$name'")
        }
    }

    private URL loadUrl( final Map<String,Resource> loaders, final String name ){
        def loader = loaders[ name ]
        if( loader ){
            return loader.resolveUrl( classLoader )
        } else {
            throw new MissingResourceException("No resource loader was found for '$name'")
        }
    }
}

/**
 * Exception denoting that a requested game resource does not exist or was not found.
 */
class MissingResourceException extends RuntimeException {

    MissingResourceException( final String message ){
        super(message)
    }
}
