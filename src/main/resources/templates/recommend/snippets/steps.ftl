<#-- recommend steps -->
<div class="ui three steps">
  <div class="${(step=='1')?string('active', 'disabled')} step">
    <i class="linkify icon"></i>
    <div class="content">
      <div class="title">第一步：</div>
      <div class="description">填写推荐网址</div>
    </div>
  </div>
  <div class="${(step=='2')?string('active', 'disabled')} step">
    <i class="edit icon"></i>
    <div class="content">
      <div class="title">第二步：</div>
      <div class="description">编辑推荐信息</div>
    </div>
  </div>
  <div class="${(step=='3')?string('active', 'disabled')} step">
    <i class="info icon"></i>
    <div class="content">
      <div class="title">第三步：</div>
      <div class="description">确认推荐结果</div>
    </div>
  </div>
</div>