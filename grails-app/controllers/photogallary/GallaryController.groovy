package photogallary

class GallaryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gallaryInstanceList: Gallary.list(params), gallaryInstanceTotal: Gallary.count()]
    }

    def create = {
        def gallaryInstance = new Gallary()
        gallaryInstance.properties = params
        return [gallaryInstance: gallaryInstance]
    }

    def save = {
        def gallaryInstance = new Gallary(params)
        if (gallaryInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'gallary.label', default: 'Gallary'), gallaryInstance.id])}"
            redirect(action: "show", id: gallaryInstance.id)
        }
        else {
            render(view: "create", model: [gallaryInstance: gallaryInstance])
        }
    }

    def show = {
        def gallaryInstance = Gallary.get(params.id)
        if (!gallaryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gallary.label', default: 'Gallary'), params.id])}"
            redirect(action: "list")
        }
        else {
            [gallaryInstance: gallaryInstance]
        }
    }

    def edit = {
        def gallaryInstance = Gallary.get(params.id)
        if (!gallaryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gallary.label', default: 'Gallary'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [gallaryInstance: gallaryInstance]
        }
    }

    def update = {
        def gallaryInstance = Gallary.get(params.id)
        if (gallaryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (gallaryInstance.version > version) {

                    gallaryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gallary.label', default: 'Gallary')] as Object[], "Another user has updated this Gallary while you were editing")
                    render(view: "edit", model: [gallaryInstance: gallaryInstance])
                    return
                }
            }
            gallaryInstance.properties = params
            if (!gallaryInstance.hasErrors() && gallaryInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gallary.label', default: 'Gallary'), gallaryInstance.id])}"
                redirect(action: "show", id: gallaryInstance.id)
            }
            else {
                render(view: "edit", model: [gallaryInstance: gallaryInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gallary.label', default: 'Gallary'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def gallaryInstance = Gallary.get(params.id)
        if (gallaryInstance) {
            try {
                gallaryInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gallary.label', default: 'Gallary'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gallary.label', default: 'Gallary'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gallary.label', default: 'Gallary'), params.id])}"
            redirect(action: "list")
        }
    }

    def addPhoto = {
        Gallary gallary = Gallary.get(params?.id)
        List<Photo> photos = Photo.list()
        List<Photo> gallaryPhotos = gallary.photos as List
        [gallary: gallary, photos: photos, gallaryPhotos: gallaryPhotos]
    }

    def savePhoto = {
        Gallary gallary = Gallary.get(params?.id)
        Photo photo
        gallary.photos = []
        if (params.photo) {
            [params.photo].flatten().each {
                photo = Photo.get(it)
                gallary.addToPhotos(photo)
            }
        }
        redirect(action: 'addPhoto', params: [id: params?.id])
    }
}
