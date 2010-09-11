package photogallery

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
        thumbnail(nullable: true)
        caption(nullable: true)
        image(validator: {
            return it?.size() > 0
        })
        description(nullable:true)
        width(nullable: false)
        height(nullable: false)
    }
}
