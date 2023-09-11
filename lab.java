
public class lab {
float speed = 0;
boolean isMoving = false;
public void stop() {
isMoving = false;
}
public void accelerate(float amount) {
speed += amount;
isMoving = speed != 0;
}
public void printSpeed() {
System.out.println("Speed: " + speed);
}
public void printIsMoving() {
if(isMoving) {
System.out.println("The car is moving");
} else {
System.out.println("The car is not moving");
}
}
}