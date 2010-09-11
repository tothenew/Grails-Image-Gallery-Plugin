package photogallery

class Gallery {
    String name
    Set<Photo> photos
    static hasMany=[photos:Photo]
    static constraints = {
        name(nullable: true)
    }
}
