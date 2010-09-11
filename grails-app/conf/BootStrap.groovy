import java.awt.Image
import javax.swing.ImageIcon
import photogallery.Photo
import photogallery.Gallery

class BootStrap {

    def init = { servletContext ->
        (1..20).each {
            File file = new File("/home/chandan/Desktop/pic.jpg")
            Photo photo = new Photo()
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
                if (Photo.exists(randomInt)) {
                    gallery.addToPhotos(Photo.get(randomInt))
                }
            }
        }

    }
    def destroy = {
    }
} 