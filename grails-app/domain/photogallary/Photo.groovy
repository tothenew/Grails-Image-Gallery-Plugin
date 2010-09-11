package photogallary

class Photo {
    byte[] thumbnail
    String caption
    byte[] image
    Integer width
    Integer height
    static mapping = {
        image column: "image", sqlType: "longblob"

    }
    static constraints = {
        thumbnail(nullable: true)
        caption(nullable: true)
        image(validator: {
            return it?.size() > 0
        })
        width(nullable: false)
        height(nullable: false)
    }
}
