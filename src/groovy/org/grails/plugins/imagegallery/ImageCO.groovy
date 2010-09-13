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
    org.springframework.web.multipart.commons.CommonsMultipartFile image
    String caption

    static constraints = {
        caption(nullable: true)
        image(validator: {
            return it?.size > 0
        })
    }

    org.grails.plugins.imagegallery.Image getPhoto() {
        org.grails.plugins.imagegallery.Image photo = new org.grails.plugins.imagegallery.Image()
        photo?.image = this?.image?.bytes
        photo?.caption = this?.caption
        Image img = new ImageIcon(this?.image?.bytes).getImage();
        photo?.height = img.getHeight(null);
        photo?.width = img.getWidth(null);
        return photo
    }
}
