package com.yobuligo.timeTracker.Activity;

import com.yobuligo.timeTracker.R;

import java.util.ArrayList;
import java.util.Random;

public class ColorList {

    private ArrayList<Integer> colors = new ArrayList<>();
    private int cursor = -1;

    public ColorList() {
        colors.add(R.color.colorF39A27);
        colors.add(R.color.color03C03C);
        colors.add(R.color.color579ABE);
        colors.add(R.color.color976ED7);
        colors.add(R.color.color6EB5FF);
        colors.add(R.color.colorBFFCC6);
        colors.add(R.color.colorFFF5BA);
        colors.add(R.color.colorFFABAB);
        colors.add(R.color.colorA79AFF);
        colors.add(R.color.colorAFF8DB);
        colors.add(R.color.colorE7FFAC);
        colors.add(R.color.colorFFCBC1);
        colors.add(R.color.colorFFB5E8);
        colors.add(R.color.colorFF9CEE);
        colors.add(R.color.colorFFCCF9);
        colors.add(R.color.colorFCC2FF);
        colors.add(R.color.colorC23B23);
        colors.add(R.color.colorF6A6FF);
        colors.add(R.color.colorB28DFF);
        colors.add(R.color.colorC5A3FF);
        colors.add(R.color.colorD5AAFF);
        colors.add(R.color.colorECD4FF);
        colors.add(R.color.colorFBE4FF);
        colors.add(R.color.colorDCD3FF);
        colors.add(R.color.colorB5B9FF);
        colors.add(R.color.color97A2FF);
        colors.add(R.color.colorAFCBFF);
        colors.add(R.color.colorC4FAF8);
        colors.add(R.color.color85E3FF);
        colors.add(R.color.colorACE7FF);
        colors.add(R.color.colorDBFFD6);
        colors.add(R.color.colorF3FFE3);
        colors.add(R.color.colorFFFFD1);
        colors.add(R.color.colorFFC9DE);
        colors.add(R.color.colorFFBEBC);
    }

    public int getNext() {
        int cursor = new Random().nextInt(colors.size());
        int result = colors.get(cursor);
        colors.remove(cursor);
        return result;
    }

}
