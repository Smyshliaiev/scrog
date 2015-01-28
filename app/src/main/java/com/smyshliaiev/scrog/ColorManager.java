package com.smyshliaiev.scrog;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by a.smyshliaie on 28-Jan-15.
 */
public class ColorManager {
    public static final int ALPHA = 150;

    private List<Colors> colorsList = new ArrayList<Colors>();
    private Iterator<Colors> it;
    private Colors colors;

    public ColorManager() {
        init();
    }

    private void init(){
        colorsList.add(new Colors(Color.BLACK, Color.LTGRAY));
        colorsList.add(new Colors(Color.GRAY, Color.WHITE));
        colorsList.add(new Colors(Color.WHITE, Color.BLACK));
        colorsList.add(new Colors(Color.BLACK, Color.GREEN));
        it = colorsList.iterator();
        colors = it.next();
    }

    public void nextColor(){
        if(!it.hasNext()) {it = colorsList.iterator();}
        colors = it.next();
    }

    public Colors getColor(){
        return colors;
    }

}
