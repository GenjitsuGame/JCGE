package com.gg.jcge.displayable;

import com.gg.jcge.game.Displayable;
import com.gg.jcge.utils.events.Notifier;

import java.awt.*;

/**
 * Superclass représentant tous les objets du jeu.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public abstract class GameObject implements Displayable<Scene> {

    protected final Notifier notifier;

    /**
     * Encapsulation de l'ensemble des components du Game Object
     *
     * - Pascal Luttgens.
     */
    protected final ComponentManager componentManager;
    protected final ScriptManager scriptManager;

    protected String id;
    protected Scene scene;
    /**
     * Flag indiquant si le Game Object doit call update et render. Utile pour
     * le pattern object pool qui consiste a avoir une liste d'objets
     * réutilisables afin d'éviter les opérations d'allocation de mémoire.
     *
     * - Pascal Luttgens.
     */
    private boolean isActive;

    public GameObject() {
        this.componentManager = new ComponentManager();
        this.scriptManager = new ScriptManager();
        this.isActive = true;
        this.id = this.getClass().getSimpleName().toUpperCase();
        this.notifier = new Notifier(this);
    }

    public GameObject(String id) {
        this.componentManager = new ComponentManager();
        this.scriptManager = new ScriptManager();
        this.isActive = true;
        this.id = id.toUpperCase();
        this.notifier = new Notifier(this);
    }

    /**
     * Doit être overridée pour spécifier la liste des components à charger dans
     * le component manager. Attention : Les components doivent se trouver dans
     * le package components. Pour éviter les pertes de performances, il faut
     * ordonner les components en fonction des call de la fonction update. Un
     * contrôle est quand même effectué lors de l'initialisation du CM.
     *
     * - Pascal Luttgens.
     */
    /**
     * Recupere l'id du GameObject
     *
     * @return
     */
    public String getId() {
        return this.id;
    }

    /**
     * Recupere la scene
     *
     * @return
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Initialise la scene
     *
     * @param scene
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Recupere le ComponentManager encapsulant le comportement des components
     * au sein d'un GameObject
     *
     * @return
     */
    public ComponentManager getComponentManager() {
        return this.componentManager;
    }

    @Override
    public void init(Scene scene) {
        this.scene = scene;
        this.componentManager.init(this);
        this.scriptManager.init(this);
    }

    @Override
    public void load() {
        this.componentManager.load();
        this.scriptManager.load();
    }

    /**
     * Vérifie que le GameObject est actif avant de procéder à l'update.
     *
     * - Pascal Luttgens.
     */
    @Override
    public final void update() {
        if (this.isActive) {
            updateObject();
        }
    }

    /**
     * Méthode à Override pour définir le comportement du Game Object lors d'un
     * call de son cycle d'update.
     *
     * - Pascal Luttgens.
     */
    protected void updateObject() {
        this.componentManager.update();
        this.scriptManager.update();
    }

    /**
     * Vérifie que le Game Object est actif avant de procéder au rendering.
     *
     * - Pascal Luttgens.
     *
     * @param g
     */
    @Override
    public final void render(Graphics g) {
        if (this.isActive) {
            renderObject(g);
        }
    }

    /**
     * Méthode à Override pour définir le comportement du Game Object lors d'un
     * call de son rendering.
     *
     * - Pascal Luttgens.
     *
     * @param g
     */
    protected void renderObject(Graphics g) {
        componentManager.render(g);
    }

    /**
     * Active l'update et le rendering du Game Object.
     *
     * - Pascal Luttgens.
     */
    public void active() {
        this.isActive = true;
    }

    /**
     * Désactive l'update et le rendering du Game Object.
     *
     * - Pascal Luttgens.
     */
    public void disable() {
        this.isActive = false;
    }

    public boolean isInCameraField() {
        return true;
    }


    public ScriptManager getScriptManager() {
        return this.scriptManager;
    }

}
