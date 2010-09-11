package photogallery

class PhotoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }


    def list = {
        List<Photo> photos = []
        Integer count
        Gallery gallery = new Gallery()
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        if (params?.id) {
            gallery = Gallery.get(params?.id)
            photos = gallery.photos as List
            count = photos?.size()
        } else {
            photos = Photo.list(params)
            count = Photo.count()
        }
        [photos: photos, photoTotal: count, gallery: gallery]
    }
    def create = {
        def photo = new Photo()
        photo.properties = params
        return [photo: photo]
    }

    def save = {PhotoCO photoCO ->
        def photo = photoCO?.photo
        if (!photo?.hasErrors() && photo.save()) {
            flash.message = "Photo has been uploaded"
            redirect(action: "show", id: photo.id)
        } else {
            render(view: "create", model: [photo: photo])
        }
    }

    def show = {
        def photo = Photo.get(params.id)
        if (!photo) {
            flash.message = "Photo not found"
            redirect(action: "list")
        }
        else {
            [photo: photo]
        }
    }

    def edit = {
        def photo = Photo.get(params.id)
        if (!photo) {
            flash.message = "Photo not found"
            redirect(action: "list")
        }
        else {
            return [photo: photo]
        }
    }

    def update = {
        def photo = Photo.get(params.id)
        if (photo) {
            if (params.version) {
                def version = params.version.toLong()
                if (photo.version > version) {

                    photo.errors.rejectValue("version", "Another user has updated this Photo while you were editing", "")
                    render(view: "edit", model: [photo: photo])
                    return
                }
            }
            photo.properties = params
            if (!photo.hasErrors() && photo.save(flush: true)) {
                flash.message = "Photo has been updated"
                redirect(action: "show", id: photo.id)
            }
            else {
                render(view: "edit", model: [photo: photo])
            }
        }
        else {
            flash.message = "Photo not found"
            redirect(action: "list")
        }
    }


    def delete = {
        def photo = Photo.get(params.id)
        if (photo) {
            try {
                photo.delete(flush: true)
                flash.message = "Photo has been deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Photo could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "Photo could not be found"
            redirect(action: "list")
        }
    }

    def showPhoto = {
        Photo photo = Photo?.get(params?.id)
        response.setContentType('image/jpg')
        response.outputStream << (params?.thumbnail ? photo?.thumbnail : photo?.image)
    }
}
