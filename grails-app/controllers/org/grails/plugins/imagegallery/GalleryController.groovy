package org.grails.plugins.imagegallery

class GalleryController {
    def imageGalleryService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [galleryList: Gallery.list(params), galleryTotal: Gallery.count()]
    }

    def create = {
        def gallery = new Gallery()
        gallery.properties = params
        return [gallery: gallery]
    }

    def save = {
        def gallery = new Gallery(params)
        if (gallery.save(flush: true)) {
            flash.message = "Gallery has been created"
            redirect(action: "show", id: gallery.id)
        }
        else {
            render(view: "create", model: [gallery: gallery])
        }
    }

    def show = {
        def gallery = Gallery.get(params.id)
        if (!gallery) {
            flash.message = "Gallery not found"
            redirect(action: "list")
        }
        else {
            [gallery: gallery]
        }
    }

    def edit = {
        def gallery = Gallery.get(params.id)
        if (!gallery) {
            flash.message = "Gallery not found"
            redirect(action: "list")
        }
        else {
            return [gallery: gallery]
        }
    }

    def update = {
        def gallery = Gallery.get(params.id)
        if (gallery) {
            gallery.properties = params
            if (!gallery.hasErrors() && gallery.save(flush: true)) {
                flash.message = "Gallery has been updated"
                redirect(action: "show", id: gallery.id)
            }
            else {
                render(view: "edit", model: [gallery: gallery])
            }
        }
        else {
            flash.message = "Gallery not found"
            redirect(action: "list")
        }
    }

    def delete = {
        def gallery = Gallery.get(params.id)
        if (gallery) {
            try {
                gallery.delete(flush: true)
                flash.message = "Gallery has been deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Gallery can not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "Gallery not found"
            redirect(action: "list")
        }
    }

    def addImage = {
        Gallery gallery = Gallery.get(params?.id)
        List<Image> images = Image.list()
        List<Image> galleryImages = gallery.images as List
        [gallery: gallery, images: images, galleryimages: galleryImages]
    }

    def saveImage = {
        Gallery gallery = Gallery.get(params?.id)
        Image image
        gallery.images = []
        if (params.image) {
            [params.image].flatten().each {
                imageGalleryService.addImageToGallery(Image.get(it), gallery)
            }
        }
        redirect(action: 'list')
    }

}
