#include <wiringPi.h>  
#include "com_kolpakov_andrey_pi_home_sensors_dht11_DHT11Reader.h"
#include <stdio.h>  
#include <stdlib.h>  
#include <stdint.h>  
#define MAX_TIME 85  
jint dht11_val[5]={0,0,0,0,0};  
  
jintArray dht11_read_val(JNIEnv *env, jint gpio)  
{  
jintArray result;
result = (*env)->NewIntArray(env, 5);
  if (result == NULL){
	return NULL;
}
  uint8_t lststate=HIGH;  
  uint8_t counter=0;  
  uint8_t j=0,i;  
  for(i=0;i<5;i++)  
     dht11_val[i]=0;  
  pinMode(gpio,OUTPUT);  
  digitalWrite(gpio,LOW);  
  delay(18);  
  digitalWrite(gpio,HIGH);  
  delayMicroseconds(40);  
  pinMode(gpio,INPUT);  
  for(i=0;i<MAX_TIME;i++)  
  {  
    counter=0;  
    while(digitalRead(gpio)==lststate){  
      counter++;  
      delayMicroseconds(1);  
      if(counter==255)  
        break;  
    }  
    lststate=digitalRead(gpio);  
    if(counter==255)  
       break;  
    // top 3 transistions are ignored  
    if((i>=4)&&(i%2==0)){  
      dht11_val[j/8]<<=1;  
      if(counter>16)  
        dht11_val[j/8]|=1;  
      j++;  
    }  
  }  
  // verify cheksum and print the verified data  
  if((j>=40)&&(dht11_val[4]==((dht11_val[0]+dht11_val[1]+dht11_val[2]+dht11_val[3])& 0xFF)))  
  {  
    printf("humidity-relative:%d.%d|temperature-celsius:%d.%d\n",dht11_val[0],dht11_val[1],dht11_val[2],dht11_val[3]); 
	(*env)->SetIntArrayRegion(env, result, 0, 5, dht11_val);
  }  
  else {  
   printf("Invalid Data\n");
  }
  return result;
}  

JNIEXPORT jintArray JNICALL Java_com_dor_smarthome_app_sensors_types_dht11_DHT11Reader_getResponse
  (JNIEnv *env, jobject myClass, jint gpio) {
if(wiringPiSetup()==-1) return NULL;
return dht11_read_val(env, gpio); 
}