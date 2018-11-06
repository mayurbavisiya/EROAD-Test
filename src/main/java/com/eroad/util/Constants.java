/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eroad.util;

import java.util.ResourceBundle;

/**
 *
 * @author Mayur
 */
public class Constants {

    static ResourceBundle mybundle = ResourceBundle.getBundle("googleapi");

    /**
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        try {
            return mybundle.getString(key);
        } catch (Exception e) {
            return "";
        }
    }

}
