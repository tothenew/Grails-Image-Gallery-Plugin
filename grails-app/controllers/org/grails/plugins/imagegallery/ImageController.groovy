package org.grails.plugins.imagegallery

class ImageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }


    def list = {
        List<Image> photos = []
        Integer count
        Gallery gallery = new Gallery()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        if (params?.id) {
            gallery = Gallery.get(params?.id)
            photos = gallery.photos as List
            count = photos?.size()
        } else {
            photos = Image.list(params)
            count = Image.count()
        }
        [photos: photos, photoTotal: count, gallery: gallery]
    }
    def create = {
        def photo = new Image()
        photo.properties = params
        return [photo: photo]
    }

    def save = {ImageCO photoCO ->
        def photo = photoCO?.photo
        if (!photo?.hasErrors() && photo.save()) {
            flash.message = "Image has been uploaded"
            redirect(action: "show", id: photo.id)
        } else {
            render(view: "create", model: [photo: photo])
        }
    }

    def show = {
        def photo = Image.get(params.id)
        if (!photo) {
            flash.message = "Image not found"
            redirect(action: "list")
        }
        else {
            [photo: photo]
        }
    }

    def edit = {
        def photo = Image.get(params.id)
        if (!photo) {
            flash.message = "Image not found"
            redirect(action: "list")
        }
        else {
            return [photo: photo]
        }
    }

    def update = {
        def photo = Image.get(params.id)
        if (photo) {
            if (params.version) {
                def version = params.version.toLong()
                if (photo.version > version) {

                    photo.errors.rejectValue("version", "Another user has updated this Image while you were editing", "")
                    render(view: "edit", model: [photo: photo])
                    return
                }
            }
            photo.properties = params
            if (!photo.hasErrors() && photo.save(flush: true)) {
                flash.message = "Image has been updated"
                redirect(action: "show", id: photo.id)
            }
            else {
                render(view: "edit", model: [photo: photo])
            }
        }
        else {
            flash.message = "Image not found"
            redirect(action: "list")
        }
    }


    def delete = {
        def photo = Image.get(params.id)
        if (photo) {
            try {
                photo.delete(flush: true)
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

    def showPhoto = {
        Image photo = Image?.get(params?.id)
        response.setContentType('image/jpg')
        response.outputStream << (params?.thumbnail ? photo?.thumbnail : photo?.image)
    }
}
