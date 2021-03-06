/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.displayable.scripts.updatescripts;

import com.gg.jcge.components.Position;
import com.gg.jcge.displayable.ScriptManager;
import com.gg.jcge.displayable.scripts.UpdateScript;
import com.gg.jcge.services.ServiceLocator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Adrien
 */
public class PlayerClikScript extends UpdateScript implements MouseListener {

    private Position position;

    @Override
    public void init(ScriptManager script) {
        super.init(script);
        ServiceLocator.getGameWindow().getFrame().addMouseListener(this);
        ServiceLocator.getGameWindow().getCanvas().addMouseListener(this);
    }

    @Override
    public void load() {
        this.position = (Position) scriptManager.getComponent(Position.class);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void execute() {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p1 = e.getPoint();
        e = SwingUtilities.convertMouseEvent((Component) e.getSource(), e, ((Canvas) e.getSource()).getParent());
        int x = (int) p1.getX() + this.scriptManager.getGameObject().getScene().getCamera().getX();
        int y = (int) p1.getY() + this.scriptManager.getGameObject().getScene().getCamera().getY();

        p1.setLocation(new Point(p1.x + this.scriptManager.getGameObject().getScene().getCamera().getX() - position.getWidth() / 2,
                p1.y + this.scriptManager.getGameObject().getScene().getCamera().getY() - position.getHeight()));
        int buttonDown = e.getButton();
        if (buttonDown == MouseEvent.BUTTON3) {

            position.setDestination(p1);

            // a gauche - a gauche en haut - a gauche en bas
            if (e.getX() < position.getX() && e.getY() == position.getY() || e.getX() < position.getX() && e.getY() < position.getY() || e.getX() < position.getX() && e.getY() > position.getY()) {
                position.setOrientation(Position.Orientation.LEFT);
            }

            // a droite - a droite en haut - a droite en bas
            if (e.getX() > position.getX() && e.getY() == position.getX() || e.getX() > position.getX() && e.getY() < position.getY() || e.getX() > position.getX() && e.getY() > position.getY()) {
                position.setOrientation(Position.Orientation.RIGHT);
            }

            //en haut 
            if (e.getX() == position.getX() && e.getY() > position.getY()) {
                position.setOrientation(Position.Orientation.DOWN);
            }

            //en bas
            if (e.getX() == position.getX() && e.getY() < position.getY()) {
                position.setOrientation(Position.Orientation.UP);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
