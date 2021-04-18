import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainKeyListener  implements KeyListener {

    private Form form;

    public MainKeyListener(Form form)
    {
        this.form = form;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        form.keyPressedEventHandler(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
