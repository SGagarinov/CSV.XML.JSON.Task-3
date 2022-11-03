import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, ParseException {

        String json = readString("input.json");
        List<Employee> list = jsonToList(json);

        for (Employee emp : list) {
            System.out.println(emp.toString());
        }
    }

    public static String readString(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Employee> jsonToList(String json) throws ParseException {
        List<Employee> employees = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray array = (JSONArray) jsonParser.parse(json);
            for (Object object : array) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                String jsonString = ((JSONObject) object).toJSONString();
                Employee employee = gson.fromJson(jsonString, Employee.class);
                employees.add(employee);
            }
        } catch (RuntimeException ex) {

        }

        return employees;
    }
}
