/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.displayable.gameobjects;

import com.gg.jcge.displayable.GameObject;


public class EmptyGameObject extends GameObject {

    public EmptyGameObject() {
        super();
        this.disable();
    }

    @Override
    public void load() {
    }

}
