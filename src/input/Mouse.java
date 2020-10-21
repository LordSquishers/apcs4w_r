package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    public static boolean LEFT_HELD, RIGHT_HELD, MIDDLE_MW_HELD;
    public static boolean MOUSE_IN_WINDOW;

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) LEFT_HELD = true;
        if (mouseEvent.getButton() == MouseEvent.BUTTON2) RIGHT_HELD = true;
        if (mouseEvent.getButton() == MouseEvent.BUTTON3) MIDDLE_MW_HELD = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) LEFT_HELD = false;
        if (mouseEvent.getButton() == MouseEvent.BUTTON2) RIGHT_HELD = false;
        if (mouseEvent.getButton() == MouseEvent.BUTTON3) MIDDLE_MW_HELD = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        MOUSE_IN_WINDOW = true;
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        MOUSE_IN_WINDOW = false;
    }
}
