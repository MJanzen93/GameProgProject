package Game.GameObjects.Platfrom;

public class FixedPlattform extends Plattform {
    public FixedPlattform(double startX, double startY, int width, int height) {
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
