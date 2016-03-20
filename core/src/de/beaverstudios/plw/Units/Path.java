package de.beaverstudios.plw.Units;

/**
 * Created by paul on 20.03.16.
 */
public class Path {

    public Path(){

    }

    void findPath(Unit u) {

        float[] vec0 = new float[2];
        float[] vec1 = new float[2];
        float[] vec2 = new float[2];

        vec0[0] = u.getX();
        vec0[1] = u.getY();
        vec1[0] = u.getTarget().getX();
        vec1[1] = u.getTarget().getY();

        for (int i = 0; i < 2; i++) {
            vec2[i] = (vec1[i] - vec0[i])*u.getMovementspeed()/u.getDistTarget();
        }
        u.setDx(vec2[0]);
        u.setDy(vec2[1]);
        System.out.println(u.getDx()+ " "+u.getDy());
    }

}
