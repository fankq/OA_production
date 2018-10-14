package ssm.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializeUtil {
    private static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    public SerializeUtil() {
    }

    public static byte[] serialize(Object object) throws Exception {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] e = baos.toByteArray();
            return e;
        } catch (Exception var4) {
            logger.error("serialize", var4);
            throw new Exception(var4.getMessage());

/*
            throw Exceptions.atomException("SYSTEM", new Object[]{"", var4.getMessage()});
*/
        }
    }

    public static Object unserialize(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = null;

        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream e = new ObjectInputStream(bais);
            return e.readObject();
        } catch (Exception var3) {
            logger.error("unserialize", var3);
            throw new Exception(var3.getMessage());
        }
    }
}