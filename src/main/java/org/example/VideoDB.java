package org.example;

import java.io.*;
import java.util.*;

public class VideoDB {

    private static final String FILE_NAME = "videos.txt";

    // VIDEO SAQLASH (ADMIN yuborgan video)
    public static void save(String fileId) {
        try {
            List<String> all = getAll();

            int id = all.size() + 1;

            String line = id + "|" + fileId;

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            writer.write(line);
            writer.newLine();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIDEO OLISH (USER so‘raganda)
    public static String getVideo(String number) {
        try {
            List<String> all = getAll();

            for (String line : all) {
                String[] parts = line.split("\\|");

                if (parts[0].equals(number)) {
                    return parts[1];
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // HAMMA VIDEOLARNI O‘QISH
    private static List<String> getAll() {
        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                file.createNewFile();
            }

            return java.nio.file.Files.readAllLines(file.toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
