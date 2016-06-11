package com.hutter.master.mvc.freemarker;

import java.io.IOException;
import java.util.Map;

import freemarker.cache.TemplateCache;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 继承模板
 * @author Larry.qi
 */
public class ExtendsDirective implements TemplateDirectiveModel {

  public final static String DIRECTIVE_NAME = "extends";

  @SuppressWarnings({"rawtypes", "deprecation"})
  public void execute(Environment env, Map params, TemplateModel[] loopVars,
      TemplateDirectiveBody body) throws TemplateException, IOException {

    String name = DirectiveUtils.getRequiredParam(params, "name");
    String encoding = DirectiveUtils.getParam(params, "encoding", null);
    String includeTemplateName = TemplateCache.getFullTemplatePath(env, getTemplatePath(env), name);
    env.include(includeTemplateName, encoding, true);
  }

  @SuppressWarnings("deprecation")
  private String getTemplatePath(Environment env) {
    String templateName = env.getTemplate().getName();
    return templateName.lastIndexOf('/') == -1
        ? ""
        : templateName.substring(0, templateName.lastIndexOf('/') + 1);
  }

}
