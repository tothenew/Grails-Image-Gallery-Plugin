package org.grails.plugins.imagegallery

import javax.swing.ImageIcon
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

/**
 * Image holds the image bytes, its thumbnail and other properties related to an Image.
 * Image data field is required. Height, Width and thumbnail are created updating or creating an image.    
 */
class Image {
    byte[] thumbnail
    String caption
    String description
    byte[] data
    Integer width
    Integer height

    static mapping = {
    }
    static constraints = {
        description(nullable: true)
        thumbnail(nullable: true)
        caption(nullable: true)
        data(validator: {
            return it?.size() > 0
        })
        width(nullable: true)
        height(nullable: true)
    }

    def beforeInsert = {
        java.awt.Image img = new ImageIcon(this?.data).getImage();
        this?.height = img.getHeight(null);
        this?.width = img.getWidth(null);
        loadThumbnail()
    }
    def beforeUpdate = {
        loadThumbnail()
    }



  /**
    * Creates the thumbnail. Takes maxWidth and maxHeight from config.groovy if provided
    *  otherwise cosiders the default max width & height as 170 & 120 respectively.
    */
    public void loadThumbnail() {
        if (this.data) {
            def imageUtil = new ImageUtil()
            imageUtil.load(data)
            float maxWidth = CH?.config?.grails?.imageGallery?.thumbnail?.maxWidth?:170
            float maxHeight = CH?.config?.grails?.imageGallery?.thumbnail?.maxWidth?:120
            imageUtil.thumbnailSpecial(maxWidth, maxHeight, 1,1)
            this.thumbnail = imageUtil.getBytes("JPEG")
        }
    }
}
