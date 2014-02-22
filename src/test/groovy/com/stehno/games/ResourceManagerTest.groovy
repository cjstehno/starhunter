package com.stehno.games
import com.stehno.starhunter.StarHunter
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class ResourceManagerTest {
    // FIXME: this test is based on the StarHunter game - remove dependency

    private ResourceManager resourceManager

    @Before void before(){
        resourceManager = new ResourceManager(
            StarHunter.classLoader,
            Resources.loaders {
                audio 'background', 'deeper.ogg'
                audio 'menu-toggle', 'pop_clip_in.ogg'
                audio 'menu-select', 'button_push.ogg'
                image 'something', 'background.png'
            }
        )
    }

    @Test void 'audio loading'(){
        assert resourceManager.loadMusic( 'background' )
        assert resourceManager.loadSound( 'menu-toggle' )
    }

    // TODO: requires an opengl context in thread - see what I can do about it
    @Test @Ignore void 'image loading'(){
        assert resourceManager.loadImage( 'something' )
    }

    @Test(expected=MissingResourceException) void 'load missing sound'(){
        resourceManager.loadSound( 'not-there' )
    }

    @Test(expected=MissingResourceException) void 'load missing music'(){
        resourceManager.loadMusic( 'not-there' )
    }

    @Test(expected=MissingResourceException) void 'load missing image'(){
        resourceManager.loadImage( 'not-there' )
    }
}
