import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class SaveData {
    //Find a file first
    public void SaveToFile(String filePath, String user, int points) {
        File file = new File(filePath);
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
                System.out.print("Creating path");
                file.getParentFile().mkdirs();
            }
        } catch (Exception e) {
            System.out.println("Error on path creation" + e);
        }
    }

    private void checkFile(File file) {
        try {
            if (!file.exists()){
                System.out.print("Creating file");
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Error on file/path creation" + e);
        }
    }

    public void appendContent(String user, int points, File file) {
        //Get local machine timezone
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = sdf.toString();
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
