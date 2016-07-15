  <#-- Pager Block -->
  <#if (pager?? && pager.total > 1)>
  <div id="pager" class="ui center aligned basic segment">
    <div class="ui compact menu">
      <#if pager.hasPrevious>
      <a class="item" href="${pager.previousUrl}">上一页</a>
      <#else>
      <a class="item disabled">上一页</a>
      </#if>
      <#if pager.hasNext>
      <a class="item" href="${pager.nextUrl}">下一页</a>
      <#else>
      <a class="item disabled">下一页</a>
      </#if>
    </div>
  </div>
  </#if>