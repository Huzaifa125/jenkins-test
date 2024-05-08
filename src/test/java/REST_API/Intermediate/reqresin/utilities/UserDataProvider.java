package REST_API.Intermediate.reqresin.utilities;

import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class UserDataProvider {

    @DataProvider(name = "userIds")
    public static Iterator<Object[]> getUserIdData() {
        List<Object[]> userIds = new ArrayList<>();
        String csvFile = System.getProperty("user.dir")+"/src/test/resources/userIds.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                userIds.add(new Object[]{Integer.parseInt(data[0])});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userIds.iterator();
    }


    @DataProvider(name = "userIdsTwo")
    public static Iterator<Object[]> getUserIdDataTwo() {
        List<Object[]> userIds = new ArrayList<>();
        String csvFile = System.getProperty("user.dir")+"/src/test/resources/userIdsTwo.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                userIds.add(new Object[]{Integer.parseInt(data[0])});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userIds.iterator();
    }



}
