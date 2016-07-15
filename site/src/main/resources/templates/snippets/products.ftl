<#-- products block: for product list -->
<section class="ui container">
  <#if (records?size>0)>
  <div class="ui segment">
    <div class="ui divided items">
      <#list records as r>
      <div class="item">
        <#-- <a class="ui tiny image">
          <img src="images/image.png">
        </a> -->
        <div class="content">
          <a class="header" href="/${r.id}/hit" target="_blank">${r.title}</a>
          <div class="meta">
            <p>${r.description}</p>
          </div>
          <#-- <div class="extra">
            <button class="ui orange label button"><i class="star icon"></i>102</button>
            <a class="ui right floated label button">开发者工具 <i class="right chevron icon"></i> </a>
          </div> -->
        </div>
      </div>
      </#list>
    </div>
  </div>
  
  <@extends name="/snippets/pager.ftl"/>
  <#else>
  <div class="ui very padded center aligned basic segment">
    <div class="ui huge grey header">No results found.</div>
  </div>
  </#if>
</section>