package photogallary

class Gallary {
    String name
    Set<Photo> photos
    static hasMany=[photos:Photo]
    static constraints = {
        name(nullable: true)
    }
}
