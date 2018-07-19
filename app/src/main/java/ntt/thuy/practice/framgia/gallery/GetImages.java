package ntt.thuy.practice.framgia.gallery;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by thuy on 18/07/2018.
 */
public class GetImages {
    public static final String JPG = "jpg";
    public static final String JPEG = "jpeg";
    public static final String TIFF = "tiff";
    public static final String GIF = "gif";
    public static final String PNG = "png";

    public static void getAllImages(File file, ArrayList<String> list){
        File[] listFile = file.listFiles();
        for (File f:listFile){
            if(f.isDirectory()){
                getAllImages(f, list);
            } else if (isImage(f.getPath())){
                list.add(f.getPath());
            }
        }
    }

    private static boolean isImage(String name) {
        if(name.endsWith(JPG)
                || name.endsWith(JPEG)
                || name.endsWith(TIFF)
                || name.endsWith(GIF)
                || name.endsWith(PNG)){
            return true;
        }
        return false;
    }
}
