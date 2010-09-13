package org.grails.plugins.imagegallery

class ImageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }


    def list = {
        List<Image> images = []
        Integer count
        Gallery gallery = new Gallery()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        if (params?.id) {
            gallery = Gallery.get(params?.id)
            images = gallery.images as List
            count = images?.size()
        } else {
            images = Image.list(params)
            count = Image.count()
        }
        [images: images, imageTotal: count, gallery: gallery]
    }
    def create = {
        def image = new Image()
        image.properties = params
        return [image: image]
    }

    def save = {ImageCO imageCO ->
        def image = imageCO?.image
        if (!image?.hasErrors() && image.save()) {
            flash.message = "Image has been uploaded"
            redirect(action: "show", id: image.id)
        } else {
            render(view: "create", model: [image: image])
        }
    }

    def show = {
        def image = Image.get(params.id)
        if (!image) {
            flash.message = "Image not found"
            redirect(action: "list")
        }
        else {
            [image: image]
        }
    }

    def edit = {
        def image = Image.get(params.id)
        if (!image) {
            flash.message = "Image not found"
            redirect(action: "list")
        }
        else {
            return [image: image]
        }
    }

    def update = {
        def image = Image.get(params.id)
        if (image) {
            if (params.version) {
                def version = params.version.toLong()
                if (image.version > version) {

                    image.errors.rejectValue("version", "Another user has updated this Image while you were editing", "")
                    render(view: "edit", model: [image: image])
                    return
                }
            }
            image.properties = params
            if (!image.hasErrors() && image.save(flush: true)) {
                flash.message = "Image has been updated"
                redirect(action: "show", id: image.id)
            }
            else {
                render(view: "edit", model: [image: image])
            }
        }
        else {
            flash.message = "Image not found"
            redirect(action: "list")
        }
    }


    def delete = {
        def image = Image.get(params.id)
        if (image) {
            try {
                image.delete(flush: true)
                flash.message = "Image has been deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Image could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "Image could not be found"
            redirect(action: "list")
        }
    }

    def showImage = {
        Image image = Image?.get(params?.id)
        response.setContentType('image/jpg')
        response.outputStream << (params?.thumbnail ? image?.thumbnail : image?.data)
    }
}
