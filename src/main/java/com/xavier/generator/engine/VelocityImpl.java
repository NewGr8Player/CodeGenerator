package com.xavier.generator.engine;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import com.xavier.config.model.TemplateElement;
import com.xavier.util.StringUtil;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class VelocityImpl implements TemplateEngine {

    private static final String ENCODING = "UTF-8";

    private static final VelocityEngine velocityEngine = new VelocityEngine();

    public VelocityImpl(String classPath) {
        final Properties props = new Properties();
        props.setProperty(Velocity.INPUT_ENCODING, ENCODING);
        props.setProperty(Velocity.OUTPUT_ENCODING, ENCODING);
        props.setProperty(Velocity.ENCODING_DEFAULT, ENCODING);
        props.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, NullLogChute.class.getName());
        props.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, classPath + "templates/velocity");
        velocityEngine.init(props);
    }

    @Override
    public String processToString(Map<String, Object> model, String stringTemplate) throws TemplateEngineException {
        try {
            VelocityContext context = new VelocityContext(model);
            StringWriter writer = new StringWriter();
            velocityEngine.evaluate(context, writer, "", stringTemplate);
            return writer.toString();
        } catch (Exception e) {
            throw new TemplateEngineException(e.getMessage(), e);
        }
    }

    @Override
    public void processToFile(Map<String, Object> model, TemplateElement templateElement)
            throws TemplateEngineException {
        try {
            Template template = velocityEngine.getTemplate(templateElement.getTemplateFile(), templateElement.getEncoding());
            VelocityContext context = new VelocityContext(model);

            String targetPath = StringUtil.packagePathToFilePath(processToString(model, templateElement.getTargetPath()));
            String targetFileName = processToString(model, templateElement.getTargetFileName());
            File file = new File(targetPath + File.separator + targetFileName);
            File directory = new File(targetPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),
                    templateElement.getEncoding()));
            template.merge(context, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new TemplateEngineException(e.getMessage(), e);
        }
    }

}
