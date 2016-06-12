<@override name="body">
<h4 class="ui horizontal divider header">
  <#if q??>
  搜索结果
  <#else>
  产品列表
  </#if>
</h4>

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
<#else>
没有找到记录
<@override name="pager" />
</#if>
</@override>

<@extends name="../base.ftl" />