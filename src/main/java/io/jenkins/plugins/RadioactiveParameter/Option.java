/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jenkins.plugins.RadioactiveParameter;

import hudson.Extension;
import hudson.model.Describable;
import hudson.model.Descriptor;
import java.io.Serializable;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 *
 * @author Verisky
 */
public class Option implements Describable<Option>, Serializable {
    private String optionName;
    private String enabledParameters;
    
    @DataBoundConstructor 
    public Option(String optionName, String enabledParameters) {
        this.optionName = optionName;
        this.enabledParameters = enabledParameters;
    }
    
    public String getOptionName() {
        return optionName;
    }
    
    public String getEnabledParameters() {
        return enabledParameters;
    }
    
    @Extension
    public static final DescriptorImpl DESCRIPOR = new DescriptorImpl();

    @Override
    public Descriptor<Option> getDescriptor() {
        return DESCRIPOR;
    }
    
    @Extension
    public static class DescriptorImpl extends Descriptor<Option> {
        public String getDisplayName() { return "An Option"; }
    }
}
