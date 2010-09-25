package org.grails.plugins.imagegallery

/**
 * Gallery has a name which may consists of multiple images.
 */
class Gallery {
    String name
    Set<Image> images
    static hasMany=[images:Image]
    static constraints = {
        name(nullable: true)
    }
}
