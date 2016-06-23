<@override name="body">
<div class="ui message hidden"></div>

<form class="ui form" action="javascript:void(0)">
  <div class="field">
    <label>昵称</label>
    <input type="text" name="title" value="${oauth.title!}" autocomplete="off" placeholder="昵称">
  </div>
  <div class="field">
    <label>邮箱</label>
    <input type="email" name="email" value="${oauth.email!}" autocomplete="off" placeholder="邮箱地址">
  </div>
  <div class="field">
    <label>介绍自己</label>
    <textarea rows="2" name="description" placeholder="简短的描述">${oauth.description!}</textarea>
  </div>
  <button class="ui blue button" type="submit">保存设置</button>
</form>
</@override>

<@override name="script">
<@super/>
<script src="${urls.getForLookupPath('/modules/settings.js')}"></script>
</@override>

<@extends name="/base.ftl" />