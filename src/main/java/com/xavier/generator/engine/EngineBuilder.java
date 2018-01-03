package com.xavier.generator.engine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class EngineBuilder implements Serializable {

    private transient Map<String, TemplateEngine> engineMap;

    public EngineBuilder(String classPath) {
        engineMap = new HashMap<>();
        synchronized (this) {
            engineMap.put("freemarker", new FreeMarkerImpl(classPath));
            engineMap.put("velocity", new VelocityImpl(classPath));
        }
    }

    public TemplateEngine getTemplateEngine(String engine) {
        return engineMap.get(engine);
    }
}
