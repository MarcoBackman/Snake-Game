import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 * Data to load: User name, points, date
 *
 */

public class LoadData {
    //Load file from given path
    public void readPointsFromFile(String fileDirectory) {
        try {
          File file = new File(fileDirectory);
          FileReader fr = new FileReader(file);

          try(BufferedReader br = new BufferedReader(fr)) {
            for(String line; (line = br.readLine()) != null;) {
                System.out.println(line);
            }
          } catch (Exception e) { // line is not visible here.
            e.printStackTrace();
          }
        } catch (Exception e) { //If file is not there send a default value
          e.printStackTrace();
        }
    }
}
