<script>
    // Load the theme
    jQuery(function() {

        Galleria.loadTheme('${g.resource(dir: pluginContextPath + '/galleria/themes/'+theme, file: 'galleria.' + theme +'.js')}');
        // Initialize Galleria
        jQuery('#${id}').galleria({${options?:''}
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