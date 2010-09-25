package org.grails.plugins.imagegallery

/**
 * ImageGallery service is used to save/update Images and adding images to the gallery.
 */
class ImageGalleryService {

    static transactional = true

  /**
   * Saves the image
   * @param args includes caption, description and image bytes
   * @return Image
   */
    Image saveImage(Map args) {
        Image image = new Image(args)
        image.save()
        return image
    }

 /**
   * Saves the image
   * @param data
   * @param caption
   * @return Image
   */
    Image saveImage(byte[] data, String caption = "") {
        Image image = new Image(data: data, caption: caption)
        image.save()
        return image
    }

 /**
   * Saves the image
   * @param data
   * @param caption
   * @return Image
   */
    Image saveImage(byte[] data, String description, String caption) {
        Image image = new Image(data: data, caption: caption, description: description)
        image.save()
        return image
    }

   /**
   * Updates the image
   * @param id
   * @param data
   * @param caption
   * @param description
   * @return Image
   */
    Image updateImage(Long id, byte[] data, String caption = "", String description = "") {
        Image image = Image.get(id)
        description = description ?: image?.description
        caption = caption ?: image?.caption
        updateImage(image, data, description, caption)
        return image
    }

   /**
   * Updates the image
   * @param image
   * @param data
   * @param caption
   * @param description
   * @return Image
   */
    Image updateImage(Image image, byte[] data, String description = "", String caption = "") {
        image.data = data
        image.caption = caption ?: image?.caption
        image.description = description ?: image?.description
        return image
    }


   /**
    * Create a gallery with the given name
    * @parm name
    * @return Gallery
     */
    Gallery saveGallery(String name) {
        Gallery gallery = new Gallery(name: name)
        gallery.save()
        return gallery
    }


   /**
    * Add multiple images to the gallery
    * @param list of images
    * @param gallery
     */
    void addImagesToGallery(List<Image> images, Gallery gallery) {
        images.each {Image image ->
            addImageToGallery(image, gallery)
        }
    }

  /**
    * Add image to the gallery
    * @param image
    * @param gallery     
     */
    void addImageToGallery(Image image, Gallery gallery) {
        gallery.addToImages(image)
    }

}
