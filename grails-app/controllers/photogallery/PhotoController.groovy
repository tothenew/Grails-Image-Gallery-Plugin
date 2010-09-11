package photogallery

class PhotoController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    List<Photo> photos = []
    Integer count
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    if (params?.id) {
      Gallery gallery = Gallery.get(params?.id)
      photos = gallery.photos as List
      count = photos?.size()
    } else {
      photos = Photo.list(params)
      count = Photo.count()
    }
    [photoInstanceList: photos, photoInstanceTotal: count]
  }

  def create = {
    def photoInstance = new Photo()
    photoInstance.properties = params
    return [photoInstance: photoInstance]
  }

  def save = {PhotoCO photoCO ->
    def photoInstance = photoCO?.photo
    photoInstance.loadThumbnail(photoInstance)
    if (!photoInstance?.hasErrors() && photoInstance.save()) {
      flash.message = "${message(code: 'default.created.message', args: [message(code: 'photo.label', default: 'Photo'), photoInstance.id])}"
      redirect(action: "show", id: photoInstance.id)
    } else {
      render(view: "create", model: [photoInstance: photoInstance])
    }
  }

  def show = {
    def photoInstance = Photo.get(params.id)
    if (!photoInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'photo.label', default: 'Photo'), params.id])}"
      redirect(action: "list")
    }
    else {
      [photoInstance: photoInstance]
    }
  }

  def edit = {
    def photoInstance = Photo.get(params.id)
    if (!photoInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'photo.label', default: 'Photo'), params.id])}"
      redirect(action: "list")
    }
    else {
      return [photoInstance: photoInstance]
    }
  }

  def update = {
    def photoInstance = Photo.get(params.id)
    if (photoInstance) {
      if (params.version) {
        def version = params.version.toLong()
        if (photoInstance.version > version) {

          photoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'photo.label', default: 'Photo')] as Object[], "Another user has updated this Photo while you were editing")
          render(view: "edit", model: [photoInstance: photoInstance])
          return
        }
      }
      photoInstance.properties = params
      Photo.loadThumbnail(photoInstance)
      if (!photoInstance.hasErrors() && photoInstance.save(flush: true)) {
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'photo.label', default: 'Photo'), photoInstance.id])}"
        redirect(action: "show", id: photoInstance.id)
      }
      else {
        render(view: "edit", model: [photoInstance: photoInstance])
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'photo.label', default: 'Photo'), params.id])}"
      redirect(action: "list")
    }
  }

  def delete = {
    def photoInstance = Photo.get(params.id)
    if (photoInstance) {
      try {
        photoInstance.delete(flush: true)
        flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'photo.label', default: 'Photo'), params.id])}"
        redirect(action: "list")
      }
      catch (org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'photo.label', default: 'Photo'), params.id])}"
        redirect(action: "show", id: params.id)
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'photo.label', default: 'Photo'), params.id])}"
      redirect(action: "list")
    }
  }

  def showPhoto = {
    Photo photo = Photo?.get(params?.id)
    response.setContentType('image')
    response.outputStream << photo?.image
  }

  def showThumbNail = {
    Photo photo = Photo?.get(params?.id)
    response.setContentType('image')
    response.outputStream << photo?.thumbnail
  }  
}
