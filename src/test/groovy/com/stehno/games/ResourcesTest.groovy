package com.stehno.games

import org.junit.Test

class ResourcesTest {

    @Test void 'loaders'(){
        def resources = Resources.loaders {
            audio 'background', 'deeper.ogg'
            image 'something', 'background.png'
            font 'spacy', 'Earth_Kid.ttf'
        }

        assert resources.audioLoaders.size() == 1
        assert resources.audioLoaders['background']

        assert resources.imageLoaders.size() == 1
        assert resources.imageLoaders['something']

        assert resources.fontLoaders.size() == 1
        assert resources.fontLoaders['spacy']
    }
}
