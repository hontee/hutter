<@override name="main">
<section class="ui container">
  <@extends name="/recommend/snippets/steps.ftl" />

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
    <a class="ui blue button active" href="/recommend">继续分享</a>
  </div>
</section>
</@override>

<@extends name="/primary.ftl" />