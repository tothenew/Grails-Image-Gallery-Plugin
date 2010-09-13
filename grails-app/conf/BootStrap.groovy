import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.codehaus.groovy.grails.web.context.ServletContextHolder as SCH
import org.grails.plugins.imagegallery.Gallery

class BootStrap {
    def imageGalleryService

    def init = { servletContext ->
        ConfigurationHolder.config.rover.filePaths.restaurantImages
        String path = SCH.servletContext.getRealPath("/bootstrapData/")
        List<String> imagePaths = ["04092010345.jpg", "04092010346.jpg", "04092010347.jpg", "04092010348.jpg", "04092010362.jpg"]
        (1..20).each {
            File file = new File(path + "/${imagePaths[it % 4]}")
            imageGalleryService.saveImage(file?.bytes, "caption$it", "description$it")
        }
        (1..5).each {
            imageGalleryService.saveGallery("Gallery$it")
        }

        Gallery.list().each {Gallery gallery ->
            while (gallery.images?.size() != 5) {
                Integer randomInt = (new Random()).nextInt(21)
                if (org.grails.plugins.imagegallery.Image.exists(randomInt)) {
                    gallery.addToImages(org.grails.plugins.imagegallery.Image.get(randomInt))
                }
            }
        }

    }
    def destroy = {
    }
} 