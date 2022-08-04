package com.berkozmen.library_automation_system.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectExtensions {

    public static   <O> String toJson(O object ) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String JsonObject = gson.toJson(object);
        return JsonObject;
    }
}
