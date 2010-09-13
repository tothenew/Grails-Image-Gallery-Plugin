package org.grails.plugins.imagegallery

import javax.swing.ImageIcon

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

    public void loadThumbnail() {
        if (this.data) {
            def imageUtil = new ImageUtil()
            imageUtil.load(data)
            imageUtil.thumbnail(100)
            this.thumbnail = imageUtil.getBytes("JPEG")
        }
    }
}
