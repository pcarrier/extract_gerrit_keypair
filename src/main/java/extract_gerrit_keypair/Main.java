package extract_gerrit_keypair;

import org.bouncycastle.openssl.PEMWriter;

import java.io.*;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length != 1) {
            help();
            System.exit(1);
        }

        InputStream inputStream = new FileInputStream(args[0]);
        printPemKeysFromGerrit(inputStream);
    }

    private static void help() {
        System.err.println("Provide provide a filename as an argument.");
        System.err.println("The standard location is etc/ssh_host_key in the Gerrit root.");
    }

    private static void printPemKeysFromGerrit(InputStream source) throws ClassNotFoundException, IOException {
        final ObjectInputStream objectInputStream = new ObjectInputStream(source);
        final KeyPair p = (KeyPair) objectInputStream.readObject();
        final PrivateKey priv = p.getPrivate();
        final PublicKey pub = p.getPublic();
        System.out.println(pemifyKey(priv));
        System.out.println(pemifyKey(pub));
    }

    private static String pemifyKey(Key key) throws IOException {
        final StringWriter stringWriter = new StringWriter();
        PEMWriter pemWriter = new PEMWriter(stringWriter);
        pemWriter.writeObject(key);
        pemWriter.flush();
        return stringWriter.toString();
    }
}
