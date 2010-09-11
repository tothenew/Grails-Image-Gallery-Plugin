<div id="gallery_${gallery.id}">
    <g:each in="${gallery.photos}" var="photo">
        <g:link controller="photo" action="showPhoto" id="${photo.id}" params="[mimeType:'.jpeg']">
            <img src="${createLink(controller: 'photo', action: 'showPhoto', id: photo.id, params: [thumbnail: true,mimeType:'.jpeg'])}" title="${photo.caption}" alt="${photo.description}">
        </g:link>
    </g:each>
</div>
<script>
    // Load the theme
    Galleria.loadTheme('${g.resource(dir: pluginContextPath + '/galleria/themes/'+theme, file: 'galleria.' + theme +'.js')}');
    // Initialize Galleria
    jQuery('#gallery_${gallery.id}').galleria({${options?:''}});

</script>