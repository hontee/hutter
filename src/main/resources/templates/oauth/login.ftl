<@override name="body">
<div class="ui message hidden"></div>

<form class="ui form" action="javascript:void(0)">
  <div class="field">
    <label>帐号</label>
    <input type="text" name="username" maxLength="20" autocomplete="off" placeholder="用户名 / 邮箱">
  </div>
  <div class="field">
    <label>密码</label>
    <input type="password" name="password" maxLength="20" placeholder="登录密码">
  </div>
  <button class="ui blue button" type="submit">登录</button>
</form>
</@override>

<@override name="script">
<@super/>
<script src="${urls.getForLookupPath('/modules/oauth.js')}"></script>
</@override>

<@extends name="/base.ftl" />