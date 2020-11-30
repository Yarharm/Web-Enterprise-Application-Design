package helpers;

import models.Post;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class XMLSerializer {
    public static String serialize(Post post) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Post.class);
        StringWriter sw = new StringWriter();
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(post, sw);
        return sw.toString();
    }
}
