package Game;

import Game.GameObjects.GameObject;

import java.io.*;

public class MapParser {

    public static GameObject[] getGameObjects(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        String result = "";
        String line;

        //read file line by line
        while((line = br.readLine()) != null){
            result += line;
        }

        //process result...


        return null;
    }
}
