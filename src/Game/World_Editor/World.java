package Game.World_Editor;

import java.util.ArrayList;
import java.util.List;

import static Game.World_Editor.ConstantValues.FRAME_MINIMUM_MILLIS;
import static java.lang.Thread.sleep;

public class World {

    private InputSystem inputSystem;
    private Camera camera;

    private double diffSeconds;
    private double secondsPassed;

    public double worldPartX = 0;
    public double worldPartY = 0;

    private List<GameObject> gameObjects;

    public void init(){
        gameObjects = new ArrayList<>();
        gameObjects.add(new GameObject(100,100,50,50));
        gameObjects.add(new GameObject(1000,100,100,100));
    }

    public void run(){
        long lastTick = System.currentTimeMillis();
        while(true)
        {
            // calculate elapsed time
            //
            long currentTick    = System.currentTimeMillis();
            long diffMillis   = (currentTick-lastTick);

            if(diffMillis<FRAME_MINIMUM_MILLIS)
            {
                try{ sleep(FRAME_MINIMUM_MILLIS-diffMillis);} catch(Exception ex){}
                currentTick = System.currentTimeMillis();
                diffMillis  = currentTick-lastTick;
            }

            diffSeconds  = diffMillis/1000.0;
            secondsPassed+=diffSeconds;
            lastTick            = currentTick;


            processUserInput();

            adjustWorldPart();

            camera.clear();

            for(int i = 0; i < gameObjects.size(); i++) {
                camera.draw(gameObjects.get(i));
            }

            camera.redraw();
        }
    }

    public void processUserInput(){

    }

    int diffX = 0;
    int diffY = 0;
    public void adjustWorldPart(){
        if(inputSystem.mousePressed){
            int mouseX = inputSystem.mouseX;
            int mouseY = inputSystem.mouseY;
            int mouseXd = inputSystem.mouseXd;
            int mouseYd = inputSystem.mouseYd;

            GameObject object = null;
            for (int i = 0; i < gameObjects.size(); i++){
                int x = (int) (gameObjects.get(i).x - worldPartX);
                int y = (int) (gameObjects.get(i).y - worldPartY);
                if(mouseXd > x && mouseXd < x + gameObjects.get(i).width &&
                        mouseYd > y && mouseYd < y + gameObjects.get(i).height){
                    object = gameObjects.get(i);
                    break;
                }
            }

            if(object != null){
                object.x = mouseXd + worldPartX - object.width/2;
                object.y = mouseYd + worldPartY - object.height/2;
            }else{
                if(diffX != mouseXd - mouseX){
                    diffX = mouseXd - mouseX;
                    worldPartX -= diffX*0.05;
                }
                if(diffY != mouseYd - mouseY){
                    diffY = mouseYd - mouseY;
                    worldPartY -= diffY*0.05;
                }
            }
        }
    }

    public void setInputSystem(InputSystem _inInputSystem){
        inputSystem = _inInputSystem;
    }

    public void setGraphicSystem(Camera _camera){
        camera = _camera;
    }
}
