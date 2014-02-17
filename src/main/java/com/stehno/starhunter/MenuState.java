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

    static final int STATE_ID = 100;

    private static enum MenuItem {
        PLAY("Play"),
        QUIT("Quit");

        private final String label;

        private MenuItem(final String label){
            this.label = label;
        }

        public String getLabel(){
            return label;
        }
    }

    private static final String TITLE = "Star Hunter";

    private Font titleFont, optionFont;
    private MenuItem selectedItem = MenuItem.PLAY;
    private Music music;

    @Override
    public int getID(){
        return STATE_ID;
    }

    @Override
    public void init( final GameContainer gameContainer, final StateBasedGame stateBasedGame ) throws SlickException{
        try {
            final java.awt.Font rawFont = java.awt.Font.createFont( java.awt.Font.TRUETYPE_FONT, MenuState.class.getResourceAsStream( "/fnt/Earth_Kid.ttf" ) );
            titleFont = new TrueTypeFont( rawFont.deriveFont( 55f ), true );
            optionFont = new TrueTypeFont( rawFont.deriveFont( 32f ), true );
        } catch( FontFormatException | IOException e ){
            e.printStackTrace();
        }

        music = new Music( MenuState.class.getResource( "/aud/deeper.ogg" ) );
        music.play();
        music.setVolume( 0.25f );
    }

    @Override
    public void update( final GameContainer gc, final StateBasedGame sbg, final int delta ) throws SlickException{
        final Input input = gc.getInput();
        if( input.isKeyPressed( Input.KEY_DOWN ) || input.isKeyPressed( Input.KEY_UP ) ){
            if( selectedItem == MenuItem.PLAY ){
                selectedItem = MenuItem.QUIT;
            } else {
                selectedItem = MenuItem.PLAY;
            }
        } else if( input.isKeyPressed( Input.KEY_ENTER ) ){
            if( selectedItem == MenuItem.PLAY ){
                sbg.enterState( GamePlayState.STATE_ID );
            } else {
                // TODO: is there something better?
                System.exit( 0 );
            }
        }
    }

    @Override
    public void render( final GameContainer gameContainer, final StateBasedGame stateBasedGame, final Graphics graphics ) throws SlickException{
        graphics.setColor( Color.red );
        graphics.setFont( titleFont );

        // TODO: this should probably be in the update method?
        final int titleX = ( gameContainer.getWidth() - titleFont.getWidth( TITLE ) ) / 2;
        graphics.drawString( TITLE, titleX, 200 );

        graphics.setFont( optionFont );

        // setup the play item
        graphics.setColor( selectedItem == MenuItem.PLAY ? Color.green : Color.gray );

        final int playX = (gameContainer.getWidth() - optionFont.getWidth( MenuItem.PLAY.getLabel() ) ) / 2;
        graphics.drawString( MenuItem.PLAY.getLabel(), playX, 300 );

        // setup the quit item
        graphics.setColor( selectedItem == MenuItem.QUIT ? Color.green : Color.gray );

        final int quitX = (gameContainer.getWidth() - optionFont.getWidth( MenuItem.QUIT.getLabel() ) ) / 2;
        graphics.drawString( MenuItem.QUIT.getLabel(), quitX, 350 );
    }
}