package domain;

import json.*;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private ArrayList<Tuple<String, Integer>> Result = new ArrayList<>();

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        for (Tuple<String, Integer> ex: exams) {
            Result.add(ex);
        }
    }



    @Override
    public JsonObject toJsonObject() {
        JsonObject student = super.toJsonObject();
        JsonObject[] exams = new JsonObject[Result.size()];//масив екзаменів
        int i = 0;
        for (Tuple<String, Integer> ex: Result) {//для кожного екзамена створюю масив
            exams[i] = new JsonObject(
                    new JsonPair("course", new JsonString(ex.key)),
                    new JsonPair("mark", new JsonNumber(ex.value)),
                    new JsonPair("passed", new JsonBoolean(ex.value > 2)));

            i += 1;
        }
        student.add(new JsonPair("exams", new JsonArray(exams)));//до обєкта додаю екзамени
        return student;
    }
}

