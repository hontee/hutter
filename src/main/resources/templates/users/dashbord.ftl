<div class="ui vertical masthead center aligned segment" style="padding: 40px 0;">
  <div class="ui text container">
    <h1 class="ui header">${userInfo.title!}</h1>
    <p>${userInfo.description!}</p>
  </div>
</div>

<@override name="body">
<@extends name="/snippets/products.ftl"/>
</@override>

<@extends name="/base.ftl" />