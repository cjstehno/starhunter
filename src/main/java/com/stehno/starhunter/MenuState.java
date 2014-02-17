package com.stehno.starhunter;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * TODO: document
 */
public class MenuState extends NiftyBasicGameState {

    @Override
    protected void prepareNifty( final Nifty nifty, final StateBasedGame stateBasedGame ){
        nifty.fromXml( "menu", MenuState.class.getResourceAsStream( "/gui/menu.xml" ), "start" );
    }

    @Override
    public int getID(){
        return 0;
    }
}
