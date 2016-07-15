$(document).ready(function() {
  var form = $('.ui.form'),
      button = $('.ui.button'),
      message = $('.ui.message');
  
  // valid form and close keyboardShortcuts.
  form.form({
    keyboardShortcuts: false,
    fields: {
      title: 'empty',
      email: ['empty', 'email'],
    }
  });
  
  // submit form
  form.on('submit', function() {
    if (form.form('is valid')) {
      hutter.loader(button);
      $.post("/api/users/settings", form.serialize()).done(function(data) {
	    if (data.success) {
	      hutter.info(message, data.message);
		} else {
		  hutter.error(message, data.message);
		}
	    hutter.unloader(button);
      });
	}
  });
  
});