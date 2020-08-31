/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jenkins.plugins.RadioactiveParameter;

import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.ParameterValue;
import hudson.model.Run;
import hudson.util.VariableResolver;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.export.Exported;

import java.util.Locale;

/**
 *
 * @author Verisky
 */
public class RadioactiveParameterValue extends ParameterValue {

    @Exported(visibility = 4)
    @Restricted(NoExternalUse.class)
    public String value;

    @DataBoundConstructor
    public RadioactiveParameterValue(String name, String selectedOption) {
        this(name, selectedOption, null);
    }

    public RadioactiveParameterValue(String name, String selectedOption, String description) {
        super(name, description);
        this.value = selectedOption;
    }

    @Override
    public String getValue() {
        return value;
    }

    /**
     * Exposes the value as an environment variable.
     */
    @Override
    public void buildEnvironment(Run<?, ?> build, EnvVars env) {
        env.put(name, value);
        env.put(name.toUpperCase(Locale.ENGLISH), value); // backward compatibility pre 1.345
    }

    @Override
    public VariableResolver<String> createVariableResolver(AbstractBuild<?, ?> build) {
        return name -> RadioactiveParameterValue.this.name.equals(name) ? value : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RadioactiveParameterValue that = (RadioactiveParameterValue) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "(RadioactiveParameterValue) " + getName() + "='" + value + "'";
    }

    @Override
    public String getShortDescription() {
        return name + '=' + value;
    }
}
