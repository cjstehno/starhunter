package com.stehno.starhunter.alien

/**
 * Created by cjstehno on 4/5/2014.
 */
class AlienModel {

    int currentWave = 1
    int currentWaveSize = 4
    int currentWaveKilled = 0

    private int lastWave = currentWave

    boolean waveComplete(){
        currentWaveKilled >= currentWaveSize
    }

    void reset(){
        currentWave = 1
        currentWaveSize = 4
        currentWaveKilled = 0
    }

    void setupNextWave(){
        currentWave += 1
        currentWaveSize += 2
        currentWaveKilled = 0
    }

    boolean waveChanged(){
        boolean changed = currentWave != lastWave
        lastWave = currentWave
        return changed
    }
}
