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

    Image saveImage(byte[] data, String description, String caption) {
        Image image = new Image(data: data, caption: caption, description: description)
        image.save()
        return image
    }

    Image updateImage(Long id, byte[] data, String caption = "", String description = "") {
        Image image = Image.get(id)
        description = description ?: image?.description
        caption = caption ?: image?.caption
        updateImage(image, data, description, caption)
        return image
    }

    Image updateImage(Image image, byte[] data, String description = "", String caption = "") {
        image.data = data
        image.caption = caption ?: image?.caption
        image.description = description ?: image?.description
        return image
    }

    Gallery saveGallery(String name) {
        Gallery gallery = new Gallery(name: name)
        gallery.save()
        return gallery
    }

    void addImagesToGallery(List<Image> images, Gallery gallery) {
        images.each {Image image ->
            addImageToGallery(image, gallery)
        }
    }

    void addImageToGallery(Image image, Gallery gallery) {
        gallery.addToImages(image)
    }

}
