package au.com.workingmouse.challenge.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class VelocityAndDirectionData {

    private @NonNull Timestamp timestamp;

    private @NonNull Integer record, dcsModel, dcsSerial;
    
    private @NonNull Double dcsAbsspdAvg, dcsDirectionAvg, dcsNorthCurAvg, dcsEastCurAvg, dcsHeadingAvg, 

    				dcsTiltXAvg,dcsTiltYAvg,dcsSpStdAvg,dcsSigStrengthAvg,dcsPingCntAvg,dcsAbsTiltAvg,
    		
    				dscMaxTiltAvg,dcsStdTiltAvg;
   

}

