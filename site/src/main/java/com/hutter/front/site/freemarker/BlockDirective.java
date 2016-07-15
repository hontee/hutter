package com.hutter.front.site.freemarker;

import java.io.IOException;
import java.util.Map;

import com.hutter.front.site.freemarker.OverrideDirective.TemplateDirectiveBodyOverrideWraper;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 指定一个代码块并命名<@block name="menu">，
 * 如果被继承，则可能在继承之前使用<@override name="menu">来覆盖初始内容
 * 
 * @author Larry.qi
 */
public class BlockDirective implements TemplateDirectiveModel {

  public final static String DIRECTIVE_NAME = "block";

  @Override
  @SuppressWarnings("rawtypes")
  public void execute(Environment env, Map params, TemplateModel[] loopVars,
      TemplateDirectiveBody body) throws TemplateException, IOException {

    String name = DirectiveUtils.getRequiredParam(params, "name");
    TemplateDirectiveBodyOverrideWraper overrideBody = DirectiveUtils.getOverrideBody(env, name);

    if (overrideBody == null) {
      if (body != null) {
        body.render(env.getOut());
      }
    } else {
      DirectiveUtils.setTopBodyForParentBody(env,
          new TemplateDirectiveBodyOverrideWraper(body, env), overrideBody);
      overrideBody.render(env.getOut());
    }
  }

}
