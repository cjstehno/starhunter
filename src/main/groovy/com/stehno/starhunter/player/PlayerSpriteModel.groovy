package com.stehno.starhunter.player

import com.stehno.games.DefaultSpriteModel

/**
 * Model state information for the player.
 */
class PlayerSpriteModel extends DefaultSpriteModel {

    private int lives = 3
    private int score = 0

    void reset(){
        lives = 3
        score = 0
    }

    int currentScore(){ score }

    int currentLives(){ lives }

    void addScore( int value ){
        score += value
    }

    void decrementLives(){
        lives--
    }
}
