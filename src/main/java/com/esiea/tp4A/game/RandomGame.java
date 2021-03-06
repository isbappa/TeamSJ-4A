package com.esiea.tp4A.game;

import com.esiea.tp4A.domain.*;

import java.io.Serializable;
import java.util.*;

public class RandomGame implements Serializable {

    public int getRandomIntFromList(List list){
        Random rand = new Random();
        return (int)((List) list).get(rand.nextInt(list.size()));
    }

    public int getRandomMapSize(){
        return getRandomIntFromList(Arrays.asList(100, 300, 600));
    }

    public int getRandomLaserRange(){
        return getRandomIntFromList(Arrays.asList(5, 30, 601));
    }

    public int getRandomIntWithinRange(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public Set<Obstacle> generateObstaclesPosition(int mapSize, int limit_neg, int limit_pos){
        int nbObstacles = (int) Math.round(mapSize*mapSize*0.15);
        Set<Obstacle> obstacles = new HashSet<>();
        while (nbObstacles > 0) {
            Obstacle temp_obstacle = new Obstacle(getRandomIntWithinRange(limit_neg, limit_pos), getRandomIntWithinRange(limit_neg, limit_pos));
            if(obstacles.contains(temp_obstacle)) continue;
            else{
                obstacles.add(temp_obstacle); nbObstacles--;
            }
        }
        return obstacles;
    }

    public MyRover generateRandomRover(TheGame theGame, Set<Obstacle> obstacles, HashMap<String, MyRover> rovers, int laserRange, Mars map, String player){
        if(rovers.size() == 50) return null;
        while(true){
            Direction randomDirection = Direction.values()[new Random().nextInt(Direction.values().length)];
            Obstacle player_position = new Obstacle(getRandomIntWithinRange(map.getLimit_neg(), map.getLimit_pos()), getRandomIntWithinRange(map.getLimit_neg(), map.getLimit_pos()));
            if(obstacles.contains(player_position)) continue;
            MyRover rover = new MyRover(theGame, player_position.getX(), player_position.getY(), randomDirection, laserRange, map, player);
            if(rovers.containsKey(rover.getPlayer())) continue;
            else return rover;
        }
    }
}
