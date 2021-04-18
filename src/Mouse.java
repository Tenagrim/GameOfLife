public class Mouse {
    private MouseState  state;
    private Coords      pos;
    private Coords      prevPos;


    public void setPos(Coords pos) {
        this.prevPos = this.pos;
        this.pos = pos;
    }

    public void setState(MouseState state) {
        this.state = state;
    }

    public Coords getPrevPos() {
        return prevPos;
    }

    public Coords getPos() {
        return pos;
    }

    public MouseState getState() {
        return state;
    }
}
