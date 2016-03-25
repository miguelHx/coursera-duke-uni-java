/*eportfolio Create an image from scratch algorithmically with a pattern
PART 1: Create an Image from Scratch
*/

var output = new SimpleImage(256, 256);

for (var pixel of output.values()) {
    var x = pixel.getX();
    var y = pixel.getY();
    if (x < y && x < 40) {
    pixel.setRed(x);
    pixel.setGreen(x);
    pixel.setBlue(100);
    }
    else if (x > y && x < 220) {
        pixel.setRed(x);
        pixel.setGreen(x);
        pixel.setBlue(100);
    }
    else {
        pixel.setRed(y);
        pixel.setGreen(x);
        pixel.setBlue(150);
    }
    
}
print(output);
