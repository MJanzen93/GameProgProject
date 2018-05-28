package Game.GameObjects.Items;

import javax.imageio.ImageIO;

import Game.AudioPlayer;
import Game.GameObjects.CharacterObjects.CharacterObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Coin extends ItemObject {

    public Coin(double startX, double startY) {
        super(startX, startY);
        setColor(Color.yellow);
        isFixed = true;
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\coin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void applyItem(CharacterObject obj){
        hp = 0;
        AudioPlayer.shortSound(".\\src\\Game\\Sounds\\coin.wav",0.15);
    }
}
