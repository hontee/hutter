<!DOCTYPE html>
<html class="no-js" lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="renderer" content="webkit">
  <@block name="meta">
  <meta name="keywords" content="Hutter,开发者,开发者服务,开发文档,开发工具,开源项目,SDK,API,数据平台">
  <meta name="description" content="Hutter是一个基于学习和分享的开发者社区。我们以汇聚国内外最优质资源，打造最有影响力的开发者平台为目标，专注为开发者服务。">
  <title>一个基于学习和分享的开发者社区 · Hutter</title>
  </@block>
  <link rel="apple-touch-icon" href="/apple-touch-icon.png">
  <link rel="shortcut icon" href="/favicon.ico">
  <#if oauth??>
  <meta property="hutter:id" content="${oauth.id}">
  <meta property="hutter:user" content="${oauth.name}">
  </#if>
  <#-- styles block -->
  <@block name="styles">
  <link href="${urls.getForLookupPath('/dist/semantic.css')}" rel="stylesheet">
  </@block>
</head>
<@block name="body"></@block>
</html>