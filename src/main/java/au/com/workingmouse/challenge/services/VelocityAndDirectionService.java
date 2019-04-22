package au.com.workingmouse.challenge.services;

import au.com.workingmouse.challenge.models.VelocityAndDirectionData;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.text.DecimalFormat;
public class VelocityAndDirectionService {

    public static VelocityAndDirectionData parseLine(String line) {
        // NOTE: This CSV parsing is not fully inclusive
        String[] parts = line.split(",");

        VelocityAndDirectionData velocityAndDirectionData = new VelocityAndDirectionData();
       try {
        velocityAndDirectionData.setTimestamp(Timestamp.valueOf(parts[0]));
       } catch(Exception e) {
       }
        velocityAndDirectionData.setRecord(Integer.parseInt(parts[1]));
        velocityAndDirectionData.setDcsModel(Integer.parseInt(parts[2]));
        velocityAndDirectionData.setDcsSerial(Integer.parseInt(parts[3]));
        velocityAndDirectionData.setDcsAbsspdAvg(Double.parseDouble(parts[4]));
        velocityAndDirectionData.setDcsDirectionAvg(Double.parseDouble(parts[5]));
        velocityAndDirectionData.setDcsNorthCurAvg(Double.parseDouble(parts[6]));
        velocityAndDirectionData.setDcsEastCurAvg(Double.parseDouble(parts[7]));
        velocityAndDirectionData.setDcsHeadingAvg(Double.parseDouble(parts[8]));
        velocityAndDirectionData.setDcsTiltXAvg(Double.parseDouble(parts[9]));
        velocityAndDirectionData.setDcsTiltYAvg(Double.parseDouble(parts[10]));
        velocityAndDirectionData.setDcsSpStdAvg(Double.parseDouble(parts[11]));
        velocityAndDirectionData.setDcsSigStrengthAvg(Double.parseDouble(parts[12]));
        velocityAndDirectionData.setDcsPingCntAvg(Double.parseDouble(parts[13]));
        velocityAndDirectionData.setDcsAbsTiltAvg(Double.parseDouble(parts[14]));
        velocityAndDirectionData.setDscMaxTiltAvg(Double.parseDouble(parts[15]));
        velocityAndDirectionData.setDcsStdTiltAvg(Double.parseDouble(parts[16]));

        return velocityAndDirectionData;
    }

    private static DecimalFormat df2 = new DecimalFormat("#.##");
    
    /**
     * Takes the given ordered list of VelocityAndDirectionData properties and initialises a new
     * VelocityAndDirectionData object.
     * @param line - row to parse
     * @return VelocityAndDirectionData
     */
    public static VelocityAndDirectionData parseLine(List<String> line) {

        if (line.size() != 17) {
            throw new IllegalArgumentException("VelocityAndDirectionData Objects require 17 input arguments.");
        }

        return new VelocityAndDirectionData(
                Timestamp.valueOf(line.get(0)),
                Integer.parseInt(line.get(1)),
                Integer.parseInt(line.get(2)),
                Integer.parseInt(line.get(3)),
                Double.parseDouble(line.get(4)),
                Double.parseDouble(line.get(5)),
                Double.parseDouble(line.get(6)),
                Double.parseDouble(line.get(7)),
                Double.parseDouble(line.get(8)),
                Double.parseDouble(line.get(9)),
                Double.parseDouble(line.get(10)),
                Double.parseDouble(line.get(11)),
                Double.parseDouble(line.get(12)),
                Double.parseDouble(line.get(13)),
                Double.parseDouble(line.get(14)),
                Double.parseDouble(line.get(15)),
                Double.parseDouble(line.get(16))
        );
    }

    public static List<VelocityAndDirectionData> parseLines(List<String> lines) {
        var parsedLines = new ArrayList<VelocityAndDirectionData>();

        int count = 0;
        for (String line : lines) {
            if (count++ == 0) {
                // Skip header
                continue;
            }
            parsedLines.add(VelocityAndDirectionService.parseLine(line));
        }

        return parsedLines;
    }

    public static double[] findAvg(List<VelocityAndDirectionData> velocityAndDirectionDataset) {
    	double[] result = new double[16];
    	for (VelocityAndDirectionData data: velocityAndDirectionDataset) {
    		result[0] += data.getRecord();
    		result[1] += data.getDcsModel();
    		result[2] += data.getDcsSerial();
    		result[3] += data.getDcsAbsspdAvg();
    		result[4] += data.getDcsDirectionAvg();
    		result[5] += data.getDcsNorthCurAvg();
    		result[6] += data.getDcsEastCurAvg();
    		result[7] += data.getDcsHeadingAvg();
    		result[8] += data.getDcsTiltXAvg();
    		result[9] += data.getDcsTiltYAvg();
    		result[10] += data.getDcsSpStdAvg();
    		result[11] += data.getDcsSigStrengthAvg();
    		result[12] += data.getDcsPingCntAvg();
    		result[13] += data.getDcsAbsTiltAvg();
    		result[14] += data.getDscMaxTiltAvg();
    		result[15] += data.getDcsStdTiltAvg();
    	}
    	for (int i = 0; i < result.length; i++) {
    		result[i] /= velocityAndDirectionDataset.size();
    	}
    	return result;
    }
    
    public static String summarise(List<VelocityAndDirectionData> velocityAndDirectionDataset) {
        Integer totalLines = velocityAndDirectionDataset.size();
        double[] averages = findAvg(velocityAndDirectionDataset);
        var summaryBuilder = new StringBuilder() ;

        // Transform dataset to be listed in columns rather than rows

        summaryBuilder.append("<head></head>")
                .append("<body>")
                .append("<h2>Summary</h2>")
                .append("<br />")
                .append("<strong>Total Lines:</strong>" + totalLines.toString())
                .append("<p>");  // Add p element to HTML for displaying averages
        
        for (double average: averages) {
        	summaryBuilder.append(df2.format(average) + " ");
        }
        // TODO: You will also have to do some work here to ensure the details are complete.
        summaryBuilder.append("</p>")
                .append("</body>");

        return summaryBuilder.toString();
    }
}
