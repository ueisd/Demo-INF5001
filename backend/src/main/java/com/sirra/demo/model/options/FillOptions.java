package com.sirra.demo.model.options;

public class FillOptions {
    FillLateralOptions lateralOption;
    FillVerticalOptions verticalOption;
    int fiilMin;
    int fillMax;

    public FillOptions() {
        this.lateralOption = FillLateralOptions.Fill_START;
        this.verticalOption = FillVerticalOptions.Fill_BOTTOM;
        this.fiilMin = 0;
        this.fillMax = 0;
    }

    public boolean ifIsStartBottom() {
        return ((this.lateralOption == FillLateralOptions.Fill_START) &&
                (this.verticalOption == FillVerticalOptions.Fill_BOTTOM));
    }
}
