package ca.cognizantgames.assets;

import java.awt.*;

public class Button {
    private int width, height, locationX, locationY;

    public Button(int width, int height, int locationX, int locationY) {
        this.width = width;
        this.height = height;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public boolean isMouseOver(float mouseX, float rawMouseY, float screenHeight) {
        float mouseY = screenHeight - rawMouseY;
        if (mouseX >= locationX) {
            if (mouseX <= width + locationX) {
                if (mouseY >= locationY) {
                    if (mouseY <= height + locationY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setLocation(int x, int y) {
        this.locationX = x;
        this.locationY = y;
    }

    public Rectangle drawButton() {
        return new Rectangle(locationX, locationY, width, height);
    }
}
