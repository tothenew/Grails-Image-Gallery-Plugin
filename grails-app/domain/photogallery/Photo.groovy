package photogallery

import photogallery.utility.PhotoTool

class Photo {
    byte[] thumbnail
    String caption
    String description
    byte[] image
    Integer width
    Integer height

    static mapping = {
    }
    static constraints = {
        description(nullable: true)
        thumbnail(nullable: true)
        caption(nullable: true)
        image(validator: {
            return it?.size() > 0
        })
        width(nullable: false)
        height(nullable: false)
    }

    def beforeInsert = {
        loadThumbnail()
    }
    def beforeUpdate = {
        loadThumbnail()
    }

    public void loadThumbnail() {
        if (this.image) {
            def photoTool = new PhotoTool()
            photoTool.load(image)
            photoTool.thumbnail(100)
            this.thumbnail = photoTool.getBytes("JPEG")
        }
    }
}
