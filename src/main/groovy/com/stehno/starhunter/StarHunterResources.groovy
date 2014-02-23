package com.stehno.starhunter

import com.stehno.games.Resources

/**
 * Provides the loadable resources and their names.
 */
class StarHunterResources {

    static final AUDIO_BACKGROUND = 'background'
    static final String AUDIO_MENU_TOGGLE = 'menu-toggle'
    static final String AUDIO_MENU_SELECT = 'menu-select'
    static final String FONT_MAIN = 'main-font'

    static Resources resources(){
        Resources.loaders {
            audio AUDIO_BACKGROUND, 'deeper.ogg'
            audio AUDIO_MENU_TOGGLE, 'pop_clip_in.ogg'
            audio AUDIO_MENU_SELECT, 'button_push.ogg'
            font  FONT_MAIN, 'Earth_Kid.ttf'
        }
    }
}
