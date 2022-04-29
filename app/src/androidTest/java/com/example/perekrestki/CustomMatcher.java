package com.example.perekrestki;

import android.graphics.Color;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CustomMatcher {
    public static TypeSafeMatcher<View> withNotBgColor(final int color) {
        return new TypeSafeMatcher<View>() {
            @Override public boolean matchesSafely (final View view) {
                return color != view.getSolidColor();
            }
            @Override public void describeTo (final Description description) {
                description.appendText ("View's background should be " + color);
            }
        };
    }
}
