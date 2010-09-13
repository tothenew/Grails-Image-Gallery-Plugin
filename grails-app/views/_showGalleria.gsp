<div id="gallery_${gallery.id}">
    <g:each in="${gallery.images}" var="image">
        <ig:img src="${createLink(controller: 'image', action: 'showImage', id: image.id, params: [thumbnail: true,mimeType:'.jpeg'])}"
                thumbnailSrc="${createLink(controller: 'image', action: 'showImage', id: image.id, params: [mimeType:'.jpeg'])}"
                title="${image.caption}" alt="${image.description}"/>
    </g:each>
</div>
<script>
    // Load the theme
    jQuery(function() {

        Galleria.loadTheme('${g.resource(dir: pluginContextPath + '/galleria/themes/'+theme, file: 'galleria.' + theme +'.js')}');
        // Initialize Galleria
        jQuery('#gallery_${gallery.id}').galleria({${options?:''}
            <g:if test="${showInLightBox}">,
                    extend
    :
        function() {
            this.bind(Galleria.IMAGE, function(e) {
                $(e.imageTarget).css('cursor', 'pointer').click(this.proxy(function() {
                    this.openLightbox();
                }));
            })
        }

    </g:if>
    })
    })
    ;

</script>