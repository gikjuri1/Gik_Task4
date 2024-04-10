package ru.gik.task4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
@Loggable
public class WriterToFile implements ConveyerDataWriter {
    public WriterToFile( @Value("${spring.application.pathoutput}") String path) {
        this.pathoutput = path;
    }
    //    public WriterToFile (String path) {
//        this.path = path;
//    }
    // @Value("${spring.application.pathoutput}")
    private String pathoutput;


//    public String getPath() {
//        return path;
//    }

//    public void setPath(String path) {
//        this.path = path;
//    }

    @Override
    public void write(String str) {
        FileWriter fw;
        try {
            fw = new FileWriter(pathoutput);
            fw.write(str);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
