import java.awt.Image
import javax.swing.ImageIcon
import photogallery.Photo
import photogallery.Gallery
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.codehaus.groovy.grails.web.context.ServletContextHolder as SCH
import photogallery.Test


class BootStrap {

    def init = { servletContext ->
        ConfigurationHolder.config.rover.filePaths.restaurantImages
        String path = SCH.servletContext.getRealPath("/bootstrapData/sample-photo.jpg")

        (1..20).each {
            File file = new File(path)
            Photo photo = new Photo()
            photo?.image = file?.bytes
            photo?.thumbnail = new File(path).readBytes() 
            photo?.caption = "caption$it"
            Image img = new ImageIcon(file?.bytes).getImage();
            photo?.height = img.getHeight(null);
            photo?.width = img.getWidth(null);
            photo.loadThumbnail(photo);
            photo?.save()
        }
        (1..5).each {
            Gallery gallery = new Gallery(name: "Gallery$it")
            gallery.save()
        }

        Gallery.list().each {Gallery gallery ->
            while (gallery.photos?.size() != 5) {
                Integer randomInt = (new Random()).nextInt(21)
                if (Photo.exists(randomInt)) {
                    gallery.addToPhotos(Photo.get(randomInt))
                }
            }
        }

        Test test = new Test()
        Gallery.list().each{
            test.addToGalleries(it)
        }
        test.save()

    }
    def destroy = {
    }
} 