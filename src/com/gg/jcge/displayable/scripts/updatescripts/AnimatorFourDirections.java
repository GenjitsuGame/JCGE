/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.displayable.scripts.updatescripts;

import com.gg.jcge.assets.types.ImageAsset;
import com.gg.jcge.components.Position;
import com.gg.jcge.components.Renderer;
import com.gg.jcge.displayable.ScriptManager;
import com.gg.jcge.displayable.scripts.UpdateScript;
import com.gg.jcge.services.ServiceLocator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author scalpa
 */
public class AnimatorFourDirections extends UpdateScript {

    private ArrayList<BufferedImage> animationsLeft;
    private ArrayList<BufferedImage> animationsRight;
    private ArrayList<BufferedImage> animationsUp;
    private ArrayList<BufferedImage> animationsDown;

    private Position currentPosition;
    private Position previousPosition;
    private Renderer renderer;

    @Override
    public void init(ScriptManager scriptManager) {
        super.init(scriptManager);
        this.animationsDown = new ArrayList<>();
        this.animationsLeft = new ArrayList<>();
        this.animationsRight = new ArrayList<>();
        this.animationsUp = new ArrayList<>();
        
        loadAnimations();
    }
    
    @Override
    public void load() {
        this.currentPosition = (Position) this.scriptManager.getComponent(Position.class);
        this.renderer = (Renderer) this.scriptManager.getComponent(Renderer.class);
        this.previousPosition = this.currentPosition.deepClone();
        BufferedImage image = this.animationsDown.get(1);
        this.renderer.setImage(image);
    }
    
    

    public void loadAnimations() {
        try {
        //Ouest
            ImageAsset o1 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "ouest/1O.png");
            animationsLeft.add(ImageIO.read(new ByteArrayInputStream(o1.getPixels())));
            ImageAsset o2 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "ouest/2O.png");
            animationsLeft.add(o2.getBufferedImage());
            ImageAsset o3 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "ouest/3O.png");
            animationsLeft.add(o3.getBufferedImage());
            ImageAsset o4 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "ouest/4O.png");
            animationsLeft.add(o4.getBufferedImage());

            //Est
            ImageAsset e1 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "est/1E.png");
            animationsRight.add(e1.getBufferedImage());
            ImageAsset e2 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "est/2E.png");
            animationsRight.add(e2.getBufferedImage());
            ImageAsset e3 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "est/3E.png");
            animationsRight.add(e3.getBufferedImage());
            ImageAsset e4 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "est/4E.png");
            animationsRight.add(e4.getBufferedImage());

            //Nord
            ImageAsset n1 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "nord/1N.png");
            animationsUp.add(n1.getBufferedImage());
            ImageAsset n2 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "nord/2N.png");
            animationsUp.add(n2.getBufferedImage());
            ImageAsset n3 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "nord/3N.png");
            animationsUp.add(n3.getBufferedImage());
            ImageAsset n4 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "nord/4N.png");
            animationsUp.add(n4.getBufferedImage());

            //Sud
            ImageAsset s1 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "sud/1S.png");
            animationsDown.add(s1.getBufferedImage());
            ImageAsset s2 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "sud/2S.png");
            animationsDown.add(s2.getBufferedImage());
            ImageAsset s3 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "sud/3S.png");
            animationsDown.add(s3.getBufferedImage());
            ImageAsset s4 = (ImageAsset) ServiceLocator.getAssetManager().getAsset("image", "sud/4S.png");
            animationsDown.add(s4.getBufferedImage());
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    @Override
    public void execute() {
        //a changer
        if (this.previousPosition.distanceWith(this.currentPosition) > 3) {
            Position.Orientation orientation = this.currentPosition.getOrientation();
            BufferedImage current = this.renderer.getImage();
            switch (orientation) {
                case DOWN:
                    this.renderer.setImage(getNextImage(current, this.animationsDown));
                    break;
                case UP:
                    this.renderer.setImage(getNextImage(current, this.animationsUp));
                    break;
                case LEFT:
                    this.renderer.setImage(getNextImage(current, this.animationsLeft));
                    break;
                case RIGHT:
                    this.renderer.setImage(getNextImage(current, this.animationsRight));
                    break;
            }
            this.previousPosition = this.currentPosition.deepClone();
        }
    }

    public BufferedImage getNextImage(Image img, ArrayList<BufferedImage> images) {
        BufferedImage image;
        for (int i = 0; i < images.size(); i++) {
            if (img.equals(images.get(i))) {
                image = images.get((i + 1) % images.size());
                return image;
            }

        }
        return images.get(1);
    }
    
}
