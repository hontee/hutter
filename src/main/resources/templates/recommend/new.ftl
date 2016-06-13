<@override name="body">
<div class="ui three steps">
  <div class="active step">
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
  <div class="disabled step">
    <i class="info icon"></i>
    <div class="content">
      <div class="title">第三步：</div>
      <div class="description">推荐结果确认</div>
    </div>
  </div>
</div>

<#if exists??>
<div class="ui error message">
  <p>推荐的产品链接已存在 </p>
</div>
</#if>

<form class="ui form" action="/recommend/confirm" method="get">
  <div class="field">
    <label>产品链接</label>
    <input type="url" name="url" value="${url!}" placeholder="http(s)://" autocomplete="off">
  </div>
  <button class="ui submit blue button" type="submit">提交</button>
</form>
</@override>

<@override name="script">
<@super/>
<script>
$(function() {
  $('.ui.form').form({
    fields: {
      url: 'url'
    }
  });
});
</script>
</@override>

<@extends name="../base.ftl" />