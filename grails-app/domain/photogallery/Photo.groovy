package photogallery

import org.grails.plugins.imagetools.ImageTool

class Photo {
    byte[] thumbnail
    String caption
    byte[] image
    Integer width
    Integer height
    String description
    static mapping = {
    }
    static constraints = {
        thumbnail(nullable: true)
        description(nullable: true)
        caption(nullable: true)
        image(validator: {
            return it?.size() > 0
        })
        width(nullable: false)
        height(nullable: false)
    }

    public static void loadThumbnail(Photo photo) {
        if (photo) {
            def imageTool = new ImageTool()
            imageTool.load(photo.image)
            imageTool.thumbnail(100)
            photo.thumbnail = imageTool.getBytes("JPEG")
        }
    }
}
