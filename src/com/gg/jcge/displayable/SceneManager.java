package com.gg.jcge.displayable;

import com.gg.jcge.game.Displayable;
import com.gg.jcge.game.Manager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class SceneManager implements Manager<Displayable, Scene> {

    private Displayable owner = null;

    private List<Scene> cachedScenes;

    private List<Scene> activeScenes;

    public SceneManager() {
        this.cachedScenes = new LinkedList<>();
        this.activeScenes = new ArrayList<>();
    }

    @Override
    public void load() {
        this.activeScenes.stream().forEach(s -> s.load());
    }

    @Override
    public void render(Graphics g) {
        for (Scene s : activeScenes) {
            s.render(g);
        }

    }

    @Override
    public void update() {
        for (Scene s : activeScenes) {
            s.update();
        }
    }

    @Override
    public void add(Scene... u) {

        activeScenes.addAll(Arrays.asList(u));

    }

    @Override
    public void init(Displayable owner) {
        this.owner = owner;
        this.init();
        this.activeScenes.stream().forEach(s -> s.init(this));
    }

    protected abstract void init();

}
