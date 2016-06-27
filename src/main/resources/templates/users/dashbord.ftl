<#-- User Dashbord -->
<@override name="meta">
  <meta name="description" content="${userInfo.description}">
  <title>${userInfo.title!} · Hutter</title>
</@override>

<@override name="main">
  <@extends name="/users/snippets/cover.ftl"/>
  <@extends name="/snippets/products.ftl"/>
</@override>

<@extends name="/primary.ftl" />