package com.sirra.demo.model.options;

public class FillOptions {
    FillLateralOptions lateralOption;
    FillVerticalOptions verticalOption;

    public FillOptions() {
        this.lateralOption = FillLateralOptions.Fill_START;
        this.verticalOption = FillVerticalOptions.Fill_BOTTOM;
    }

    public boolean ifIsStartBottom() {
        return ((this.lateralOption == FillLateralOptions.Fill_START) &&
                (this.verticalOption == FillVerticalOptions.Fill_BOTTOM));
    }
}
