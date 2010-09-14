package org.grails.plugins.imagegallery

class ImageGalleryTagLib {

    static namespace = "ig"

    def show = {attrs, body ->
        Boolean showInLightBox = attrs['showInLightBox'] ? true : false
        def options = attrs['options'] ?: 'autoplay: true'
        Gallery gallery = (attrs['galleryInstance'])
        String id = gallery ? "gallery_${gallery.id}" : "imageGallery"
        String renderValue = """${
            g.render(template: "${pluginContextPath}/grails-app/views/showGalleria", model: [theme: attrs['theme'] ?: 'classic',
                    id: id, pluginContextPath: pluginContextPath, options: options ?: '', showInLightBox: showInLightBox])
        }"""
        String imgValues = ''
        if (gallery) {
            gallery.images.each {Image image ->
                String src = g.createLink(controller: 'image', action: 'showImage', id: image.id)
                String thumbnailSrc = g.createLink(controller: 'image', action: 'showImage', id: image.id, params: [thumbnail: true])
                imgValues = imgValues + img([src: src, thumbnailSrc: thumbnailSrc, 'alt': image.description, 'title': image.caption])
            }

        } else {
            imgValues = body()
        }
        renderValue = """<div id="${id}">${imgValues}</div>${renderValue}"""
        out << renderValue
    }

    def img = {attrs ->
        String thumbnailSrc = attrs['thumbnailSrc'] ?: ""
        if (thumbnailSrc) {
            thumbnailSrc = attrs['src']
            attrs['src'] = attrs['thumbnailSrc']
        }
        attrs.remove('thumbnailSrc')
        attrs['src'] = addParameter(attrs['src']?.toString())
        attrs['class'] = attrs['class'] ? "${attrs['class']} imageGallery" : "imageGallery"
        String attributes = "${attrs.collect {k, v -> " $k=\"$v\"" }.join('')}"
        String image = "<img ${attributes} />"
        if (thumbnailSrc) {
            thumbnailSrc = addParameter(thumbnailSrc)
            out << "<a href='${thumbnailSrc}'>${image}</a>"
        } else {
            out << image
        }
    }

    String addParameter(String url) {
        String appender = (url.indexOf("?") == -1) ? "?" : "&"
        url = "${url}${appender}imageGallery=.jpeg"
        return url
    }

    def resources = {
        out << "<script type='text/javascript' src='${g.resource(dir: pluginContextPath + '/galleria', file: 'galleria.js')}' ></script>"

    }
}
