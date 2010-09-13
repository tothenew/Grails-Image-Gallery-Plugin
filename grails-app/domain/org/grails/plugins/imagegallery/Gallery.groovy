package org.grails.plugins.imagegallery

class Gallery {
    String name
    Set<Image> photos
    static hasMany=[photos:Image]
    static constraints = {
        name(nullable: true)
    }
}
