package com.stehno.starhunter;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * FIXME: document
 */
public class GamePlayState extends BasicGameState {

    static int STATE_ID = 2;

    @Override
    public int getID(){
        return STATE_ID;
    }

    @Override
    public void init( final GameContainer gameContainer, final StateBasedGame stateBasedGame ) throws SlickException{

    }

    @Override
    public void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{

    }

    @Override
    public void render( final GameContainer gc, final StateBasedGame sbg, final Graphics g ) throws SlickException{
        g.setColor( Color.white );
        g.drawString( "Game Play Time!", 200, 200 );
    }
}
