package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    public static boolean KEY_W, KEY_S, KEY_A, KEY_D;
    public static boolean KEY_E;
    public static boolean KEY_SPACE;

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'w') KEY_W = true;
        if (keyEvent.getKeyChar() == 's') KEY_S = true;
        if (keyEvent.getKeyChar() == 'a') KEY_A = true;
        if (keyEvent.getKeyChar() == 'd') KEY_D = true;

        if (keyEvent.getKeyChar() == 'e') KEY_E = true;

        if (keyEvent.getKeyChar() == ' ') KEY_SPACE = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'w') KEY_W = false;
        if (keyEvent.getKeyChar() == 's') KEY_S = false;
        if (keyEvent.getKeyChar() == 'a') KEY_A = false;
        if (keyEvent.getKeyChar() == 'd') KEY_D = false;

        if (keyEvent.getKeyChar() == 'e') KEY_E = false;

        if (keyEvent.getKeyChar() == ' ') KEY_SPACE = false;
    }
}
