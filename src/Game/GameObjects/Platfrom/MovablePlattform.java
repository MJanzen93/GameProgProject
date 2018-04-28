package Game.GameObjects.Platfrom;

public class MovablePlattform extends Plattform {

    public MovablePlattform(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }
}
