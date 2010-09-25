package org.grails.plugins.imagegallery

import java.awt.Image
import javax.swing.ImageIcon

/**
 * ImageCO is a command object. 
 */
class ImageCO {
    org.springframework.web.multipart.commons.CommonsMultipartFile data
    String caption

    static constraints = {
        caption(nullable: true)
        data(validator: {
            return it?.size > 0
        })
    }

    org.grails.plugins.imagegallery.Image getImage() {
        org.grails.plugins.imagegallery.Image image = new org.grails.plugins.imagegallery.Image()
        image?.data = this?.data?.bytes
        image?.caption = this?.caption
        return image
    }
}
