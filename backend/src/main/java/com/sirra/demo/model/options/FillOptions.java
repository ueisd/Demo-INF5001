package com.sirra.demo.model.options;

public class FillOptions {
    FillLateralOptions lateralOption;
    FillVerticalOptions verticalOption;
    int fiilMinOnVoid;
    int fillMax;

    public FillOptions() {
        this.lateralOption = FillLateralOptions.Fill_START;
        this.verticalOption = FillVerticalOptions.Fill_BOTTOM;
        this.fiilMinOnVoid = 0;
        this.fillMax = 0;
    }

    public boolean ifIsStartBottom() {
        return ((this.lateralOption == FillLateralOptions.Fill_START) &&
                (this.verticalOption == FillVerticalOptions.Fill_BOTTOM));
    }

    public boolean isStart() {
        return this.lateralOption == FillLateralOptions.Fill_START;
    }

    public int getFillMax() {
        return fillMax;
    }

    public void setFillMax(int fillMax) {
        this.fillMax = fillMax;
    }

    public int getFiilMinOnVoid() {
        return fiilMinOnVoid;
    }

    public void setVerticalOption(FillVerticalOptions verticalOption) {
        this.verticalOption = verticalOption;
    }

    public void setFiilMinOnVoid(int fiilMinOnVoid) {
        this.fiilMinOnVoid = fiilMinOnVoid;
    }

    public FillLateralOptions getLateralOption() {
        return lateralOption;
    }

    public FillVerticalOptions getVerticalOption() {
        return verticalOption;
    }
}
