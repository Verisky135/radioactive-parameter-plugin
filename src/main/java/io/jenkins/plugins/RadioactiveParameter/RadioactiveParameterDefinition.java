package io.jenkins.plugins.RadioactiveParameter;


import hudson.Extension;
import hudson.model.ParameterDefinition;
import hudson.model.ParameterValue;
import hudson.model.SimpleParameterDefinition;
import java.util.List;
import javax.annotation.Nonnull;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;


/**
 *
 * @author Verisky
 */
public class RadioactiveParameterDefinition extends SimpleParameterDefinition {
    
    private static final RadioactiveParameterConfiguration config = RadioactiveParameterConfiguration.get();
    private static final long serialVersionUID = 1905162041950251407L;
    
    private List<Option> options;
    private String selectedOption;
    private String errorMsg = "";
    private HideMode hideMode;
    
    @DataBoundConstructor
    @SuppressWarnings("unused")
    public RadioactiveParameterDefinition(String name, String description, String selectedOption, List<Option> options) {
        super(name, description);
        this.hideMode = config.getDefaultHideMode();
        this.selectedOption =  selectedOption;
        this.options = options;
    }

    public HideMode getHideMode() {
        return hideMode;
    }
    
    public String getSelectedOption() {
        return selectedOption;
    }
    
    public List<Option> getOptions() {
        return options;
    }
    
    public String getErrorMsg() {
        return errorMsg;
    }
    
    @Override
    public ParameterDefinition copyWithDefaultValue(ParameterValue defaultValue) {
        if (defaultValue instanceof RadioactiveParameterValue) {
            RadioactiveParameterValue value = (RadioactiveParameterValue) defaultValue;
            return new RadioactiveParameterDefinition(getName(), getDescription(),
                value.getValue(), getOptions());
        }
        return this;
    }

    @Override
    public ParameterValue getDefaultParameterValue() {
        String value = "";
        if (! selectedOption.equals("")) {
            value = selectedOption;
        }
        else if (! options.isEmpty()) {
           value = options.get(0).getOptionName();
        } 
        return new RadioactiveParameterValue(getName(), value, getDescription());
    }

    @Override
    public ParameterValue createValue(String value) {
        if (value == null || value.equals("")) {
            if (! selectedOption.equals("")) {
                value = selectedOption;
            }
            else if (! options.isEmpty()) {
               value = options.get(0).getOptionName();
           } 
        }
        return new RadioactiveParameterValue(getName(), value, getDescription());
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jo) {
        return req.bindJSON(RadioactiveParameterValue.class, jo);
    }
    
    @Symbol("radioactiveParameter")
    @Extension
    public static class DescriptorImpl extends ParameterDescriptor {

        @Override
        @Nonnull
        public String getDisplayName() {
            return "Radioactive Parameter";
        }
        
        @SuppressWarnings("unused")
        public HideMode getDefaultHideMode() {
            return config.getDefaultHideMode();
        }
        
    }

}