#include <Stepper.h>
 
int in1Pin = 12;
int in2Pin = 11;
int in3Pin = 10;
int in4Pin = 9;
const int pwPin1 = 3;
long pulse1, sensor1;
 
Stepper motor(768, in1Pin, in2Pin, in3Pin, in4Pin); 
 
void setup()
{
    pinMode(in1Pin, OUTPUT);
  pinMode(in2Pin, OUTPUT);
  pinMode(in3Pin, OUTPUT);
  pinMode(in4Pin, OUTPUT);
  pinMode(pwPin1, INPUT);
 
  // this line is for Leonardo's, it delays the serial interface
  // until the terminal window is opened
  while (!Serial);

  Serial.begin(9600);
  motor.setSpeed(20);
}

void loop()
{
  if (Serial.available())
  {
    int steps = Serial.parseInt();
    motor.step(steps);
  }
  read_sensor();
  printall();
  delay(50);
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
