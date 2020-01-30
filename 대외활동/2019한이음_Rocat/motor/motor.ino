const int anPin1 = 0;
long distance1;

void setup() {
  Serial.begin(9600);  // sets the serial port to 9600
}

void read_sensors(){
  distance1 = analogRead(anPin1)/2;
}

void print_all(){
  Serial.print("S1");
  Serial.print(" ");
  Serial.print(distance1);
  Serial.print("inches");
  Serial.println();
}

void loop() {
  read_sensors();
  print_all();
  delay(50); 
}
