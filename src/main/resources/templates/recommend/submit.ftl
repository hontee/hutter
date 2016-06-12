<@override name="body">
<h4 class="ui horizontal divider header">推荐成功</h4>

<div class="ui three steps">
  <div class="disabled step">
    <i class="linkify icon"></i>
    <div class="content">
      <div class="title">第一步：</div>
      <div class="description">填写产品的链接地址</div>
    </div>
  </div>
  <div class="disabled step">
    <i class="edit icon"></i>
    <div class="content">
      <div class="title">第二步：</div>
      <div class="description">补全产品标题和描述</div>
    </div>
  </div>
  <div class="active step">
    <i class="info icon"></i>
    <div class="content">
      <div class="title">第三步：</div>
      <div class="description">推荐结果确认</div>
    </div>
  </div>
</div>

<div class="ui divided items">
  <div class="item">
    <div class="content">
      <a class="header" href="/${record.id}/hit" target="_blank">${record.title!}</a>
      <div class="description">
        <p>${record.description!}</p>
      </div>
    </div>
  </div>
</div>

<div class="ui buttons">
  <a class="ui button" href="/">返回首页</a>
  <div class="or" data-text="or"></div>
  <a class="ui blue button active" href="/recommend">继续推荐</a>
</div>
</@override>

<@extends name="../base.ftl" />