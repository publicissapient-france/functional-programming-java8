jQuery(document).ready(function() {
  var helpBlock = jQuery(".help");

  helpBlock.wrap('<div></div>');
  helpBlock.append('<Strong>Si vous êtes toujours bloqué, vous pouvez allez regarder sur la branche solution:</strong> <br/> <code>git checkout step*-solution</code>');
  helpBlock.before('<button  type="button" class = "teaser">Aide</button>');
  helpBlock.hide();
  jQuery(document).on('click', '.teaser', function() {
    jQuery(this).next(".help").slideToggle(500);
  });
});
