<!DOCTYPE html>
<html class="no-js" lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="renderer" content="webkit">
  <meta name="keywords" content="${keywords!"Hutter,开发者,开发者服务,开发文档,开发工具,开源项目,SDK,API,数据平台"}">
  <meta name="description" content="${description!"Hutter是一个基于学习和分享的开发者社区。我们以汇聚国内外最优质资源，打造最有影响力的开发者平台为目标，专注为开发者服务。"}">
  <title>${title!"Hutter"}</title>
  <link rel="apple-touch-icon" href="/apple-touch-icon.png">
  <link rel="shortcut icon" href="/favicon.ico">
  <#-- resources block -->
  <@block name="resources">
  <link href="${urls.getForLookupPath('/dist/semantic.css')}" rel="stylesheet">
  </@block>
</head>

<body>
<#-- navbar block -->
<@block name="navbar">
<header class="ui top fixed menu" style="background:#f5f5f5;box-shadow:none;">
  <a class="header item" href="/">Hutter</a>
  <div class="item">
    <form class="ui icon input" action="/" method="get">
      <input name="q" value="${q!}" placeholder="搜索" autocomplete="off">
      <i class="search icon"></i>
    </form>
  </div>
  <a class="right item" href="/recommend"><i class="add icon"></i> 开发者推荐</a>
</header>
</@block>

<div class="ui container" style="width: 800px; padding: 70px 0;">
  <#-- body block (main container) -->
  <@block name="body"></@block>
</div>

<#-- script block -->
<@block name="script">
<script src="${urls.getForLookupPath('/js/modernizr.js')}"></script>
<script src="${urls.getForLookupPath('/js/jquery.js')}"></script>
<script src="${urls.getForLookupPath('/dist/semantic.js')}"></script>
</@block>
</body>
</html>