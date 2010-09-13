package org.grails.plugins.imagegallery

class Gallery {
    String name
    Set<Image> images
    static hasMany=[images:Image]
    static constraints = {
        name(nullable: true)
    }
}
