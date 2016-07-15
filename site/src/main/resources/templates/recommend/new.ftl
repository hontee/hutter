<#-- Recommend -->
<@override name="meta">
<title>分享链接 · Hutter</title>
</@override>

<@override name="main">
<section class="ui container">
  <@extends name="/recommend/snippets/steps.ftl" />

  <#if exists??>
  <div class="ui error message">
    <p>分享的链接已经存在！</p>
  </div>
  </#if>

  <div class="ui very padded segment">
    <form class="ui form" action="/recommend/confirm" method="get">
      <div class="field">
        <label>链接地址</label>
        <input type="url" name="url" value="${url!}" placeholder="以 http(s):// 开头的有效网址" autocomplete="off">
      </div>
      <button class="ui submit blue button" type="submit">分享链接</button>
    </form>
  </div>
</section>
</@override>

<@override name="scripts">
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

<@extends name="/primary.ftl" />