package de.beaverstudios.plw.Screens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
public class EndNote extends Actor {

    Integer bgRadius;
    float animationRadius;
    ShapeRenderer shapeRenderer;
    float animationTime;
    Action action;

    public EndNote(ShapeRenderer shapeRenderer,Integer x, Integer y,Integer radius) {
        setX(x);
        setY(y);
        this.shapeRenderer = shapeRenderer;
        setSize(radius,radius);
        bgRadius = radius;
        animationRadius =10;
    }

    public void draw(Batch batch, float parentAlpha){
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 0, 1);
        shapeRenderer.circle(getX(),getY(), bgRadius);
        shapeRenderer.circle(getX(),getY(),animationRadius);
        shapeRenderer.end();
        batch.begin();
    }
}
