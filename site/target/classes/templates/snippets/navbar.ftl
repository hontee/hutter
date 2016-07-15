<#-- navbar block -->
<@block name="navbar">
<header class="ui top fixed navbar menu">
  <a class="header item" href="/">Hutter</a>
  <div class="item">
    <form class="ui icon input" action="/" method="get">
      <div class="ui icon input">
        <input name="q" value="${q!}" placeholder="搜索" autocomplete="off">
        <i class="search icon"></i>
      </div>
    </form>
  </div>

  <div class="right menu">
    <#if oauth??>
    <a class="item" href="/users/${oauth.name}">我的主页</a>
    <a class="item" href="/recommend">分享链接</a>
    <div id="oauth-dropdown" class="ui right dropdown item">
      <i class="user icon"></i> ${oauth.title!}
      <i class="dropdown icon"></i>
      <div class="menu transition">
        <a class="item" href="/users/${oauth.name}/settings"><i class="setting icon"></i> 设置</a>
        <a class="item" href="/logout"><i class="sign out icon"></i> 退出</a>
      </div>
    </div>
    <#else>
    <a class="item" href="/login"><i class="sign in icon"></i> 登录</a>
    </#if>
  </div>
</header>
</@block>