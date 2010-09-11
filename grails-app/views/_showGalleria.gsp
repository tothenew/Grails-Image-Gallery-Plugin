<div id="gallery_${gallery.id}">
    <g:each in="${gallery.photos}" var="photo">
        <div>
        <g:link controller="photo" action="showPhoto" id="${photo.id}" params="[mimeType:'.jpeg']">
            <img src="${createLink(controller: 'photo', action: 'showPhoto', id: photo.id, params: [thumbnail: true,mimeType:'.jpeg'])}" title="${photo.caption}" alt="${photo.description}">
        </g:link>
        </div>
    </g:each>
</div>
<script>
    // Load the theme
    jQuery(function(){

    Galleria.loadTheme('${g.resource(dir: pluginContextPath + '/galleria/themes/'+theme, file: 'galleria.' + theme +'.js')}');
    // Initialize Galleria
    jQuery('#gallery_${gallery.id}').galleria({${options?:''}
        <g:if test="${showInLightBox}">,
                extend: function() {
                    this.bind(Galleria.IMAGE, function(e) {
                    $(e.imageTarget).css('cursor','pointer').click(this.proxy(function() {
                        this.openLightbox();
                    }));
                })
                }
        </g:if>
    })
    });

</script>