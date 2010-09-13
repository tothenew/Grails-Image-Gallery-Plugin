import java.awt.Image
import javax.swing.ImageIcon
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.codehaus.groovy.grails.web.context.ServletContextHolder as SCH
import org.grails.plugins.imagegallery.Gallery

class BootStrap {

    def init = { servletContext ->
        ConfigurationHolder.config.rover.filePaths.restaurantImages
        String path = SCH.servletContext.getRealPath("/bootstrapData/sample-photo.jpg")

        (1..20).each {
            File file = new File(path)
            org.grails.plugins.imagegallery.Image photo = new org.grails.plugins.imagegallery.Image()
            photo?.image = file?.bytes
            photo?.caption = "caption$it"
            Image img = new ImageIcon(file?.bytes).getImage();
            photo?.height = img.getHeight(null);
            photo?.width = img.getWidth(null);            
            photo?.save()
        }
        (1..5).each {
            Gallery gallery = new Gallery(name: "Gallery$it")
            gallery.save()
        }

        Gallery.list().each {Gallery gallery ->
            while (gallery.photos?.size() != 5) {
                Integer randomInt = (new Random()).nextInt(21)
                if (org.grails.plugins.imagegallery.Image.exists(randomInt)) {
                    gallery.addToPhotos(org.grails.plugins.imagegallery.Image.get(randomInt))
                }
            }
        }

    }
    def destroy = {
    }
} 