public class FixedObject extends GameObject {

    public FixedObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isFixed = true;
        isSolid = true;
    }
}
