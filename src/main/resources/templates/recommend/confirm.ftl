<@override name="main">
<section class="ui container">
  <@extends name="/recommend/snippets/steps.ftl" />

  <div class="ui very padded segment">
    <form class="ui form" action="/recommend/submit" method="get">
      <input type="hidden" name="token" value="${token!}" />
      <#-- <div class="field">
        <label>主题</label>
        <select class="ui search dropdown">
          <option value="">选择一个主题</option>
          <option value="AF">Afghanistan</option>
          <option value="AX">Åland Islands</option>
          <option value="AL">Albania</option>
          <option value="DZ">Algeria</option>
        </select>
      </div> -->
      <div class="field">
        <label>链接</label>
        <input type="url" name="url" value="${spider.url!}" readonly>
      </div>
      <div class="field">
        <label>标题</label>
        <input type="text" name="title" value="${spider.title!}" placeholder="标题">
      </div>
      <div class="field">
        <label>描述</label>
        <textarea rows="2" name="description" placeholder="简短的描述">${spider.description!}</textarea>
      </div>
      <button class="ui blue button" type="submit">确认</button>
    </form>
  </div>
</section>
</@override>

<@override name="scripts">
<@super/>
<script>
$(function() {
  $('select.dropdown').dropdown();
  $('.ui.form').form({
    fields: {
      title: 'empty',
      description: 'empty',
    }
  });
});
</script>
</@override>
<@extends name="/primary.ftl" />