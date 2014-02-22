package com.stehno.games

import org.junit.Test

class ResourcesTest {

    @Test void 'loaders'(){
        def resources = Resources.loaders {
            audio 'background', 'deeper.ogg'
            image 'something', 'background.png'
        }

        assert resources.audioLoaders.size() == 1
        assert resources.audioLoaders['background']

        assert resources.imageLoaders.size() == 1
        assert resources.imageLoaders['something']
    }
}
