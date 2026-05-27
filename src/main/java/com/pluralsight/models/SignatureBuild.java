package com.pluralsight.models;
import com.pluralsight.models.components.Component;
import com.pluralsight.models.enums.FormFactor;
import com.pluralsight.models.enums.PCType;
import java.util.List;

public class SignatureBuild extends PCBuild {

    // variables
    private final String signatureName;
    private final String description;

    // constructor
    public SignatureBuild(String signatureName, String description,
                          PCType size, FormFactor formFactor,
                          List<Component> preloadedComponents, boolean rgb) {
        super(size, formFactor);
        this.signatureName = signatureName;
        this.description = description;
        preloadedComponents.forEach(this::addComponent);
        this.setRGBLighting(rgb);
    }

    public String getSignatureName() {
        return signatureName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return "\"" + signatureName + "\" Signature Build (" + getSize().getDisplayName() + ")";
    }

    @Override
    public String getOrderSummary() {
        return "  [SIGNATURE] " + signatureName + "\n"
                + "    (" + description + ")\n"
                + super.getOrderSummary().replaceFirst("  PC Build: .+\n", "");
    }
}