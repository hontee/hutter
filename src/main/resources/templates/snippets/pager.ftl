<#if pager??>
<div id="pager">
  <#if pager.hasPrevious>
  <a class="ui left floated button" href="${pager.previousUrl}">上一页</a>
  </#if>
  <#if pager.hasNext>
  <a class="ui right floated button" href="${pager.nextUrl}">下一页</a>
  </#if>
</div>
</#if>