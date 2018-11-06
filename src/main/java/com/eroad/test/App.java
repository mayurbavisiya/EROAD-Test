package com.eroad.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

import com.eroad.util.Utility;
import com.google.maps.model.LatLng;

/**
 *
 * @author Mayur
 */
public class App {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        App app = new App();
        Path filePath = new App().getfilePath("input.csv");
        app.readWriteFile(filePath);
    }

    /**
     *
     * @param name
     * @return
     * @throws URISyntaxException
     */
    public Path getfilePath(String name) throws URISyntaxException {
        ClassLoader classLoader = new App().getClass().getClassLoader();
        Path filePath = Paths.get(classLoader.getResource(name).toURI());
        return filePath;
    }

    /**
     *
     * @param path
     * @throws IOException
     * @throws URISyntaxException
     */
    public void readWriteFile(Path path) throws IOException, URISyntaxException {
        List<String> inputData = Files.readAllLines(path);
        Utility obj = new Utility();
        List<String> output = getOutput(inputData, obj);
        Files.write(new App().getfilePath("output.csv"), output);
        output.stream().forEach(x -> System.out.println(x));
    }

    /**
     *
     * @param data
     * @param obj
     * @return
     */
    public List<String> getOutput(List<String> data, Utility obj) {
        return data.stream().map(input -> {
            final String[] split = input.split(",");
            String dateTime = split[0];
            Double lat = Double.valueOf(split[1]);
            Double lng = Double.valueOf(split[2]);
            LatLng latLng = new LatLng(lat, lng);
            Optional<TimeZone> timeZone = obj.getTimeZoneFromGoogle(latLng);
            if (!timeZone.isPresent()) {
                return "Invalid input";
            }
            LocalDateTime localDateTime = new Utility().getLocalDate(dateTime);
            String formattedLocalDateTime = localDateTime.toString().replace("T", " ");
            final String finalString = input.concat(",").concat(timeZone.get().getID()).concat(",").concat(formattedLocalDateTime);
            return finalString;
        }).collect(Collectors.toList());
    }
}
