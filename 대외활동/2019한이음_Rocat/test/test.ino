#include <Adafruit_GPS.h>
#include <SoftwareSerial.h>
#define GPSECHO  true
SoftwareSerial mySerial(8, 7);
Adafruit_GPS GPS(&mySerial);

const int anPin1 = 0;
long distance_temp;
long distance = 999;
int count1 = 0;
int count2 = 0;
bool isComing = false;

void setup() {
  Serial.begin(9600);  
  /*
  GPS.begin(9600);
  GPS.sendCommand(PMTK_SET_NMEA_OUTPUT_RMCGGA);
  // uncomment this line to turn on only the "minimum recommended" data
  //GPS.sendCommand(PMTK_SET_NMEA_OUTPUT_RMCONLY);
  // For parsing data, we don't suggest using anything but either RMC only or RMC+GGA since
  // the parser doesn't care about other sentences at this time

  // Set the update rate
  GPS.sendCommand(PMTK_SET_NMEA_UPDATE_1HZ);   // 1 Hz update rate
  // For the parsing code to work nicely and have time to sort thru the data, and
  // print it out we don't suggest using anything higher than 1 Hz

  // Request updates on antenna status, comment out to keep quiet
  GPS.sendCommand(PGCMD_ANTENNA);

  delay(1000); // 딜레이 시간
  // Ask for firmware version
  mySerial.println(PMTK_Q_RELEASE);*/

}
uint32_t timer = millis();

void Calculate_distance(){
  while(!isComing)
  {
    distance_temp = analogRead(anPin1)/2;
    if(distance_temp < distance)
    {
      count1++;
      distance = distance_temp;
    }
    else if(distance_temp >= distance && count1 > 0)
      count2++;
    else if(distance_temp == distance)
    {
      //Serial.print("값 변동없음");
      Serial.println();
    }
  
    if(count1 > 15)
      isComing = true;
    else if(count2 > 175)
    {
      count1 = 0;
      count2 = 0;
      distance = 999;
    }
   
    Serial.print("count1: ");
    Serial.print(count1);
    Serial.println();
    /*
    Serial.print("count2: ");
    Serial.print(count2);
    Serial.println();
    */
  }
}

void get_gps()
{
  long Logitude;
  long Latitude;
  char c = GPS.read();
  // if you want to debug, this is a good time to do it!
  if ((c) && (GPSECHO))
    Serial.write(c);

  // if a sentence is received, we can check the checksum, parse it...
  if (GPS.newNMEAreceived()) {
    // a tricky thing here is if we print the NMEA sentence, or data
    // we end up not listening and catching other sentences!
    // so be very wary if using OUTPUT_ALLDATA and trytng to print out data
    //Serial.println(GPS.lastNMEA());   // this also sets the newNMEAreceived() flag to false

    if (!GPS.parse(GPS.lastNMEA()))   // this also sets the newNMEAreceived() flag to false
      return;  // we can fail to parse a sentence in which case we should just wait for another
  }

  // if millis() or timer wraps around, we'll just reset it
  if (timer > millis())  timer = millis();

  // approximately every 2 seconds or so, print out the current stats
  if (millis() - timer > 2000) {
    timer = millis(); // reset the timer

    Serial.print("\nTime: ");
    if (GPS.hour < 10) { Serial.print('0'); }
    Serial.print(GPS.hour, DEC); Serial.print(':');
    if (GPS.minute < 10) { Serial.print('0'); }
    Serial.print(GPS.minute, DEC); Serial.print(':');
    if (GPS.seconds < 10) { Serial.print('0'); }
    Serial.print(GPS.seconds, DEC); Serial.print('.');
    if (GPS.milliseconds < 10) {
      Serial.print("00");
    } else if (GPS.milliseconds > 9 && GPS.milliseconds < 100) {
      Serial.print("0");
    }
    Serial.println(GPS.milliseconds);
    Serial.print("Date: ");
    Serial.print(GPS.day, DEC); Serial.print('/');
    Serial.print(GPS.month, DEC); Serial.print("/20");
    Serial.println(GPS.year, DEC);
    Serial.print("Fix: "); Serial.print((int)GPS.fix);
    Serial.print(" quality: "); Serial.println((int)GPS.fixquality);
    if (GPS.fix) {
      Serial.print("Location: ");
      Serial.print(floor(GPS.latitude/100)+ (GPS.latitude/100-floor(GPS.latitude/100))*100/60, 10); Serial.print(GPS.lat);
      Latitude = floor(GPS.latitude/100)+ (GPS.latitude/100-floor(GPS.latitude/100))*100/60;
      Serial.print(", ");
      Serial.print(floor(GPS.longitude/100)+ (GPS.longitude/100-floor(GPS.longitude/100))*100/60, 10); Serial.println(GPS.lon);
      Logitude = floor(GPS.longitude/100)+ (GPS.longitude/100-floor(GPS.longitude/100))*100/60;

      Serial.print("Speed (knots): "); Serial.println(GPS.speed);
      Serial.print("Angle: "); Serial.println(GPS.angle);
      Serial.print("Altitude: "); Serial.println(GPS.altitude);
      Serial.print("Satellites: "); Serial.println((int)GPS.satellites);
    }
  }
}

void loop() {
  uint8_t temp, temp_last;
  Calculate_distance();
  Serial.print("무엇인가 다가옴!");
  isComing = false;
  count1 = 0;
  count2 = 0;
  distance = 999;
  //get_gps();
  delay(100000); 
}
