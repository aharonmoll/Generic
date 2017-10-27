import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import java.io.*;
import java.sql.SQLException;

/**
 * Created by aharon on 3/11/15.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        GigaSpace gigaspace = new GigaSpaceConfigurer(new UrlSpaceConfigurer("jini://*/*/mySpace?groups=aharon")).gigaSpace();
        String myfileName = "/tmp/test.pdf";

        File myfile = new File(myfileName);

        //writing Space File to space
        SpaceFile spaceFile = new SpaceFile(myfile.getName(),fileToBytes(myfileName));
        gigaspace.write(spaceFile);

        //reading Space File from space
        SpaceFile spaceFile2 = gigaspace.readById(SpaceFile.class, "test.pdf");
        bytesTofile(spaceFile2.getContent(), "/tmp/test_.pdf");
    }

    public static byte[] fileToBytes(String fileName) {
        FileInputStream inFile = null;
        byte[] content = null;

        try {
            System.out.println("Reading " + fileName + "...");
            inFile = new FileInputStream(fileName);
            int fileLength = (int) inFile.getChannel().size();
            content = new byte[fileLength];
            int length = 0;
            length = inFile.read(content);
            System.out.println("Read " + length + " out of " + fileLength);
            inFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inFile != null)
                try {
                    inFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return content;
    }

    public static void bytesTofile(byte[] content, String fileName) {
        ByteArrayInputStream bais = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        try {
            bais = new ByteArrayInputStream(content);
            System.out.println("Copying Space file Data to destination file...");
            byte[] buffer = new byte[100];
            fos = new FileOutputStream(fileName);
            bis = new BufferedInputStream(bais);

            for (int count = 0; count != -1;) {
                count = bis.read(buffer);
                fos.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bais != null)
                    bais.close();
                if (fos != null)
                    fos.close();
                if (bis != null)
                    bis.close();
            } catch (Exception e) {
            }
        }
        System.out.println("Copying Space file Data to destination file...Done!");
    }

}
