const int pwPin1 = 3;
long pulse1, sensor1;

void setup () {
  Serial.begin(9600);
  pinMode(pwPin1, INPUT);
}

void read_sensor(){
  pulse1 = pulseIn(pwPin1, HIGH);
  sensor1 = pulse1/147;
}

void printall(){         
  Serial.print("S1");
  Serial.print(" ");
  Serial.print(sensor1);
  Serial.println(" ");
}

void loop () {
  read_sensor();
  printall();
  delay(50); // This delay time changes by 50 for every sensor in the chain.  For 5 sensors this will be 250
}
