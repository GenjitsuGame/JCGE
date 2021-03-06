/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.displayable;

import com.gg.jcge.game.Displayable;
import com.gg.jcge.game.Game;
import com.gg.jcge.utils.timer.Timer;

import java.awt.*;

/**
 * La camera représente ce que le joueur peut voir du paysage à travers la
 * fenetre de jeu.
 *
 * @author Pascal Luttgens
 *
 * @version 2.0
 *
 * @since 2.0
 */
public class Camera implements Displayable<Scene> {

    private Scene owner;
    private int x;
    private int y;
    private Timer timer;

    @Override
    public void init(Scene owner) {
        this.owner = owner;
        this.timer = new Timer();
    }

    @Override
    public void load() {
        this.x = 0;
        this.y = 0;
        this.timer.start();
    }

    @Override
    public void update() {
        this.owner.getLandscape().setRenderedArea(new Rectangle(new Point(x, y), new Dimension(Game.WIDTH, Game.HEIGHT)));
    }

    @Override
    public void render(Graphics g) {
        owner.getLandscape().render(g);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Scene getOwner() {
        return owner;
    }

    public void setOwner(Scene owner) {
        this.owner = owner;
    }

}
