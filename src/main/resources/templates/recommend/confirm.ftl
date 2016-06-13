<@override name="body">
<div class="ui three steps">
  <div class="disabled step">
    <i class="linkify icon"></i>
    <div class="content">
      <div class="title">第一步：</div>
      <div class="description">填写产品的链接地址</div>
    </div>
  </div>
  <div class="active step">
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

<form class="ui form" action="/recommend/submit" method="get">
  <input type="hidden" name="token" value="${token!}" />
  <div class="field">
    <label>链接</label>
    <input type="url" name="url" value="${spider.url!}" readonly>
  </div>
  <div class="field">
    <label>标题</label>
    <input name="title" value="${spider.title!}" placeholder="标题">
  </div>
  <div class="field">
    <label>描述</label>
    <textarea rows="2" name="description" placeholder="简短的描述">${spider.description!}</textarea>
  </div>
  <button class="ui submit blue button" type="submit">确认提交</button>
</form>
</@override>

<@override name="script">
<@super/>
<script>
$(function() {
  $('.ui.form').form({
    fields: {
      title: 'empty',
      description: 'empty',
    }
  });
});
</script>
</@override>

<@extends name="../base.ftl" />