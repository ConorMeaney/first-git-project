#include <SPI.h>         
#include <Ethernet.h>
#include <EthernetUdp.h>         
#include <SoftwareSerial.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress ip(192,168,1,13); 
byte subnet[] = { 255, 255, 255, 0 };  
EthernetServer server(8081);  // port 8081

String HTTP_req;          // stores the HTTP request
boolean Motor_status = 0;   // state of Motor, off at the start
unsigned int localPort = 8081; 


// buffers for receiving and sending data
char packetBuffer[UDP_TX_PACKET_MAX_SIZE]; //buffer to hold incoming packet,
char  ReplyBuffer[] = "packet here";       // a string to send back

// An EthernetUDP instance to send and receive packets over UDP
EthernetUDP Udp;

void setup() {
  // start the Ethernet and UDP:
  Ethernet.begin(mac,ip);
  Udp.begin(localPort);
  pinMode(2,OUTPUT);
  Serial.begin(9600);
}

void loop() {

  int packetSize = Udp.parsePacket();

Serial.println(packetSize);
if(packetSize)
  {
    Serial.print("size of packet from  ");
    Serial.println(packetSize);
    Serial.print("device: ");
    IPAddress remote = Udp.remoteIP();
    for (int i =0; i < 4; i++)
    {
      Serial.print(remote[i], DEC);
      if (i < 3)
      {
        Serial.print(".");
      }
    }
    Serial.print(", port ");
    Serial.println(Udp.remotePort());

    // recving the packet 
    Udp.read(packetBuffer,UDP_TX_PACKET_MAX_SIZE);
    Serial.println("Contents:");
    Serial.println(packetBuffer);
    Serial.println(packetBuffer[0]);
  
    if(packetBuffer[0]=='1'){
      digitalWrite(2,HIGH);//sets the voltage for pin 2 to hgih  
    }else if(packetBuffer[0]=='2'){
       digitalWrite(2,LOW);//sets the voltage for pin 2 to low
    }

    // sends a reply back to the 192,168,1,13 and 8081 that sent the packet 
    Udp.beginPacket(Udp.remoteIP(),Udp.remotePort());
    Udp.write("hi");
    Udp.endPacket();
  }
    
}

