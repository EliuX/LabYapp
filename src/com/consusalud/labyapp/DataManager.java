/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.consusalud.labyapp;

import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

/**
 *
 * @author EliuX
 */
public class DataManager {
    Hashtable response = new Hashtable();
    JSONParser parser = new JSONParser();
    static Resources res;
    private static final DataManager INSTANCE = new DataManager();
     static public DataManager getInstance() {
        return INSTANCE;
    }

    static public DataManager getInstance(Resources resources) {
        res = resources;
        return INSTANCE;
    }   
    
    public Hashtable getData(String filename) {
        if(response==null || response.isEmpty()){
            try {
                InputStream istream = res.getData(filename);
                InputStreamReader reader = new InputStreamReader(istream);
                response = parser.parse(reader);
            } catch (IOException ex) {
                Log.e(ex);
            } 
        }
        return response;
    }
    
}
