import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMouseListener implements MouseListener {
    private Form form;

    public MainMouseListener(Form form)
    {
        this.form = form;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        form.mousePressedEventHandler(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        form.mouseReleasedEventHandler(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
