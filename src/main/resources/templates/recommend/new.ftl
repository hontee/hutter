<@override name="body">
<@extends name="/recommend/snippets/steps.ftl" />

<#if exists??>
<div class="ui error message">
  <p>您推荐的网址已经存在！</p>
</div>
</#if>

<form class="ui form" action="/recommend/confirm" method="get">
  <div class="field">
    <label>链接地址</label>
    <input type="url" name="url" value="${url!}" placeholder="以 http(s):// 开头的有效网址" autocomplete="off">
  </div>
  <button class="ui submit blue button" type="submit">我要推荐</button>
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

<@extends name="/base.ftl" />