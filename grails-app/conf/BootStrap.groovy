import java.awt.Image
import javax.swing.ImageIcon
import photogallary.Photo
import photogallary.Gallary

class BootStrap {

    def init = { servletContext ->
        (1..20).each {
            File file = new File("/home/uday/Pictures/IG/30082010194.jpg")
            Photo photo = new Photo()
            photo?.image = file?.bytes
            photo?.caption = "caption$it"
            Image img = new ImageIcon(file?.bytes).getImage();
            photo?.height = img.getHeight(null);
            photo?.width = img.getWidth(null);
            photo?.save()
        }
        (1..5).each {
            Gallary gallary = new Gallary(name: "Gallary$it")
            gallary.save()
        }

        Gallary.list().each {Gallary gallary ->
            while (gallary.photos?.size() != 5) {
                Integer randomInt = (new Random()).nextInt(21)
                if (Photo.exists(randomInt)) {
                    gallary.addToPhotos(Photo.get(randomInt))
                }
            }
        }

    }
    def destroy = {
    }
} 