package com.stehno.starhunter;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.io.IOException;

/**
 * TODO: document
 */
public class MenuState extends BasicGameState {
    /*
        TODO: background music
        TODO: click/select sounds
        TODO: title image
        TODO: options (play, quit)
     */

    static final int STATE_ID = 100;

    private static final String TITLE = "Star Hunter";
    private static final String PLAY_OPTION = "Play";
    private static final String QUIT_OPTION = "Quit";
    private Font titleFont, optionFont;

    @Override
    public int getID(){
        return STATE_ID;
    }

    @Override
    public void init( final GameContainer gameContainer, final StateBasedGame stateBasedGame ) throws SlickException{
        try {
            final java.awt.Font rawFont = java.awt.Font.createFont( java.awt.Font.TRUETYPE_FONT, MenuState.class.getResourceAsStream( "/fnt/Earth_Kid.ttf" ) );
            titleFont = new TrueTypeFont( rawFont.deriveFont( 55f ), true );
            optionFont = new TrueTypeFont( rawFont.deriveFont( 45f ), true );
        } catch( FontFormatException | IOException e ){
            e.printStackTrace();
        }
    }

    @Override
    public void render( final GameContainer gameContainer, final StateBasedGame stateBasedGame, final Graphics graphics ) throws SlickException{
        graphics.setColor( Color.red );
        graphics.setFont( titleFont );

        // TODO: this should probably be in the update method?
        final int titleX = ( gameContainer.getWidth() - titleFont.getWidth( TITLE ) ) / 2;
        graphics.drawString( TITLE, titleX, 100 );

        graphics.setColor( Color.green );

        final int playX = (gameContainer.getWidth() - optionFont.getWidth( PLAY_OPTION ) ) / 2;
        graphics.drawString( PLAY_OPTION, playX, 200 );

        graphics.setColor( Color.gray );

        final int quitX = (gameContainer.getWidth() - optionFont.getWidth( QUIT_OPTION ) ) / 2;
        graphics.drawString( QUIT_OPTION, quitX, 300 );
    }

    @Override
    public void update( final GameContainer gameContainer, final StateBasedGame stateBasedGame, final int i ) throws SlickException{

    }
}
