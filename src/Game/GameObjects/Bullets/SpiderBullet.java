package Game.GameObjects.Bullets;

import Game.GameObjects.CharacterObjects.Enemies.MiniEnemy;

public class SpiderBullet extends BulletObject {


    public SpiderBullet(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isFixed = false;

        range = 3000;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        if(hp <= 0){
            world.gameObjects.add(new MiniEnemy(x, y));
        }
    }
}
