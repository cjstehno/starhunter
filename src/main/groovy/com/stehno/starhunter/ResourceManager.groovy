package com.stehno.starhunter

import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory
import org.newdawn.slick.Music
import org.newdawn.slick.SlickException
import org.newdawn.slick.util.DefaultLogSystem
import org.newdawn.slick.util.LogSystem

import javax.script.ScriptEngine

/**
 * A simple abstraction for resource loading with standardized file locations.
 */
class ResourceManager {
    // FIXME: support for non-preloaded resources

    private static final LogSystem log = new DefaultLogSystem();
    private final ClassLoader classLoader;
    private final Map<String,Music> music = new HashMap<>();

    public ResourceManager( final ClassLoader classLoader ){
        this.classLoader = classLoader;
    }

    public void init( final String configFile ){
        final ScriptEngine engine = new GroovyScriptEngineFactory().getScriptEngine();

        engine.put( "resources", new ResourceScript() {
            @Override
            public void music( final String name, final String file, final boolean preload ){
                music.put( name, loadMusic( name ) );
                log.info( "Loading music (" + name + ") from: " + file );
            }
        } );

        new InputStreamReader( classLoader.getResourceAsStream( "" ) ).withReader { reader->
            engine.eval( reader );
        }
    }

    public Music getMusic( final String name ){
        return music.get( name );
    }

    private Music loadMusic( final String fileName ){
        try {
            return new Music( audio( fileName ) );
        } catch( SlickException e ){
            return (Music)error( fileName, e );
        }
    }

//    public Sound loadSound( final String fileName ){
//        try {
//            return new Sound( audio( fileName ) );
//        } catch( SlickException e ){
//            return (Sound)error( fileName, e );
//        }
//    }
//
//    public Image loadImage( final String fileName ){
//        try {
//            return new Image( image( fileName ), fileName, false );
//        } catch( SlickException e ){
//            return (Image)error( fileName, e );
//        }
//    }
//
//    public Font loadFont( final String fileName ){
//        try {
//            return new Font( image( fileName ), fileName, false );
//        } catch( SlickException e ){
//            return (Image)error( fileName, e );
//        }
//    }

    private URL audio( final String fileName ){
        return classLoader.getResource( String.format( "/aud/%s", fileName ) );
    }
//
//    private InputStream image( final String fileName ){
//        return classLoader.getResourceAsStream( String.format( "/img/%s", fileName ) );
//    }
//
    private Object error( final String fileName, final Exception e ){
        log.error( "Unable to load resource file (" + fileName + "): " + e.getMessage() );
        return null;
    }

    static interface ResourceScript {

        void music( String name, String file, boolean preload );
    }
}
