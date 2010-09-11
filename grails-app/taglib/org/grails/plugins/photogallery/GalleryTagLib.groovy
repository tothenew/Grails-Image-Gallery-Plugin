package org.grails.plugins.photogallery

import photogallery.Gallery

class GalleryTagLib {

    static namespace = "gallery"

    def show = {attrs ->
        Gallery gallery = (attrs['galleryInstance'])
        Boolean showInLightBox = attrs['showInLightBox'] ? true : false
        def options = attrs['options'] ?: 'autoplay: true'
        out << g.render(template: '/showGalleria', model: [theme: attrs['theme'] ?: 'classic', gallery: gallery, pluginContextPath: pluginContextPath, options: options ?: '', showInLightBox: showInLightBox])
    }


    def resources = {
        out << "<script type='text/javascript' src='${g.resource(dir: pluginContextPath + '/galleria', file: 'galleria.js')}' ></script>"

    }
}
