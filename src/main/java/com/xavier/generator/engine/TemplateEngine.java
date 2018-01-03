package com.xavier.generator.engine;

import com.xavier.config.model.TemplateElement;

import java.util.Map;


public interface TemplateEngine {

    String processToString(Map<String, Object> model, String stringTemplate) throws TemplateEngineException;

    void processToFile(Map<String, Object> model, TemplateElement templateElement) throws TemplateEngineException;
}
