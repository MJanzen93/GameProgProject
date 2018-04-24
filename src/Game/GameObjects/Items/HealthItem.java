package Game.GameObjects.Items;

import Game.GameObjects.MovableObject;
import Game.GameObjects.Player;

import java.awt.*;

public class HealthItem extends ItemObject{

    public HealthItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.red);
        isFixed = true;
    }

    public void setHealth(Player player){
        player.hp = player.maxHP;
    }

    @Override
    public void move(double diffSeconds) {
        if(!isFixed){
            super.move(diffSeconds);
        }
    }
}
