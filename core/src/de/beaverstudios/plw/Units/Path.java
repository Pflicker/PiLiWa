package de.beaverstudios.plw.Units;

import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.PlwGame;

/**
 * Created by paul on 20.03.16.
 */
public class Path {
    /*
    public Path(){

    }

    public static void findPath(Unit u) {

        float[] vec0 = new float[2];
        float[] vec1 = new float[2];

        /*
        float[] vec2 = new float[2];

        vec0[0] = u.getX();
        vec0[1] = u.getY();
        vec1[0] = u.getTarget().getX();
        vec1[1] = u.getTarget().getY();


        //System.out.println(u.getDistTarget());

        for (int i = 0; i < 2; i++) {
            vec2[i] = (vec1[i] - vec0[i])*u.getMovementspeed()/u.getDistTarget();
        }
        u.setDx(vec2[0]);
        u.setDy(vec2[1]);
        //System.out.println(u.getDx()+ " "+u.getDy());



        findNN(u);
        findTarget(u);
        findVec(u, u.getTarget());

        if (u.getDistTarget() < u.getRange()){
            u.setFight(true);
            u.setDx(0f);
            u.setDy(0f);
        }
        else if (checkPath(u)){
            u.setDx(u.vec[1]);
            u.setDy(-u.vec[0]);
            //System.out.println(u.getDx() + " " + u.getDy());
        }
        else{
            u.setDx(u.vec[0]);
            u.setDy(u.vec[1]);
        }
    }

    static void findTarget(Unit u){
        double dist;
        boolean trigger;

        trigger = false;
        u.setDistTarget(100000);
        for (Unit unit_ptr : u.NNtable){

            if (unit_ptr.getPlayer() == Game.opponent(u.getPlayer())){
                dist = java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - unit_ptr.getX(), 2) + java.lang.Math.pow(u.getY() - unit_ptr.getY(), 2));
                if (dist < PlwGame.DET_RANGE) {
                    trigger = true;
                    if (dist < u.getDistTarget()) {
                        u.setDistTarget((float) dist);
                        u.setTarget(unit_ptr);
                    }
                }
            }
        }
        if (!trigger) {
            for(Player p : Game.players)
                if (u.getPlayer() == Game.opponent(p)) {
                    u.setTarget(p.getUnits().get(0));
                    u.setDistTarget((float) java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - u.getTarget().getX(), 2) + java.lang.Math.pow(u.getY() - u.getTarget().getY(), 2)));
            }
        }
    }

    static void findVec(Unit u, Unit unit_ptr){

        float dist;
        float[] vec0 = new float[2];
        float[] vec1 = new float[2];

        vec0[0] = u.getX();
        vec0[1] = u.getY();
        vec1[0] = unit_ptr.getX();
        vec1[1] = unit_ptr.getY();

        //System.out.println(u.getDistTarget());

        dist = abs(u, unit_ptr);
        for (int i = 0; i < 2; i++) {
            u.vec[i] = (vec1[i] - vec0[i])/dist;
        }
    }

    static boolean checkPath(Unit u){
        float a,b,c;

        for (Unit unit_ptr : u.NNtable) {
            b = (u.getTarget().getX() - u.getX())*(unit_ptr.getX() - u.getX()) + (u.getTarget().getY() - u.getY())*(unit_ptr.getY() - u.getY());

            if (b < 50 && b != 0) {
                c = abs(u, unit_ptr);
                a = (float) java.lang.Math.sqrt(java.lang.Math.pow(c, 2) - java.lang.Math.pow(b, 2));
                //System.out.println(a);
                if (a > 10) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void findNN(Unit u) {

        int row;
        int col;
        int size;
        int rowMax;
        int colMax;
        double dist;

        u.NNtable.clear();
        Unit unit_ptr;

        row = u.getGridX() - 1;
        col = u.getGridY() - 1;
        rowMax = u.getGridX() + 1;
        colMax = u.getGridY() + 1;

        if (u.getGridX() == 0) {
            row = u.getGridX();
        }

        if (u.getGridX() == PlwGame.GRID_RES) {
            rowMax = u.gridX;
        }

        if (u.getGridY() == 0) {
            col = u.getGridY();
        }

        if (u.getGridY() == PlwGame.GRID_RES) {
            colMax = u.getGridY();
        }
        for (int i = row; i < rowMax; i++) {
            for (int j = col; j < colMax; j++) {

                size = Grid.gridTable.get(i).get(j).size();

                for (int k = 0; k < size; k++) {
                    unit_ptr = Grid.gridTable.get(i).get(j).get(k);
                    //vllt dist calc rausnehmen für performance
                    dist = abs(u,unit_ptr);
                    if (dist < PlwGame.DET_RANGE) {
                        u.NNtable.add(unit_ptr);
                    }
                }
            }
        }
    }


    static float abs(Unit u1, Unit u2){

        float dist;
        dist = (float) java.lang.Math.sqrt(java.lang.Math.pow(u1.getX() - u2.getX(), 2) + java.lang.Math.pow(u1.getY() - u2.getY(), 2));
        return dist;
    }
*/
}
