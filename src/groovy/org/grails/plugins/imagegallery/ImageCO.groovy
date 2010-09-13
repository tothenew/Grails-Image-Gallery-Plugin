package org.grails.plugins.imagegallery

import java.awt.Image
import javax.swing.ImageIcon

/**
 * Created by IntelliJ IDEA.
 * User: uday
 * Date: 9 Sep, 2010
 * Time: 3:09:44 PM
 * To change this template use File | Settings | File Templates.
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
