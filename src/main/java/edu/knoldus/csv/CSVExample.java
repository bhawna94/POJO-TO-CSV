package edu.knoldus.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CSVExample {

    public static void main(String[] args) throws IOException {
         /*Data*/
        List<Info> info = Arrays.asList(Info.builder().brand("B1").version("V1").build(),
                Info.builder().brand("B1").version("V2").build());

        List<Data> data = Arrays.asList(Data.builder().code("C1").policies(info).build(),
                Data.builder().code("c2").policies(info).build());

        /*Every Record is separated by new line*/
        CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator('\n');

        /*Constructs a FileWriter object given a file name and true will append the data */
        FileWriter fileWriter = new FileWriter("abc.csv", true);
        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat);

        IntStream.range(0, data.size()).forEach(x -> {
            String code = data.get(x).getCode();
            String brand = data.get(x).getPolicies().get(x).getBrand();
            String version = data.get(x).getPolicies().get(x).getVersion();

            try {
                csvPrinter.printRecord(code, brand, version);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        /*Print the record into file separated by Record Separator*/

        fileWriter.flush();

        fileWriter.close();

        csvPrinter.close();
    }
}
