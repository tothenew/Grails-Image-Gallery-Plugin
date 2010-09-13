package org.grails.plugins.imagegallery

class ImageGalleryService {

    static transactional = true

    Image saveImage(Map args) {
        Image image = new Image(args)
        image.save()
        return image
    }

    Image saveImage(byte[] data, String caption = "") {
        Image image = new Image(data: data, caption: caption)
        image.save()
        return image
    }

    Image saveImage(byte[] data, String caption, String description) {
        Image image = new Image(data: data, caption: caption, description: description)
        image.save()
        return image
    }

    Gallery saveGallery(String name) {
        Gallery gallery = new Gallery(name: name)
        gallery.save()
        return gallery
    }

    void addImagesToGallery() {

    }
}
