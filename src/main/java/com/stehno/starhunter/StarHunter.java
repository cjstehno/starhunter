package com.stehno.starhunter;

import org.newdawn.slick.*;

public class StarHunter extends BasicGame {

    private final int DELAY = 1500;
    private int elapsedTime;
    private Image backGround;

    private int playerX, playerY;
    private int ovalX, ovalY;

    private Music music;
    private Sound sound;

    public StarHunter(){
        super( "GameOne" );
    }

    // NOTE: seems line 24-bit png is supported, not 8

    @Override
    public void init( GameContainer gc ) throws SlickException{
        backGround = new Image(
                                  StarHunter.class.getResourceAsStream("/img/background.png"),
                                  "background.png",
                                  false
        );

        music = new Music( StarHunter.class.getResource( "/aud/deeper.ogg" ) );
        music.play();

        sound = new Sound( StarHunter.class.getResource( "/aud/explosion.ogg" ) );
    }

    @Override
    public void update( GameContainer gc, int delta ) throws SlickException{
        if(elapsedTime >= DELAY ) {
            elapsedTime -= DELAY; // tracks of reminder
        } else {
            elapsedTime += delta;
        }

        Input input = gc.getInput();
        if( playerX > 0 && input.isKeyDown( Input.KEY_LEFT ) )  playerX -= 1 * delta;
        if( playerX < 507 && input.isKeyDown( Input.KEY_RIGHT ) ) playerX += 1 * delta;
        if( playerY > 0 && input.isKeyDown( Input.KEY_UP ) )    playerY -= 1 * delta;
        if( playerY < 507 && input.isKeyDown( Input.KEY_DOWN ) )  playerY += 1 * delta;

        if( input.isMouseButtonDown( Input.MOUSE_LEFT_BUTTON ) ){
            ovalX = input.getMouseX();
            ovalY = input.getMouseY();
            sound.play();
        }
    }

    @Override
    public void render( GameContainer gc, Graphics g ) throws SlickException{
        g.drawImage( backGround, 0f, 0f );

        g.setColor( Color.red );
        g.fillRect( playerX, playerY, 5, 5 );

        g.setColor( Color.yellow );
        g.fillOval( ovalX, ovalY, 5, 5 );
    }

    public static void main( final String[] args ){
        try {
            AppGameContainer agc = new AppGameContainer( new StarHunter() );
            agc.setDisplayMode( 512, 512, false );
            agc.setShowFPS( true );
            agc.start();
        } catch( SlickException sex ){
            sex.printStackTrace();
        }
    }
}