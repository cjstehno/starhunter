package com.stehno.starhunter

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetErrorListener
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.Disposable

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion

@Singleton
class Assets implements Disposable, AssetErrorListener {

    AssetPlayer player

    private static final String TAG = Assets.name
    private static final String OBJECTS_ATLAS = 'images/starhunter.pack.atlas'

    private AssetManager manager

    void init(AssetManager manager) {
        this.manager = manager
        manager.errorListener = this

        TextureAtlas objectsAtlas = manager.load(OBJECTS_ATLAS, TextureAtlas)
        manager.finishLoading()

        Gdx.app.debug TAG, "# of assets loaded: ${manager.assetNames.size}"
        manager.assetNames.each { a ->
            Gdx.app.debug TAG, "asset: $a"
        }

        objectsAtlas.textures*.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)

        player = new AssetPlayer(objectsAtlas)
    }

    @Override
    void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error TAG, "Couldn't load asset: ${asset.fileName}", throwable as Exception
    }

    @Override
    void dispose() {
        manager.dispose()
    }

    class AssetPlayer {
        final AtlasRegion ship

        AssetPlayer(TextureAtlas atlas) {
            ship = atlas.findRegion('player_ship')
        }
    }
}
