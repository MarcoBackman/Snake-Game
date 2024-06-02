package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveData {
    private final String DEFAULT_FILE_LOC = "./User-Points.log";
    //Find a file first
    public void saveToFile(final String filePath, final String user, final int points) {
        File file;
        if (filePath == "") {
            file = new File(DEFAULT_FILE_LOC);
        } else {
            file = new File(filePath);
        }
        //Check path
        checkPath(file);
        //Check file
        checkFile(file);
        appendContent(user, points, file);
    }

    private void checkPath(File file) {
        try {
            //check if the parent path exists
            if (!file.getParentFile().exists()) {
                System.out.println("Creating path");
                file.getParentFile().mkdirs();
            }
        } catch (Exception e) {
            System.out.println("Error on path creation" + e);
        }
    }

    private void checkFile(final File file) {
        try {
            if (!file.exists()){
                System.out.println("Creating file");
                file.createNewFile();
            } else {
                System.out.println("Existing file found.");
            }
        } catch (Exception e) {
            System.out.println("Error on file/path creation" + e);
        }
    }

    public void appendContent(final String user, final int points, final File file) {
        //Get local machine timezone
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = sdf.format(new Date());
        String contents = String.format("%s - %10s : %5d", timeStamp, user, points);

        try {
            FileWriter fw = new FileWriter(file, true); //true - append
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contents);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println("Error no append");
        }
    }
}
