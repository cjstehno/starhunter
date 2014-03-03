package com.stehno.starhunter

import com.stehno.games.Resources

/**
 * Provides the loadable resources and their names.
 */
class StarHunterResources {

    static final String AUDIO_BACKGROUND = 'background'
    static final String AUDIO_MENU_TOGGLE = 'menu-toggle'
    static final String AUDIO_MENU_SELECT = 'menu-select'
    static final String FONT_MAIN = 'main-font'
    static final String IMAGE_PLAYER_SHIP = 'player-ship'
    static final String IMAGE_PLAYER_MISSILE = 'player-missile'
    static final String AUDIO_PLAYER_MISSILE = 'player-missile-sound'
    static final String IMAGES_ALIEN_SHIP = 'alien-ship'
    static final String IMAGES_EXPLOSION = 'explosion'

    static Resources resources(){
        Resources.loaders {
            audio AUDIO_BACKGROUND, 'deeper.ogg'
            audio AUDIO_MENU_TOGGLE, 'pop_clip_in.ogg'
            audio AUDIO_MENU_SELECT, 'button_push.ogg'
            font  FONT_MAIN, 'Earth_Kid.ttf'
            image IMAGE_PLAYER_SHIP, 'player_ship.png'
            image IMAGE_PLAYER_MISSILE, 'player_missile.png'
            audio AUDIO_PLAYER_MISSILE, 'pew_pew.ogg'
            images IMAGES_ALIEN_SHIP, 'alien_#.png', 0..1
            images IMAGES_EXPLOSION, 'expl_06_####.png', 0..31
        }
    }
}
