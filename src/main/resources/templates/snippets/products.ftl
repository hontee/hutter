<#-- products block: for product list -->
<#if (records?size>0)>
<div class="ui divided items">
  <#list records as r>
  <div class="item">
    <div class="content">
      <a class="header" href="/${r.id}/hit" target="_blank">${r.title}</a>
      <div class="description">
        <p>${r.description}</p>
      </div>
    </div>
  </div>
  </#list>
</div>
<@extends name="/snippets/pager.ftl"/>
<#else>
没有找到记录
</#if>