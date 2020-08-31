/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jenkins.plugins.RadioactiveParameter;

/**
 *
 * @author Verisky
 */
public enum HideMode {
    HIDE("Hide elements"),
    DISABLE("Show elements but disabled");

    public final String value;

    HideMode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
