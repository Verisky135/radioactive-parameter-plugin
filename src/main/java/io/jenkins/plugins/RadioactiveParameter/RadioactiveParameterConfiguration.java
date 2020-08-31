/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jenkins.plugins.RadioactiveParameter;


import hudson.Extension;
import jenkins.model.GlobalConfiguration;

/**
 *
 * @author Verisky
 */
@Extension
public class RadioactiveParameterConfiguration extends GlobalConfiguration {
    private HideMode defaultHideMode;

    public static RadioactiveParameterConfiguration get() {
        return GlobalConfiguration.all().get(RadioactiveParameterConfiguration.class);
    }
    
    public RadioactiveParameterConfiguration() {
        
    }
    
    public HideMode getDefaultHideMode() {
        return defaultHideMode != null ? defaultHideMode : HideMode.HIDE;
    }
    
}

