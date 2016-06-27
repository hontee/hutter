<#-- Login -->
<@override name="meta">
<title>登录 · Hutter</title>
</@override>

<@override name="navbar" />
<@override name="main">
  <section class="ui medium container">
    <div class="ui center aligned basic segment">
      <h2 class="ui center aligned icon header">
        <i class="circular users icon"></i> 欢迎登录
      </h2>
    </div>

    <div class="ui message hidden"></div>
    <div class="ui padded segment">
      <form class="ui form" action="javascript:void(0)">
        <div class="field">
          <label for="username">帐号</label>
          <div class="ui left icon input">
            <input type="text" id="username" name="username" maxLength="20" autocomplete="off" placeholder="用户名 / 邮箱">
            <i class="user icon"></i>
          </div>
        </div>
        <div class="field">
          <label for="password">密码</label>
          <div class="ui left icon input">
            <input type="password" id="password" name="password" maxLength="20" placeholder="登录密码">
            <i class="lock icon"></i>
          </div>
        </div>
        <button class="fluid ui blue button" type="submit">登录</button>
      </form>
    </div>
    <#-- <div class="ui horizontal divider">第三方登录</div>
    <div class="ui center aligned basic segment">
      <button class="ui circular twitter icon button">
        <i class="qq icon"></i>
      </button>
      <button class="ui circular youtube icon button">
        <i class="weibo icon"></i>
      </button>
    </div> -->
  </section>
</@override>

<@override name="scripts">
<@super/>
<script src="${urls.getForLookupPath('/modules/oauth.js')}"></script>
</@override>

<@extends name="/secondary.ftl" />