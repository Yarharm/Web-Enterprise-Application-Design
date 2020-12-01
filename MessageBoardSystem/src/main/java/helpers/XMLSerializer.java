package helpers;

import models.Post;
import models.PostWithAttachment;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class XMLSerializer {
    public static String serialize(Post post) throws JAXBException {
        Class<? extends Post> serialClass = post.isContainsAttachment() ? PostWithAttachment.class : Post.class;
        JAXBContext jc = JAXBContext.newInstance(serialClass);
        StringWriter sw = new StringWriter();
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(post, sw);
        return sw.toString();
    }
}
